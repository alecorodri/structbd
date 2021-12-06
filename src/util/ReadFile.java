package util;

import java.io.IOException;
import java.util.LinkedList;

public class ReadFile {

	public static LinkedList<String> connectionProperties() throws IOException{
		LinkedList<String> properties = new LinkedList<String>();
		String x = "src/util/ConnectionProperties";

		@SuppressWarnings("resource")
		java.io.BufferedReader fi =	new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(x),"utf-8"));
		
		String current = fi.readLine();
		String arr[] = current.split(":");
		String host = arr[1];
		properties.add(host); 
		
		String current1 = fi.readLine();
		String arr1[] = current1.split(":");
		String dataBase = arr1[1];
		properties.add(dataBase);

		String current2 = fi.readLine();
		String arr2[] = current2.split(":");
		String user = arr2[1];
		properties.add(user);

		String current3 = fi.readLine();
		String arr3[] = current3.split(":");
		String pass = arr3[1];
		properties.add(pass);

		return properties;
	}
}
