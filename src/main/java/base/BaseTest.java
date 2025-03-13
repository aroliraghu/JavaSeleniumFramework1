package base;
// https://www.youtube.com/watch?v=L7P5fqW2kck -- Reference link
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.IConstants;

import java.lang.reflect.Method;
import java.time.Duration;


public class BaseTest {



    public static WebDriver driver;
    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;


    @BeforeTest
    public void beforeTest(){
        // Initializing Extent Report here

        // Note: 3 main Classes are used - ExtentSparkReporter , ExtentReports, ExtentTest
        extentSparkReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport.html"); // Setting Path for file storage
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        //configuration items to change the look and feel
        extentSparkReporter.config().setDocumentTitle("Simple Automation Report"); // Config method can be used to customize the Report
        extentSparkReporter.config().setReportName("Test Report");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extentReports.setSystemInfo("Hostname", "RaghuMachine");
        extentReports.setSystemInfo("Username", "Raghavendra Aroli");

    }

    @BeforeMethod
    @Parameters("browser")
    // Note: the 'browser' parameter value will be passed from TestNG.xml file
    // Note: Method is added from java.lang.reflect
    public void beforeMethod(String browser, Method testMethod){
        extentTest = extentReports.createTest(testMethod.getName()); // This adds logs for each Method execution
        setupDriver(browser); // check below in this file
        driver.get(IConstants.url); // IConstant is part of Utils package and is used to store all constant variables
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterTest
    public void afterTest(){
        extentReports.flush();
    }

    @AfterMethod
    public void  afterMethod(ITestResult result){

        // ITestResult is from TestNG
        // This method logs result of each method in different color based on the method result

        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ "- Test Case FAILED", ExtentColor.RED));
                    extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+ "- Test Case Failed", ExtentColor.RED));
        }
        else if(result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ "- Test Case SKIPPED", ExtentColor.ORANGE));
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ "- Test Case PASS", ExtentColor.GREEN));
        }
        driver.quit();
    }


    // This method decides on driver object creation based on browser value sent ex: chrome/firefox/edge
    // This method will be called inside @BeforeMethod
    public void setupDriver(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();}
        else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();}
        else if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();}
    }
}
