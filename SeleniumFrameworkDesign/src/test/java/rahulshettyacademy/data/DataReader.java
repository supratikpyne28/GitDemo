package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataReader {
	
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		//read JSON File to String
		String jsonData=FileUtils.readFileToString(new File(System.getProperty("user.dir")+
				"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);//new method accepts file location to convert json data to String
		
		//convert String to HashMap using Jackson binder
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
			
		
	}

}
