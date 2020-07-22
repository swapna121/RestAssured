package helper;

import io.restassured.path.json.JsonPath;

public class Resuable {

	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}
}
