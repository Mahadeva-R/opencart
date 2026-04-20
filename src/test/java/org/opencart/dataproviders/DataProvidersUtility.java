package org.opencart.utilities;

import org.testng.annotations.DataProvider;
import java.io.IOException;
import static org.opencart.constants.TestConstant.SHEET_LOGIN_CREDENTIALS;

public class DataProvidersUtility {

    @DataProvider(name = "LoginData")
    public static Object[][] loginDataProvider() throws IOException {
        String path = "src/test/resources/LoginCredentialsTestData.xlsx";
        ExcelHelper excelHelper = new ExcelHelper(path);
        int rowCount = excelHelper.getRowCount(SHEET_LOGIN_CREDENTIALS);
        int cellCount = excelHelper.getCellCount(SHEET_LOGIN_CREDENTIALS, 1);

        Object[][] loginData = new String[rowCount][cellCount];

        for (int row = 1; row <= rowCount; row++) {
            for (int cell = 0; cell < cellCount; cell++) {
                loginData[row - 1][cell] = excelHelper.getCellData(SHEET_LOGIN_CREDENTIALS, row, cell);
            }
        }

        excelHelper.close();
        return loginData;
    }

}
