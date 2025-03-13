package pageEvents;

import pageObjects.HomePageElements;
import utils.ElementFetch;

// This class contains all the Page Class methods of HomePage
public class HomePageEvents {
    ElementFetch element = new ElementFetch(); // Create an object of ElementFetch

    //Method 1
    public void clickOnCallCenterOption(){
      element.getWebElement("XPATH", HomePageElements.callCenterXpath).click();
     }
    }
