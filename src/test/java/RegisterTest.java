import PageObject.LoginPage;
import PageObject.RegisterPage;
import api.client.UserClient;
import api.model.User;
import api.util.UserCredentials;
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

import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterTest {
    private WebDriver driver;
    User user;
    private UserClient userClient;
    private String userAccessToken;
    RegisterPage registerPage;
    LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        userClient = new UserClient();
    }


    @Test
    @DisplayName("Успешная регистрация нового пользователя с паролем 6 символов.")
    public void registerNewUserSuccessful() {
        user = UserGenerator.getRandom(6);
        registerPage.registration(user);
        loginPage.waitForLoadHeader();
        Assert.assertTrue(loginPage.checkHeaderLogin());
        ValidatableResponse loginResponse = userClient.login(UserCredentials.from(user));
        loginResponse
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true));
        userAccessToken = loginResponse.extract().path("accessToken");
    }

    @Test
    @DisplayName("Проверка входа с некорректным паролем")
    public void checkingRegisterWithoutBadPasswordLogin() {
        user = UserGenerator.getRandom(5);
        registerPage.registration(user);
        Assert.assertTrue(registerPage.getInvalidPasswordText());
    }

    @After
    public void cleanUp() {
        driver.quit();
        if (userAccessToken != null) {
            userClient.delete(userAccessToken);
        }
    }
}
