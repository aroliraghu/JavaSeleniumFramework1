package utils;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementFetch {

    public WebElement getWebElement(String identifierType, String identifierValue){
        switch(identifierType){

            case "XPATH":
                return BaseTest.driver.findElement(By.xpath(identifierValue));

            case "CSS":
                return BaseTest.driver.findElement(By.cssSelector(identifierValue));

            case "ID":
                return BaseTest.driver.findElement(By.id(identifierValue));

            case "CLASS":
                return BaseTest.driver.findElement(By.className(identifierValue));

            case "TAGNAME":
                return BaseTest.driver.findElement(By.tagName(identifierValue));

            default:
                return null;
        }
    }


    // Find Elements methods. Return type = List of Web elements
    public List<WebElement> getWebElements(String identifierType, String identifierValue){
        switch(identifierType){

            case "XPATH":
                return BaseTest.driver.findElements(By.xpath(identifierValue));

            case "CSS":
                return BaseTest.driver.findElements(By.cssSelector(identifierValue));

            case "ID":
                return BaseTest.driver.findElements(By.id(identifierValue));

            case "CLASS":
                return BaseTest.driver.findElements(By.className(identifierValue));

            case "TAGNAME":
                return BaseTest.driver.findElements(By.tagName(identifierValue));

            default:
                return null;
        }
    }

}
