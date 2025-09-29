package forensic.image;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.GpsDirectory;


public class EXIFAnalyzer
{
	private final File file;
	
	public EXIFAnalyzer(File file)
	{
		this.file = file;
	}
	
	public GeoLocation getGeoLocation()
	{
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(this.file);
			GpsDirectory gpsDir = metadata.getFirstDirectoryOfType(GpsDirectory.class);
	        if (gpsDir != null)
	        {
	            GeoLocation geoLocation = gpsDir.getGeoLocation();

	            return geoLocation;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public void exportExifDataToCSV()
	{
		if (this.file.exists())
		{
			try {
				Metadata metadata = ImageMetadataReader.readMetadata(this.file);
				Map<String, String> dataMap = new LinkedHashMap<String, String>();
				
				for(Directory dir : metadata.getDirectories())
				{
					for(Tag tag : dir.getTags())
					{
						dataMap.put(tag.getTagName(), tag.getDescription());
					}
				}
				
				
				GeoLocation geoLocation = this.getGeoLocation();
                if (geoLocation != null)
                {
                    dataMap.put("Latitude", String.valueOf(geoLocation.getLatitude()));
                    dataMap.put("Longitude", String.valueOf(geoLocation.getLongitude()));
                }
	            
	            String currentDir = System.getProperty("user.dir");
	            String csvPath = currentDir + File.separator + "exif_data.csv";
	            
	            try (PrintWriter writer = new PrintWriter(new FileWriter(csvPath)))
	            {
	                writer.println("Tag Name,Tag Description");
	                for (Map.Entry<String, String> entry : dataMap.entrySet())
	                {
	                    String key = entry.getKey().replaceAll(",", "");
	                    String value = entry.getValue().replaceAll(",", "");
	                    writer.printf("%s,%s%n", key, value);
	                }
	            }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void openInGoogleMaps()
	{
		GeoLocation geolocation = this.getGeoLocation();
		if(geolocation != null)
		{
			double latitude = geolocation.getLatitude();
			double longitude = geolocation.getLongitude();
			String url = String.format("https://www.google.com/maps?q=%f,%f", latitude, longitude);

	        if (Desktop.isDesktopSupported())
	        {
	            Desktop desktop = Desktop.getDesktop();
	            try {
	                desktop.browse(new URI(url));
	            } catch (IOException | URISyntaxException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.err.println("Desktop is not supported, cannot open browser.");
	        }
		}
	}
	
	public String getAddressFromCoordinates()
	{
		GeoLocation loc = this.getGeoLocation();
		if(loc != null)
		{
			double lat = loc.getLatitude();
			double lon = loc.getLongitude();
			String urlStr = String.format(
		            "https://nominatim.openstreetmap.org/reverse?format=json&lat=%f&lon=%f&addressdetails=1",
		            lat, lon
		        );

	        try {
	        	URL url = new URI(urlStr).toURL();
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        // Nominatim requires a valid User-Agent header identifying your application
		        conn.setRequestProperty("User-Agent", "JavaTestApp (bobhutt019@gmail.com)");

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        StringBuilder response = new StringBuilder();
		        String inputLine;
		        while ((inputLine = in.readLine()) != null)
		        {
		            response.append(inputLine);
		        }
		        in.close();

		        JSONObject json = new JSONObject(response.toString());
		        if (json.has("error"))
		        {
		            throw new Exception("Nominatim error: " + json.getString("error"));
		        }

		        // The display_name field contains the full address string
		        return json.getString("display_name");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		}
		
		return "No address found";
    }
}