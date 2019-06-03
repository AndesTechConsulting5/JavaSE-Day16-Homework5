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
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class HomeWork5Test
{
    private WebDriver wd = null;
    private ChromeOptions chromeOptions;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "E:\\selenium_drivers\\chromedriver.exe");

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
        WebElement webElement = wd.findElement(By.name("text"));

        String searchPrase = "java tester";
        String ratingAddress = "java";

        webElement.sendKeys(searchPrase);
        webElement.submit();

        HashMap<Integer,String> rating =  new HashMap<>();

        String resultDataSelector = "ul > li.serp-item h2>a";

        int i = 0;
        List<WebElement> datas = wd.findElements(By.cssSelector(resultDataSelector));
        for(WebElement webElement1: datas)
        {
            String link =  webElement1.getAttribute("href");
            System.out.println(i++ + " :" + link);
            if(link.toLowerCase().indexOf(ratingAddress) != -1) rating.put(i,link);

        }

        int N = 6;

        for(int k = 0; k<N; k++)
        {

            WebElement pnnext = wd.findElement(By.linkText("дальше"));
            pnnext.click();

            datas.clear();
            datas = wd.findElements(By.cssSelector(resultDataSelector));
            for(WebElement webElement1 :  datas)
            {
                String link =  webElement1.getAttribute("href");
                System.out.println(i++ + " :" + link);
                if(link.toLowerCase().indexOf(ratingAddress) != -1) rating.put(i,link);
            }

        }

        System.out.println("---------------------rating-----------------------------------");
        System.out.println("Search prase: " + searchPrase + ", rating for web with text:" + ratingAddress);
        for(int key: rating.keySet())
        {
            System.out.println(key + " : " + rating.get(key));
        }
    }



    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
