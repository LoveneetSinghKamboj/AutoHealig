package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.workflow.RegistrationSelfHealingPageObjectsWorkflow;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App
{
    public static WebDriver getDriver() {
        return driver;
    }

    private static WebDriver driver;

    public static void main( String[] args ) throws Exception {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        RegistrationSelfHealingPageObjectsWorkflow registrationSelfHealingPageObjectsWorkflow =new RegistrationSelfHealingPageObjectsWorkflow();
        registrationSelfHealingPageObjectsWorkflow.completeRegistration("loveneet");
        Thread.sleep(20000);
        driver.close();
    }

}
