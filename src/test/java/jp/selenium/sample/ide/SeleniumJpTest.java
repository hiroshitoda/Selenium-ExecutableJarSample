package jp.selenium.sample.ide;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumJpTest
{
    private static WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception
    {
        if(driver == null)
        {
            driver = new FirefoxDriver();
        }
        baseUrl = "http://www.selenium.jp/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSample() throws Exception
    {
        driver.get(baseUrl + "/");
        driver.findElement(By.linkText("Selenium / Appium")).click();
        driver.findElement(By.linkText("イベントレポート")).click();
        assertEquals("イベントレポート", driver.findElement(By.id("sites-page-title")).getText());
    }

    @After
    public void tearDown() throws Exception
    {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString))
        {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by)
    {
        try
        {
            driver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    private boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e)
        {
            return false;
        }
    }

    private String closeAlertAndGetItsText()
    {
        try
        {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert)
            {
                alert.accept();
            }
            else
            {
                alert.dismiss();
            }
            return alertText;
        }
        finally
        {
            acceptNextAlert = true;
        }
    }

    public static void main(String[] args)
    {
        String browserName;

        if(args.length <= 0)
        {
            browserName = "firefox";
        }
        else
        {
            browserName = args[0].toLowerCase();
        }

        if(browserName.equals("ie"))
        {
            driver = new InternetExplorerDriver();
        }
        else if(browserName.equals("chrome"))
        {
            driver = new ChromeDriver();
        }
        else
        {
            driver = new FirefoxDriver();
        }

        JUnitCore.main(SeleniumJpTest.class.getName());
    }
}
