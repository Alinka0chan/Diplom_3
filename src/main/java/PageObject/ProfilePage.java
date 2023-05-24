package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private static WebDriver driver;

    // Вкладка "Профиль"
    private final By profileTabButton = By.xpath(".//a[text()='Профиль']");
    //Вкладка "Выход"
    private final By exitTabButton = By.xpath(".//button[text()='Выход']");
    //Конструктор
    private By constructorButton = By.xpath("//p[text()='Конструктор']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Проверка отображения вкладки Профиль")
    public boolean checkProfileTabButton() {
        return driver.findElement(profileTabButton).isDisplayed();
    }
    @Step("Ожидание загрузки вкладки Профиль")
    public void waitForProfileTabButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileTabButton));
    }
    @Step("Клик по кнопке 'Выход'")
    public void clickExitButton() {
        driver.findElement(exitTabButton).click();
    }
    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
}
