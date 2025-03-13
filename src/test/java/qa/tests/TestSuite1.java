package qa.tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pageEvents.CallCenterPageEvents;
import pageEvents.HomePageEvents;

public class TestSuite1 extends BaseTest { // Extending Base Test

    @Test
    public void testCase1(){
    //Step1
    HomePageEvents homePageEvents = new HomePageEvents();
    homePageEvents.clickOnCallCenterOption();

    //Step2
    CallCenterPageEvents callCenterPageEvents = new CallCenterPageEvents();
    callCenterPageEvents.verifyDescription();

}
}
