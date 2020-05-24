package StrategyJson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The Class JsonUploader.
 */
//using Strategy Pattern
public class JsonUploader implements FileUploader{

	/** The name 1. */
	private String name1;
	
	/** The name 2. */
	private String name2;
	
	/** The version. */
	private String version;

	/**
	 * Gets the name 1.
	 *
	 * @return the name 1
	 */
	public String getName1() {
		return this.name1;
	}

	/**
	 * Sets the name 1.
	 *
	 * @param name1 the new name 1
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}

	/**
	 * Gets the name 2.
	 *
	 * @return the name 2
	 */
	public String getName2() {
		return this.name2;
	}

	/**
	 * Sets the name 2.
	 *
	 * @param name2 the new name 2
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("                   Version " + version);
		sBuilder.append("\n");
		sBuilder.append("      Desigend & Developed by:");
		sBuilder.append("\n  ");
		sBuilder.append(name1);
		sBuilder.append("  ");
		sBuilder.append(name2);
		
		return sBuilder.toString();
	}

	/**
	 * Upload file.
	 */
	@Override
	public void uploadFile() {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("input.json"));
			JSONObject jsonObject = (JSONObject) obj;
			name1 = (String) jsonObject.get("student_name_1");
			name2 = (String) jsonObject.get("student_name_2");
			version = (String) jsonObject.get("version");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
