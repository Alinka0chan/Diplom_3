package PageObject;

import api.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static WebDriver driver;
    //Заголовок "Вход"
    private final By headerLogin = By.xpath(".//h2[text()='Вход']");
    //Поле "Email"
    private final By inputEmail = By.xpath(".//label[text()='Email']/../input");
    //Поле "Пароль"
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/../input");
    //Кнопка "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    //Кнопка "Восстановить пароль"
    private final By resetPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    //Кнопка "Личный кабинет"
    private final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    //Локатор кнопки "Зарегистрироваться"
    private By registerButtonLocator = By.xpath("//a[@href='/register']");

    public LoginPage(WebDriver driver) {
        LoginPage.driver = driver;
    }

    public void waitForLoadHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(headerLogin));
    }

    public String getLoginTextFromHeader() {
        return driver.findElement(headerLogin).getText();
    }

    public boolean checkHeaderLogin() {
        return driver.findElement(headerLogin).isDisplayed();
    }

    public void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }
    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    @Step("Клик на кнопку 'Личный Кабинет'")
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }
    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }
    @Step("Клик на кнопку 'Восстановить пароль'")
    public void clickResetPasswordButton() {
        driver.findElement(resetPasswordButton).click();
    }

    @Step("Авторизация пользователя")
    public void loginUser(User user) {
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickLoginButton();
        clickProfileButton();
    }
}
