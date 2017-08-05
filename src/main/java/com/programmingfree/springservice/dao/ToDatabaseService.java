package com.programmingfree.springservice.dao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.programmingfree.springservice.utility.DBUtility;

public class ToDatabaseService {
	private Connection connection;

	public ToDatabaseService() {
		connection = DBUtility.getConnection();
	}

	public void go() {

		String inline = "";
		{
			try {
				URL url = null;
				url = new URL("https://websiteDomain/json_file.json");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();

				if (responsecode != 200)
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				System.out.println(inline);
				sc.close();

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				JSONParser parse = new JSONParser();

				JSONObject jobj = (JSONObject) parse.parse(inline);
				JSONArray jsonarr_1 = (JSONArray) jobj.get("employee");

				System.out.println("After using Json parser");
				System.out.println(jsonarr_1);
				for (int i = 0; i < jsonarr_1.size(); i++) {
					JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);

					System.out.println("Detail ");
					System.out.println(" id :" + jsonobj_1.get("id"));
					System.out.println("short name :" + jsonobj_1.get("name"));
					PreparedStatement preparedStatement = connection.prepareStatement(
							"insert into menu_items(id,name,salary) "
									+ "values (?,?,?)");
					// Parameters start with 1
					preparedStatement.setLong(1, (long) jsonobj_1.get("id"));
					preparedStatement.setString(2, (String) jsonobj_1.get("name"));
				//	preparedStatement.setDouble(3, jsonobj_1.get("value") == null ? 0.0 : (Double) jsonobj_1.get("value"));
					preparedStatement.setDouble(3, (double) jsonobj_1.get("salary"));
									preparedStatement.executeUpdate();

				}

			} catch (ParseException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}