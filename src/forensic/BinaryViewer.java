package forensic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryViewer
{
	public static void tryConvertToTextFile(File file)
	{
		String outputFile = "output.txt";

        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            int b;
            while ((b = fis.read()) != -1)
            {
                String hex = String.format("%02X", b);
                fos.write(hex.getBytes());
            }

            System.out.println("Binary file converted to hex text successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}