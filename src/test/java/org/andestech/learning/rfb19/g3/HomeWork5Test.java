package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class HomeWork5Test
{
    private WebDriver wd = null;
    private ChromeOptions chromeOptions;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\presentation\\IdeaProjects\\ChromeDriver\\chromedriver_74.0.3729.6.exe");

    System.out.println("+++ Class: " + this);
    chromeOptions = new ChromeOptions();

    //chromeOptions.addArguments("--user-data-dir=C:\\Users\\and\\AppData\\Local\\Google\\Chrome\\User Data");
    //chromeOptions.setBinary("E:\\progs\\chrome-win\\chrome.exe");
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    }


    @Test
    public void siteRaitingTest() throws InterruptedException
    {
        wd = new ChromeDriver(chromeOptions);

        Wait<WebDriver> wait2 = new FluentWait<>(wd).withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        wd.get("http://yandex.ru");

        /**
         *  Тут нужен Ваш код!
         *
         */

        WebElement searchField = wait2.until( x -> x.findElement(By.cssSelector("#text")));
        WebElement searchButton = wait2.until( x -> x.findElement(By.cssSelector(".suggest2-form__button")));

        searchField.sendKeys("Auto tests");
        searchButton.submit();

        List<WebElement> searchResalts = wd.findElements(By.cssSelector("div.path.path_show-https.organic__path > a"));

        int i = 0;
        for(WebElement element :  searchResalts)
        {
            System.out.println(i++ + " : " + element.getAttribute("href"));
        }


    }



    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
