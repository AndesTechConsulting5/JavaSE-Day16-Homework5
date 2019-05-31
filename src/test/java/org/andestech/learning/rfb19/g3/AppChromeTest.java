package ru_raif;


import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppChromeTest {


    private WebDriver webDriver;
    private ChromeOptions chromeOptions;

    @BeforeClass
    public void init() {
        System.setProperty("webdriver.chrome.driver", "/Users/sofyashkolnik/chromedriver");
        chromeOptions = new ChromeOptions();

        chromeOptions.setPageLoadStrategy(  PageLoadStrategy.NORMAL );

        webDriver = new ChromeDriver( chromeOptions );

    }


    /**
     * Rigorous Test :-)
     */
    @Test
    public void PositiveLogin() throws InterruptedException {
        //assertTrue( true );
        //webDriver.get("https://google.com/");
        //webDriver.get("https://yandex.ru/");

        webDriver.get("http://andestech.org/learning/rfb18/");



       webDriver.manage().timeouts().pageLoadTimeout( 5, TimeUnit.SECONDS );

        Thread.sleep(2000);

        WebElement element1 = webDriver.findElement(By.partialLinkText("New customer"));

        element1.click();

        Thread.sleep(1000);

        webDriver.findElement(By.id("name")).sendKeys("Петр");
        webDriver.findElement(By.id("sname")).sendKeys("Петров");

        WebElement selectorElement = webDriver.findElement(By.id("group_selector"));

        Select select = new Select(selectorElement);

        select.selectByValue("war");

        webDriver.findElement(By.id("login")).sendKeys("PeterLogin");
        webDriver.findElement(By.id("pass")).sendKeys("P@ssw0rd");
        webDriver.findElement(By.id("submit")).click();
        webDriver.switchTo().alert().accept();

        //WebElement element_ya = webDriver.findElement(By.id("text"));
        //element_ya.sendKeys("11 friends");
        //element_ya.submit();
        //System.out.println( webDriver.findElements( By.cssSelector( "  ")  ));

        Thread.sleep(2000);

        // List<WebElement>  linkList = webDriver.findElements(By.tagName("a"));
        //findElements(By.xpath("//a"));
        //wait_one.until(ExpectedConditions.presenceOfElementLocated( By.name( "q" ) ) );
        //WebElement pnnext = webDriver.findElement(By.id("pnnext"));
        //pnnext.click();

        //webDriver.findElement( By.id("login")).click();

        WebElement element2 = webDriver.findElement(By.partialLinkText("Login"));

        element2.click();

        //webDriver.findElement(By.partialLinkText(""));
        Thread.sleep(3000);

        webDriver.findElement(By.id("login")).sendKeys( "PeterLogin" );
        webDriver.findElement(By.id("pass")).sendKeys( "P@ssw0rd" );
        webDriver.findElement(By.id("lgn")).click();

        Thread.sleep(3000);

        WebElement element3 = webDriver.findElement(By.partialLinkText("Logout"));
        element3.click();

        Thread.sleep(500);


        webDriver.switchTo().alert().accept();

        Thread.sleep(3000);






    }


    @AfterClass
    public void close() {
        webDriver.quit();
    }
}
