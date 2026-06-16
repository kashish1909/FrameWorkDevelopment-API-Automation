package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
/*
ITestContext is an interface in TestNG that provides information about the current test execution context and allows sharing data between test methods using attributes. It is commonly used to store values such as tokens or IDs that need to be used across multiple tests.
ITestListener is an interface in TestNG that allows us to listen to test execution events such as test start, success, failure, or skip. It is used to perform custom actions like logging, capturing screenshots, or generating reports during the test lifecycle.
ITestResult is an interface in TestNG that provides information about the execution result of a test method, such as its status (pass, fail, or skip), test name, exception details, and execution time. It is commonly used in listeners to perform actions like logging or capturing screenshots on test failure
In ExtentReports, createTest() creates a new test entry in the report using the test name from ITestResult. createNode() adds a sub-step under the test. assignCategory() assigns TestNG groups to the test for categorization. log(Status.FAIL, ...) records the failure status and logs the exception message obtained from ITestResult.
 */

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter extentSparkReporter; //this is for UI of reports
    public ExtentReports extentReports; //for common info about user
    public ExtentTest extentTest; //fill the report with test info
    String reportName;

    public void onStart(ITestContext context){

        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName="Test_Report"+timeStamp+".html";

        extentSparkReporter=new ExtentSparkReporter(".\\reports\\"+reportName);

        extentSparkReporter.config().setDocumentTitle("RestAssuredProjectFramework");
        extentSparkReporter.config().setReportName("Users API Report");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports=new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("Application","User API");
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("User name",System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("User","Kashish");
    }

    public void onTestStart(ITestResult result){
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
    }

    public void onTestSuccess(ITestResult result){
        extentTest.log(Status.PASS,"Test Passed");
    }

    public void onTestFailure(ITestResult result){
        extentTest.log(Status.FAIL,"Test Failed");

        if(result.getThrowable()!=null){
            extentTest.log(Status.FAIL,result.getThrowable().getMessage());
        }
    }

    public void onTestSkipped(ITestResult result){
        extentTest.log(Status.SKIP,"Test Skipped");

        if(result.getThrowable()!=null){
            extentTest.log(Status.SKIP,result.getThrowable().getMessage());
        }
    }

    public void onFinish(ITestContext context){
        extentReports.flush();
    }
}