import PageObject.MainPage;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ConstructorSectionTest {
    private WebDriver driver;
    private MainPage mainPage;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Начинки'.")
    public void checkActiveBunTest() {
        mainPage.clickFillingsButton();
        Assert.assertTrue(mainPage.checkActiveFillingsButton());
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Соусы'.")
    public void checkActiveSaucesTest() {
        mainPage.clickSaucesButton();
        Assert.assertTrue(mainPage.checkActiveSaucesButton());
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Булки'.")
    public void checkActiveBunsTest() {
        mainPage.clickSaucesButton();
        mainPage.clickBunsButton();
        Assert.assertTrue(mainPage.checkActiveBunsButton());
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
