package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	
	// video 174
	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException
	{
		// read the Jason file and convert it to string variable
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") 
		                                                +"\\src\\test\\java\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);

		// convert string to hashmap
		// go to MAVEN REPOSITORY and get   "jackson databind"
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		
		return data;
	}
}
