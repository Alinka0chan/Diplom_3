package PageObject;

import api.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private static WebDriver driver;
    //Локатор надписи "Регистрация"
    private By registerLabel = By.xpath("//h2[text()='Регистрация']");
    //Поле "Имя"
    private final By nameInput = By.xpath("//label[text()='Имя']/../input");
    //Поле "Email"
    private final By emailInput = By.xpath("//label[text()='Email']/../input");
    //Поле "Пароль"
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/../input");
    //Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Ошибка "Некорректный пароль"
    private final By invalidPasswordLabel = By.xpath(".//p[text()='Некорректный пароль']");
    //Кнопка "Войти" под формой регистрации
    private final By LoginButton = By.xpath("//a[@href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Проверка загрузки страницы Регистрации")
    public void checkRegisterLabel() {
        driver.findElement(registerLabel).isDisplayed();
    }


    @Step("Регистрация пользователя")
    public void registration(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickRegisterButton();
    }

    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(registerButton));
        driver.findElement(registerButton).click();
    }
    @Step("Проверка видимости сообщения 'Неправильный пароль'")
    public boolean getInvalidPasswordText() {
        return driver.findElement(invalidPasswordLabel).isDisplayed();
    }
    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButtonUnderReg() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(LoginButton));
        driver.findElement(LoginButton).click();
    }
}
