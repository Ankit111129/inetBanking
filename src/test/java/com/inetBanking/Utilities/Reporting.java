package com.inetBanking.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
        String reportName = "Test-Report-" + timeStamp + ".html";

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + reportName); // Specify location
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "pavan");

        htmlReporter.config().setDocumentTitle("Inetbanking Test Project"); // Title of report
        htmlReporter.config().setReportName("Functional Test Report"); // Name of report
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Location of chart
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        logger = extent.createTest(tr.getName()); // Create new entry in the report
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger = extent.createTest(tr.getName()); // Create new entry in the report
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";
        File file = new File(screenshotPath);

        if (file.exists()) {
            try {
                logger.fail("Screenshot is below: " + logger.addScreenCaptureFromPath(screenshotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
