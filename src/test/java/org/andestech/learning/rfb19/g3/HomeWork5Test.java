package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class HomeWork5Test {
    private WebDriver wd = null;
    private ChromeOptions chromeOptions;

    @BeforeClass
    public void initData() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\presentation\\G4\\chromedriver_win32\\chromedriver.exe");

        System.out.println("+++ Class: " + this);
        chromeOptions = new ChromeOptions();

        //chromeOptions.addArguments("--user-data-dir=C:\\Users\\and\\AppData\\Local\\Google\\Chrome\\User Data");
        //chromeOptions.setBinary("E:\\progs\\chrome-win\\chrome.exe");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    }


    @Test
    public void siteRaitingTest() throws InterruptedException {
        wd = new ChromeDriver(chromeOptions);

        Wait<WebDriver> wait2 = new FluentWait<>(wd).withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        wd.get("http://yandex.ru");

        WebElement search = wait2.until(x -> x.findElement(By.id("text")));
        search.sendKeys("java");
        search.submit();

        String cssSelector = "ul>li.serp-item h2>a";

        Map<Integer, String> raiting = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            List<WebElement> elements = wait2.until(x -> x.findElements(By.cssSelector(cssSelector)));

            String[] link = elements.stream()
                    .map(el -> el.getAttribute("href"))
                    .peek(System.out::println).toArray(String[]::new);
            IntStream.range(0, link.length)
                    .filter(index -> link[index].contains("oracle"))
                    .forEach(index -> raiting.put(index, link[index]));

            WebElement next = wd.findElement(By.linkText("дальше"));
            next.click();
        }
        System.out.println();
        System.out.println("**************************RAITING*******************");
        raiting.forEach((k, v) -> System.out.println("place: " + k + " link: " + v));
    }

    @AfterClass
    public void tearDown() {
        if (wd != null) wd.quit();
        System.out.println("--- Class: " + this);
    }

}
