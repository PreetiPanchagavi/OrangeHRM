package orange.crm.orangestepdefination;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import orange.crm.Listeners.ExtentReportListener;
import orange.crm.Utility.BrowserUtility;
import orange.crm.Utility.EzxcelHandlerDemo;
import orange.crm.Utility.OrangeCRMPropertiesFileReader;
import orange.crm.Utility.WiniumHandler;

public class OrangeDriver extends ExtentReportListener{
    final static Logger logger = Logger.getLogger(OrangeDriver.class);

    WiniumHandler object = new WiniumHandler();
	OrangeCRMPropertiesFileReader crmProperties = new OrangeCRMPropertiesFileReader();
    Properties crmproperties = crmProperties.getProperty();
    
	    public static String sheetName;
	    public static String filePath;
	    public static WebDriver webDriver;
	    public static String run;
	    public static String testcaseId;
	    public static String scenarioNames;
	    public static String stepDescription;
	    
	    SoftAssert soft = new SoftAssert();

	    @Before()
	    public void before(Scenario scenario) throws Exception {
	        String scenarioName = scenario.getName();	       
	        scenarioNames = scenarioName;
	        System.out.println("scenarioName===>"  +scenarioName);
	        Map<String, String> TestDataInMap = EzxcelHandlerDemo.getTestDataInMap(".\\ORANGE_CRM\\Orange\\DriverFile\\TestSet.xlsx",
	                "TestSet", scenarioName);
	        String str = TestDataInMap.get("Run");
	     
	        if (str.contains("No")) {
	            logger.info("SCENARIO SKIPPED ####" + scenarioName);
	            throw new SkipException(scenario.getName());
	        }
	        setAppData(scenarioName);
	        logger.info("SCENARIO STARTED ####" + scenarioName);
	    }

	    public static Map<String, String> appTestData = new HashMap<String, String>();

	    private void setAppData(String scenarioName) throws Exception {
	        appTestData = EzxcelHandlerDemo.getTestDataInMap(".\\ORANGE_CRM\\Orange\\DriverFile\\ApplicationData.xlsx", "AppData",
	                scenarioName);
	        logger.info("AppTestData:" + appTestData);

	    }
	    
	 @When("Configure my data sheet path {string} and spread sheet name {string}")
	    public void configureMyDataSheet(String filePath, String sheetName) {
	        this.filePath = filePath;
	        this.sheetName = sheetName;

	    }
	 
	 @Given("Launch browser and enter orange crm url for ScenarioId {string}")
	    public void launchWebApplication(String tcId) throws Throwable {
	  	        ExtentTest logInfo = null;
	        try {
	            logInfo = getNode(tcId, ".\\ORANGE_CRM\\Orange\\DriverFile\\TestSet.xlsx", "open_browser_with_URL");
	            webDriver = BrowserUtility.launchBrowser(webDriver, crmproperties.getProperty("browserName"),
	            		crmproperties.getProperty("OrangeCRMUrl"));
	            logInfo.pass("Opened IE browser and entered url");
	            logInfo.addScreenCaptureFromPath(captureScreenShot(webDriver));
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }

	    }
	 
	   public ExtentTest getNode(String rowId, String testsetPath, String nodeText) throws Exception, ClassNotFoundException {
	        ExtentTest logInfo;
	        Map<String, String> TestDataInMap = EzxcelHandlerDemo.getTestDataInMap(testsetPath, "TestSet", rowId);
	        run = TestDataInMap.get("Run");
	        testcaseId = TestDataInMap.get("TestCaseId");
	        String TestCaseDescription = TestDataInMap.get("TestCaseDescription");
	        test = extent.createTest((Class<? extends IGherkinFormatterModel>) Feature.class, testcaseId);
	        test = test.createNode((Class<? extends IGherkinFormatterModel>) Scenario.class, TestCaseDescription);
	        logInfo = test.createNode(new GherkinKeyword("Given"), nodeText);
	        return logInfo;
	    }
	   @Then("Close the OrangeCRM Web Application")
	    public void exitWebDriver() throws Exception {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Close the Application", webDriver);
	            webDriver.quit();
	        } catch (AssertionError | Exception e) {
	            testStepHandleNoDriver("FAIL", logInfo, e);
	        }

	    }
	   @And("The Below Step Description is {string}")
	    public void logger(String stepDescription) {
	        this.stepDescription = stepDescription;
	    }
	   @Then("Enter value {string} in {string} web")
	    public void enterValueWeb(String value, String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Enter value", webDriver);
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.elementToBeClickable(elt));
	            elt1.clear();
	            elt1.sendKeys(appTestData.get(value));
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }
	   @Then("Click on element {string}")
	    public void clickOnElement(String locator) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Click On", webDriver);
	            
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.elementToBeClickable(elt));
	            elt1.click();
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }

	   @Then("Get Text from element {string} {string}")
	    public void getTextWebelement(String locator, String value) throws MalformedURLException {
	        ExtentTest logInfo = null;
	        try {
	            logInfo = object.logsHandlerWeb(stepDescription, "Get Text", webDriver);
	            WebElement elt = object.locatorsfetch(locator, webDriver, filePath, sheetName);
	            WebDriverWait wait = new WebDriverWait(webDriver, 30);
	            WebElement elt1 = wait.until(ExpectedConditions.visibilityOf(elt));
	            System.out.println("Get Text===>" +elt1.getText());
	            soft.assertEquals(elt1.getText(), appTestData.get("value"));
	            soft.assertAll();
	        } catch (AssertionError | Exception e) {
	            testStepHandle("FAIL", webDriver, logInfo, e);
	        }
	    }

}
