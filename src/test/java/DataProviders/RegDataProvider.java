package DataProviders;

import org.testng.annotations.DataProvider;
import utilities.ExcelUtility;

import java.io.IOException;

public class RegDataProvider {

    @DataProvider(name = "RegistrationData")
    public String[][] getRegData() throws IOException {

        String path = "C:\\Users\\2494470\\OneDrive - Cognizant\\Desktop\\Testing Project\\RideOnDemandTest\\src\\test\\java\\TestData\\RegistrationData.xlsx";
        ExcelUtility xlUtility = new ExcelUtility(path);

        int totalRows = xlUtility.getRowCount("Sheet1");
        int totalCols = xlUtility.getCellCount("Sheet1",1);

        String[][] registrationData = new String[totalRows][totalCols];

        for(int i=1; i<=totalRows; i++){
            for(int j=0; j<totalCols; j++){
                registrationData[i-1][j] = xlUtility.getCellData("Sheet1", i, j);
            }
        }

        return registrationData;
    }
}
