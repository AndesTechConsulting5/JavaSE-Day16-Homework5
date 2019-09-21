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
import java.util.List;

import static org.testng.Assert.assertTrue;

public class HomeWork5Test {
    private WebDriver wd = null;
    private ChromeOptions chromeOptions;

    private final String searchEngine = "http://yandex.ru";
    private final String queryText = "Новости";
    private final String urlToFind = "lenta.ru";

    private String separator() {
        return String.format("%30s", "").replace(' ', '-');
    }

    @BeforeClass
    public void initData() {
        System.setProperty("webdriver.chrome.driver",
                "E:\\SeleniumDrivers\\chromedriver.exe");

        System.out.println("+++ Class: " + this);
        chromeOptions = new ChromeOptions();

        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    }

    @Test
    public void siteRaitingTest() {
        wd = new ChromeDriver(chromeOptions);

        Wait<WebDriver> wait = new FluentWait<>(wd).withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

        wd.get(searchEngine);

        WebElement weSearchInput = wait.until(x -> x.findElement(By.cssSelector("input#text")));
        weSearchInput.sendKeys(queryText);

        WebElement weSearchButton = wait.until(x -> x.findElement(By.cssSelector("div.search2__button > button")));
        weSearchButton.click();

        int foundPlace = 0;
        int i = 1;
        int maxResults = 100;
        while (foundPlace == 0 && i < maxResults)
        {
            List<WebElement> searchResults = wd.findElements(By.cssSelector("li.serp-item"));

            for(WebElement sRes : searchResults)
            {
                try {
                    WebElement site = sRes.findElement(By.cssSelector("div.organic__path b"));
                    String siteName = site.getAttribute("innerText");

                    // парсим строку на соответствие домену сайта, пропуская вставки продуктов яндекса (Яндекс.Новсти, Яндекс.Район)
                    if (!siteName.matches("^([\\dA-Za-zА-Яа-я.-]+)\\.([a-zа-я.]{2,6})$"))
                        continue;

                    WebElement link = sRes.findElement(By.cssSelector("h2 > a"));
                    String url = link.getAttribute("href");

                    // пропускаем рекламу, замаскированную под ссылки
                    if (url.startsWith("http://yabs.yandex.ru"))
                        continue;

                    // выводим строку результата поиска
                    System.out.println(i + " : " + siteName + " " + url);

                    if (siteName.toLowerCase().equals(urlToFind.toLowerCase())) {
                        foundPlace = i;
                    }

                    i++;
                } catch (NoSuchElementException e) {
                    // пропускаем еще один вид рекламы яндекса
                }
            }
            WebElement nexPage = wd.findElement(By.cssSelector("a.pager__item_kind_next"));
            nexPage.click();
        }

        System.out.println(separator());
        if (foundPlace > 0) {
            System.out.println("Искомый сайт на " + foundPlace + " месте.");
        }
        else {
            System.out.println("Искомый сайт не найден среди первых " + maxResults + " результатов.");
        }
        System.out.println(separator());

        assertTrue( foundPlace > 0 );
    }


    @AfterClass
    public void tearDown() {
        if (wd != null) wd.quit();
        System.out.println("--- Class: " + this);
    }

}
