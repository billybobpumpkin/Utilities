package forensic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * A way to read important data from a file on any OS.
 */
public class FileProcessor implements IHaveMetaData
{
	private final Path filePath;
	private final String pathToFile;
	private final File file;
	
	/**
	 * Creates the class so it can start reading the data. Throws FileNotFoundException when
	 * 	the specified file cannot be found.
	 * @param pathToFile
	 * 		  The path to the file.
	 * @throws FileNotFoundException
	 * 		   If the file is not found, it will throw this exception.
	 */
	public FileProcessor(String pathToFile) throws FileNotFoundException 
	{
		this.filePath = Paths.get(pathToFile);
		this.pathToFile = pathToFile;
		this.file = new File(pathToFile);
		
		if (!file.exists())
		{
			throw new FileNotFoundException ("File doesn't exist");
		}
	}
	
	/**
	 * Attempts to retrieve the metadata or EXIF data from the file.
	 * Includes the name, size, time it was created, last time it was modified,
	 * 	last time it was accessed, and the permissions to access the file.
	 */
	@Override
	public SimpleMetaData getMetaData()
	{			
		try {
	        BasicFileAttributes attributes = Files.readAttributes(this.filePath, BasicFileAttributes.class);
	        AclFileAttributeView permissions = Files.getFileAttributeView(this.filePath, AclFileAttributeView.class);
	        
	        String name = this.file.getName();
			long size = this.file.length();
	        FileTime creationTime = attributes.creationTime();
	        FileTime modTime = attributes.lastModifiedTime();
	        FileTime lastAccessTime = attributes.lastAccessTime();
	        String permissionString = permissions.toString();
            
	        return new SimpleMetaData(name, this.pathToFile, size,creationTime, modTime, lastAccessTime, permissionString);
	        
	    } catch (IOException e) {
	        System.err.println("Error reading file attributes: " + e.getMessage());
	    }
		
		return null;
	}
	
	/**
	 * Creates the metadata file as a YFMD (Yelifari Formated MetaData)
	 */
	public void createMetaDataFile()
	{
		try {
			this.getMetaData().asYFMD().createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the file's header, which is also know as the beginning of the file.
	 * @param numOfBytes
	 * 		  How much of the header you want to get.
	 * @return
	 */
	public byte[] getFileHeader(int numOfBytes)
	{
		try {
			FileInputStream fis = new FileInputStream(this.file);
			int size = numOfBytes >= (int)this.file.length() ? (int)this.file.length() : numOfBytes;
			byte[] fileBytes = new byte[size];
			fis.read(fileBytes);			
			fis.close();
			
			return fileBytes;
		} catch(IOException e) {
			System.err.println("An error occured when reading the file: " + this.file.getName());
		}
		
		return null;
	}
	
	/**
	 * Gets the first few bytes of the file, with the specified amount of bytes.
	 * 
	 * @param  numOfBytes
	 * 		   The amount of bytes that you want to include as the header.
	 */
	public String getFileHeaderText(int numOfBytes)
	{
		byte[] header = this.getFileHeader(numOfBytes);
		if (header != null)
		{
			String text = "";
			for (byte b : header)
			{
				char c = (char) (b & 0xFF);
				text += c;
			}
			return text;
		}
		return "";
	}
	
	/**
	 * Simply creates a new file with the fileProccessor name and adds "_TEXT_DUMP.txt"
	 * 
	 * @return  The new file created, or if it failed, null.
	 */
	private File createDumpFile()
	{
		File newFile = new File(this.file.getName().substring(0, this.file.getName().lastIndexOf(".")) + "_TEXT_DUMP.txt");
		try {
			newFile.createNewFile();
			return newFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Returns a string in which the end was removed to fit the size of the specified amount of bytes.
	 * @param line
	 *        The string to be truncated.
	 * @param numOfBytes
	 * 		  The amount of bytes the the string will fit to.
	 * @return The new string created from the truncation.
	 * @throws UnsupportedEncodingException
	 * 		   Will be thrown if UTF-8 cannot be used in byte translation.
	 */
	private String truncateStringByByteSize(String line, int numOfBytes) throws UnsupportedEncodingException
	{
		/*
		 * We have 2 byte arrays because arrays are immutable,
		 *  so we will just copy the strBytes into the newBytes, leaving out any excess
		 *  characters from strBytes.
		 */
		String text = "";
    	byte[] strBytes = line.getBytes("UTF-8");
    	byte[] newBytes = new byte[numOfBytes];
    	
    	for (int i = 0; i < numOfBytes; i++)
    	{
    		newBytes[i] = strBytes[i];
    	}
		for (byte b : newBytes)
		{
			char c = (char) (b & 0xFF);
			text += c;
		}
		
		return text;
	}
	
	/**
	 * Takes the contents of the file and dumps it into a text document.
	 * @param numOfBytes
	 * 		  Maximum amount of bytes that will be read.
	 */
	public void dumpContentsIntoTextDoc(int numOfBytes)
	{
		//Create the text file.
		File textDump = this.createDumpFile();
		/*
		 * This is to keep track of how many bytes have been read,
		 * 	to truncate it if it goes beyond the max value.
		 */
		int totalBytes = 0;
		
		//Make sure the textDump file was successfully created.
		if (textDump != null)
		{
			/*
			 * Make Sure that the program will not cause any internal errors while reading,
			 *  so there will not be a memory leak.
			 */
			try {
			
				//Create the needed buffers.
				BufferedReader bf = new BufferedReader(new FileReader(this.file));
            	BufferedWriter bw = new BufferedWriter(new FileWriter(textDump));
				String line = bf.readLine();
				
				if(line != null)
				{
					//We get the size of the bytes for each string by seeing how long the array of bytes is.
					totalBytes += line.getBytes("UTF-8").length;
					
					//If totalBytes > numOfBytes then we truncate it to remove excess characters.
		            if (totalBytes > numOfBytes)
		            {
		            	String text = this.truncateStringByByteSize(line, numOfBytes);
		    			bw.write(text + "\n");
		            }
		            else
		            {
		            	//Reset the totalBytes to 0 because the loop will start the line by line read all over.
		            	totalBytes = 0;
		            	while (line != null)
			            {
			            	totalBytes += line.getBytes("UTF-8").length;
		            		if (totalBytes > numOfBytes)
		            		{
		            			//Just like before, except we break it because we are done.
		            			String text = this.truncateStringByByteSize(line, numOfBytes);
		    	    			bw.write(text + "\n");
		    	    			break;
		            		}
			            	bw.write(line + "\n");
			            	line = bf.readLine();
			            }
		            }
				}
	            
				//Close to prevent memory leak.
	            bf.close();
	            bw.close();
	            
	            //Print to the console that we have successfully created the text dump file.
	            System.out.println(String.format("Successfully created text dump doc named '%s'", textDump.getName()));
			} catch (IOException e) {
				//Print to the console that we have failed to create the text dump file.
				e.printStackTrace();
				System.err.println(String.format("Failed to create text dump doc named '%s'", textDump.getName()));
			}
		}
	}
	
	/**
	 * Takes the contents of the file and dumps it into a text document.
	 */
	public void dumpContentsIntoTextDoc()
	{
		this.dumpContentsIntoTextDoc(Integer.MAX_VALUE);
	}
}