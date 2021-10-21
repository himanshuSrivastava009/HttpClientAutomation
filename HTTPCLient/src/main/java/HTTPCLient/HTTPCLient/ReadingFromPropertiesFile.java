package HTTPCLient.HTTPCLient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadingFromPropertiesFile {
	static FileInputStream fin = null;
	File file = null;

	public Properties readFromPropertiesFile() {
		String dir = System.getProperty("user.dir");
		String FilePath = dir + "\\client.properties";
		file = new File(FilePath);

		try {
			fin = new FileInputStream(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		System.out.println(fin);

		Properties prop = new Properties();
		try {
			prop.load(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void main(String[] args) {
		ReadingFromPropertiesFile fr = new ReadingFromPropertiesFile();
		Properties prop=fr.readFromPropertiesFile();
		System.out.println(prop.get("filr.login.username"));
		System.out.println(prop.get("filr.login.password"));
	}

}
