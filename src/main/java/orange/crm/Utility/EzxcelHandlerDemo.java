
package orange.crm.Utility;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import orange.crm.orangestepdefination.OrangeDriver;

public class EzxcelHandlerDemo {
 
    final static Logger logger = Logger.getLogger(OrangeDriver.class);
    TestDataHandler testdata = new TestDataHandler();

    public static Map<String, String> getTestDataInMap(String testDataFilePath, String sheetName, String scenarioName) throws Exception {
    	String testid = scenarioName.replaceAll("\\.", "");
    	System.out.println("testid==> " +testid);
        Map<String, String> TestDataInMap = new TreeMap<String, String>();
        String query = null;
        query = String.format("SELECT * FROM %s WHERE testcaseId='%s'", sheetName, testid);
        Fillo fillo = new Fillo();
        Connection conn = null;
        Recordset recordset = null;
        try {
            conn = fillo.getConnection(testDataFilePath);
            recordset = conn.executeQuery(query);
            // recordset=((com.codoid.products.fillo.Connection) conn).executeQuery(query);
            while (recordset.next()) {
                for (String field : recordset.getFieldNames()) {
                    TestDataInMap.put(field, recordset.getField(field));
                }
            }
        } catch (Exception e) {
            if (testDataFilePath.contains("TestSet.xlsx")) {
                logger.error("Test scenario not found for " + testid + " in sheet " + testDataFilePath);
            } else {
                logger.error("Test data not found for " + testid + " in sheet " + testDataFilePath);
            }

            // throw new Exception("Test data cannot find for " + testid + " in sheet " + testDataFile);
        }
        conn.close();
        return TestDataInMap;
    }

    public static void UpdateTestResultsToExcel(String testDataFilePath, String sheetName, String tcStatus, String testCaseId) {
        Connection conn = null;
        Fillo fillo = new Fillo();
        try {
            conn = fillo.getConnection(testDataFilePath);
            String query = String.format("UPDATE %s SET TestCaseStatus='%s' where TestCaseID='%s'", sheetName, tcStatus, testCaseId);
            conn.executeUpdate(query);
        } catch (FilloException e) {
            e.printStackTrace();
        }
    }

}
