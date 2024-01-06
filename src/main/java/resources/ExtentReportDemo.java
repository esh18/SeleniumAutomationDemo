package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {
	public static ExtentReports extent;

	public static ExtentReports getReportObject() {
		String t = java.time.LocalTime.now().toString().replaceAll("[^a-zA-Z0-9]", " ");
		String path = System.getProperty("user.dir") + "\\reports\\test reports\\index" + " " + t + ".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("InforeadWeb Automation Report");
		reporter.config().setDocumentTitle("InforeadWeb Automation Test Result");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Devesh Kakde");
		return extent;
	}
}
