package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 0;
    int retryCount=1;


    // This method retries failed TC execution when TC status Failed
    // This method will be called inside SuiteListener for performing failed Tc re execution
    // This method will be part of transform() method

    @Override
    public boolean retry(ITestResult result) {

        while(count < retryCount){
            count++;
            return true;
        }
        return false;
    }
}
