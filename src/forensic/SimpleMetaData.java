package forensic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.attribute.FileTime;

public class SimpleMetaData
{
	private String name;
	private String pathToFile;
	private long size;
	private FileTime creationTime;
	private FileTime modificationTime;
	private FileTime lastAccessTime;
	private String access;
	
	public SimpleMetaData()
	{
		this.name = "";
		this.pathToFile = "";
		this.size = 0l;
		this.creationTime = null;
		this.modificationTime = null;
		this.lastAccessTime = null;
		this.access = "";
	}
	
	public SimpleMetaData(String name, String pathToFile, long size, FileTime creationTime, FileTime modificationTime,
			FileTime lastAccessTime, String access)
	{
		this.name = name;
		this.pathToFile = pathToFile;
		this.size = size;
		this.creationTime = creationTime;
		this.lastAccessTime = lastAccessTime;
		this.modificationTime = modificationTime;
		this.access = access;
	}
	
	/**
	 * Returns a new file as a YFMD file (Yelifari Format MetaData
	 * @return
	 */
	public File asYFMD()
	{
		File newFile = new File(this.name.substring(0, this.name.lastIndexOf(".")) + ".YFMD");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
			
			bw.write("Name: " + this.name + "\n");
			bw.write("Size: " + String.valueOf(this.size) + "\n");
			bw.write("Path: " + this.pathToFile + "\n");
			bw.write("Creation Time: " + this.creationTime + "\n");
			bw.write("Last Access Time: " + this.lastAccessTime + "\n");
			bw.write("Modification Time: " + this.modificationTime + "\n");
			bw.write("Access: " + this.access + "\n");
			
			bw.close();
			return newFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String toString()
	{
		return String.format("MetaData:\n	Name: %s\n	Path: %s	\n	Size: %d\n"
				+ "	Creation Time: %s\n	Last Access Time: %s\n	Modification Time: %s\n	Access: %s\n",
				this.name, this.pathToFile, (int)this.size, this.creationTime,
				this.lastAccessTime, this.modificationTime, this.access);
	}
}