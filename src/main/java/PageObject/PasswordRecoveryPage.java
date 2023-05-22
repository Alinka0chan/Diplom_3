package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private static WebDriver driver;

    //Восстановление пароля
    private By resetPasswordButton = By.xpath("//h2[text()='Восстановление пароля']");
    //Локатор кнопки Войти
    private By loginButton = By.xpath("//a[@href='/login']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public boolean waitForLoadPage() {
        return driver.findElement(resetPasswordButton).isDisplayed();
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
