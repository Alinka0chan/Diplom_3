import PageObject.*;
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

public class PersonalAccountTest {
    private User user;
    LoginPage loginPage;
    private WebDriver driver;

    private UserClient userClient;
    private MainPage mainPage;
    private String userAccessToken;
    private ProfilePage profilePage;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        driver.get("https://stellarburgers.nomoreparties.site/");
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        userClient = new UserClient();
        user = UserGenerator.getRandom(6);
        ValidatableResponse registerResponse = userClient.register(user);
        userAccessToken = registerResponse.extract().path("accessToken");
        mainPage.waitForLoadLogoButton();
        mainPage.clickLoginButton();
        loginPage.checkHeaderLogin();
        loginPage.loginUser(user);
    }
    @Test
    @DisplayName("Проверка перехода по клику на 'Личный кабинет'.")
    public void checkPersonalAccountPageTest(){
        mainPage.clickProfileButton();
        profilePage.waitForProfileTabButton();
        Assert.assertTrue(profilePage.checkProfileTabButton());

    }
    @After
    public void cleanUp() {
        driver.quit();
        if (userAccessToken != null) {
            userClient.delete(userAccessToken);
        }
    }
}
