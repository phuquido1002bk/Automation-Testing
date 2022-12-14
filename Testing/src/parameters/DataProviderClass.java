package parameters;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	private ReadFromExcel data = null;
	private String filename = "data.xlsx";

	@DataProvider(name = "getData")
	public String[][] getDataFromFile(Method name) throws Exception {
		data = new ReadFromExcel(filename);
		if (name.getName().equalsIgnoreCase("ChangePassword")) {
			return data.ReadData("ChangePass");
		}
		return data.ReadData("ResetPass");
	}

	@DataProvider(name = "getFile")
	public String[][] getFile() throws Exception {
		data = new ReadFromExcel(filename);
		return data.ReadData("FileName");
	}
}
