package pageEvents;

import org.testng.Assert;
import pageObjects.CallCenterPageElements;
import pageObjects.HomePageElements;
import utils.ElementFetch;

// This class contains all the Page Class methods of HomePage
public class CallCenterPageEvents {
    ElementFetch element = new ElementFetch(); // Create an object of ElementFetch
    String expectedText = "Call center CRM solutions by Free CRM automate all of your inbound and outbound calling needs in 90 countries worldwide. Get a virtual phone number and start creating your call center features straight out of the gate. ";

    //Method 1
    public void verifyDescription(){
      String actualText = element.getWebElement("XPATH", CallCenterPageElements.callCenterDescXpath).getText();
      Assert.assertEquals(actualText, expectedText, "Actual and expected text do not match each other");
     }
    }
