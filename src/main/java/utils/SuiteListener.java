package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import base.BaseTest;

import org.openqa.selenium.OutputType;
import org.testng.annotations.ITestAnnotation;
import org.openqa.selenium.TakesScreenshot;


public class SuiteListener implements ITestListener, IAnnotationTransformer {
    // This Class - SuiteListener performs 2 items -> Re-try TC execution of failed TC & Capture Screenshot for failed TC
    // Note: ITestListener Interface has onTestFailure method. We have to implement this method to run the failed TC


    public void onTestFailure(ITestResult result) {
        // Code to capture screenshot
        String filename = System.getProperty("user.dir")+ File.separator+"screenshots"+File.separator+result.getMethod().getMethodName();
        File sourceFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(filename+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method that performs Failed TC req execution
    // This method is part of IAnnotationTransformer Interface
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class); // RetryAnalyzer Class that is created inside utils package
    }




}
