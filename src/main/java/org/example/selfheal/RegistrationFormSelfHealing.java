package org.example.selfheal;


import org.example.App;
import org.example.Discover.AutoDiscover;
import org.example.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;


public class RegistrationFormSelfHealing {

    private WebDriver driver;

    public void goToPage()
    {
        driver=App.getDriver();
        driver.get("file:///C:/Users/loveneetkamboj/AppData/Local/Temp/Temp1_selfheal-master.zip/selfheal-master/src/test/resources/html/bootstrap1.html");
    }

    public void enterFirstName(String firstName) throws Exception {
        enterText("input","id","validationCustom", firstName);
    }


    private void enterText(String tagName,String attributes,String value, String text) throws Exception {
        try {
            driver.findElement(By.xpath("//input[@id='validationCustom']")).clear();
            driver.findElement(By.xpath("//input[@id='validationCustom']")).sendKeys(text);
        } catch (Exception e) {
            System.out.println(e);
            driver.findElement(By.cssSelector(AutoDiscover.getLocator(getInputCharacteristics(tagName,attributes,value)))).clear();
            driver.findElement(By.cssSelector(AutoDiscover.getLocator(getInputCharacteristics(tagName,attributes,value)))).sendKeys(text);

        }
    }


    public String getTitle() {
        return driver.getTitle();
    }


    private static Map<String, String> getInputCharacteristics(String tagName, String attributes,String value) {
        Map<String, String> characteristics = new HashMap<>();
        characteristics.put("TagName",tagName);
        characteristics.put("Attributes", attributes);
        characteristics.put("AttributeMatcher",attributes);
        characteristics.put("value",value);
        return characteristics;
    }
}
