package com.atnt.rest.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConsumeJSONHelper {

	public void consumRestEndpoint(String restWSURL) throws MalformedURLException, IOException {
		URL url = new URL(restWSURL);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) connection;

		// Read the response.
		InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
		
		BufferedReader in = new BufferedReader(isr);
		
		//TODO: following is used to test parsing of json file i absence of actual service.It should be commented wile actual execution
		//BufferedReader in = new BufferedReader(new FileReader(new File("D:\\JBossWorkSpaces\\JSONProject\\ConsumeJSONProj\\src\\com\\atnt\\rest\\json\\TestFile")));

		String responseString = "";
		String outputString = "";
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}

		// json parsing

		System.out.println(outputString);
		
		JsonParser parser = new JsonParser();

		JsonElement jsonTree = parser.parse(outputString);

		long totalSum = 0;

		if (jsonTree.isJsonArray()) {

			JsonArray jsonArr = jsonTree.getAsJsonArray();

			for (JsonElement elem : jsonArr) {

				System.out.println("========= Keys for next JSON Doc ========= ");
				JsonObject obj = elem.getAsJsonObject();

				Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
				long currentSum = 0;
				boolean isNumberKeyPresent = false;
				for (Map.Entry<String, JsonElement> entry : entries) {
					System.out.println(entry.getKey());

					if (entry.getKey().equals("numbers")) {

						//long currentSum = 0;
						
						isNumberKeyPresent =  true;
						JsonArray numArr = entry.getValue().getAsJsonArray();
						for (JsonElement numElem : numArr) {

							currentSum += numElem.getAsInt();
						}

						

						totalSum += currentSum;
					}
				}
				
				if(isNumberKeyPresent)
				System.out.println("Sum of all numbers for number key = " + currentSum);

			}
			System.out.println();
			System.out.println(" Total of the numbers that were summed for the execution = " + totalSum);
		}
	}

}
