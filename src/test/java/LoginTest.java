import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.PasswordRecoveryPage;
import PageObject.RegisterPage;
import api.client.UserClient;
import api.model.User;
import api.util.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
    private User user;
    LoginPage loginPage;
    private WebDriver driver;

    private UserClient userClient;
    private MainPage mainPage;
    private String userAccessToken;
    private RegisterPage registerPage;
    private PasswordRecoveryPage passwordRecoveryPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        driver.get("https://stellarburgers.nomoreparties.site/");
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        userClient = new UserClient();
        user = UserGenerator.getRandom(6);
        ValidatableResponse registerResponse = userClient.register(user);
        userAccessToken = registerResponse.extract().path("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице.")
    public void checkLoginOnMainPage() {
    mainPage.waitForLoadLogoButton();
    mainPage.clickLoginButton();
    loginPage.checkHeaderLogin();
    loginPage.loginUser(user);
    Assert.assertTrue(mainPage.checkLogoButton());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет».")
    public void checkLoginByPersonalAccountButton() {
        mainPage.waitForLoadLogoButton();
        mainPage.clickProfileButton();
        loginPage.checkHeaderLogin();
        loginPage.loginUser(user);
        Assert.assertTrue(mainPage.checkLogoButton());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации.")
    public void checkLoginOnRegistrationPage() {
        mainPage.waitForLoadLogoButton();
        mainPage.clickLoginButton();
        loginPage.checkHeaderLogin();
        loginPage.clickRegisterButton();
        registerPage.checkRegisterLabel();
        registerPage.clickLoginButtonUnderReg();
        loginPage.checkHeaderLogin();
        loginPage.loginUser(user);
        Assert.assertTrue(mainPage.checkLogoButton());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля.")
    public void checkLoginOnPasswordRecoveryPage() {
        mainPage.waitForLoadLogoButton();
        mainPage.clickLoginButton();
        loginPage.checkHeaderLogin();
        loginPage.clickResetPasswordButton();
        passwordRecoveryPage.waitForLoadPage();
        passwordRecoveryPage.clickLoginButton();
        loginPage.checkHeaderLogin();
        loginPage.loginUser(user);
        Assert.assertTrue(mainPage.checkLogoButton());
    }

    @After
    public void cleanUp() {
        driver.quit();
        if (userAccessToken != null) {
            userClient.delete(userAccessToken);
        }
    }
}
