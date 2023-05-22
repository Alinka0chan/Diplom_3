package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static WebDriver driver;
    //Логотип
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    //Конструктор
    private By constructorButton = By.linkText("Конструктор");
    //Личный кабинет
    private By profileButton = By.linkText("Личный Кабинет");
    //Кнопка раздела "Булки" - активная
    private By activeBunButton = By.xpath(".//div[(contains(span/text(),'Булки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    //Кнопка раздела "Булки" - неактивная(не выделенная)
    private By inactiveBunButton = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    //Кнопка раздела "Соусы" - активная
    private By activeSauceButton = By.xpath(".//div[(contains(span/text(),'Соусы')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    //Кнопка раздела "Соусы" - неактивная(не выделенная)
    private By inactiveSauceButton = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    //Кнопка раздела "Начинки" - активная
    private By activeFillingButton = By.xpath(".//div[(contains(span/text(),'Начинки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    //Кнопка раздела "Начинки" - неактивная (не выделенная)
    private By inactiveFillingButton = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");
    //Войти в аккаунт
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    //Локатор кнопки "Оформить заказ"
    private By orderButtonLocator = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка загрузки главной страницы.")
    public boolean checkLogoButton() {
        return driver.findElement(logoButton).isDisplayed();
    }

    @Step("Ожидание загрузки главной страницы.")
    public void waitForLoadLogoButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(logoButton));
    }

    @Step("Клик по кнопке 'Войти в аккаунт'.")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке 'Личный Кабинет'.")
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Клик по кнопке 'Булки'.")
    public void clickBunsButton() {
        driver.findElement(inactiveBunButton).click();
    }

    @Step("Клик по кнопке 'Соусы'.")
    public void clickSaucesButton() {
        driver.findElement(inactiveSauceButton).click();
    }

    @Step("Клик по кнопке 'Начинки'.")
    public void clickFillingsButton() {
        driver.findElement(inactiveFillingButton).click();
    }
    @Step("Проверка активности кнопки 'Начинки'.")
    public boolean checkActiveFillingsButton() {
        return driver.findElement(activeFillingButton).isDisplayed();
    }
    @Step("Проверка активности кнопки 'Соусы'.")
    public boolean checkActiveSaucesButton() {
        return driver.findElement(activeSauceButton).isDisplayed();
    }
    @Step("Проверка активности кнопки 'Булки'.")
    public boolean checkActiveBunsButton() {
        return driver.findElement(activeBunButton).isDisplayed();
    }

}
