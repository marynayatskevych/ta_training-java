package com.epam.training.maryna_yatskevych.final_task.test;
import com.epam.training.maryna_yatskevych.final_task.driver.DriverSingleton;
import com.epam.training.maryna_yatskevych.final_task.page.LoginPage;
import com.epam.training.maryna_yatskevych.final_task.page.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTest extends CommonConditions{
    private LoginPage loginPage;
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @BeforeEach
    public void setUp() {
        logger.info("Setting up the driver for the test.");
        super.setUp();
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage();
        loginPage.open();
    }
    @Test
    public void testLoginWithEmptyCredentials() {
        User standardUser = User.standardUser();
        logger.info("Starting test: testLoginWithEmptyCredentials");

        loginPage.enterUsername(standardUser.getUsername());
        logger.info("Enter username: {}", standardUser.getUsername());

        loginPage.enterPassword(standardUser.getPassword());
        logger.info("Enter password.");

        loginPage.clearFields();
        logger.info("Clear all input fields.");

        assertTrue(loginPage.isUsernameFieldEmpty(), "Username field is not empty");
        assertTrue(loginPage.isPasswordFieldEmpty(), "Password field is not empty");

        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver ->
                loginPage.isUsernameFieldEmpty() && loginPage.isPasswordFieldEmpty()
        );
        logger.info("Waiting for fields to be cleared.");

        loginPage.clickLogin();
        logger.info("Clicked login button.");

        String errorMessage = loginPage.getErrorMessage();
        logger.info("Attempting to get error message.");
        assertEquals(loginPage.ERROR_USERNAME_MESSAGE, errorMessage);
        logger.info("Error message verified: {}", errorMessage);
    }
    @Test
    public void testLoginWithUsernameOnly() {
        User standardUser = User.standardUser();
        logger.info("Starting test: testLoginWithUsernameOnly");

        loginPage.enterUsername(standardUser.getUsername());
        logger.info("Entered username: {}", standardUser.getUsername());

        loginPage.enterPassword(standardUser.getPassword());
        logger.info("Entered password: {}", standardUser.getPassword());

        loginPage.clearPasswordField();
        logger.info("Cleared password field.");

        assertTrue(loginPage.isPasswordFieldEmpty(), "Password field is not empty");
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver->
                loginPage.isPasswordFieldEmpty()
        );
        logger.info("Waiting for field password to be cleared.");

        loginPage.clickLogin();
        logger.info("Click login button.");

        String errorMessage = loginPage.getErrorMessage();
        logger.info("Attempting to get an error message.");
        assertEquals(loginPage.ERROR_PASSWORD_MESSAGE, errorMessage);
        logger.info("Error message was verified: {}", errorMessage);
    }

    @ParameterizedTest
    @MethodSource("validUsersProvider")
    public void testLoginWithValidCredentials(User user) {
        logger.info("Starting test: testLoginWithValidCredentials with username: {}", user.getUsername());

        loginPage.enterUsername(user.getUsername());
        logger.info("Enter user_name: {}", user.getUsername());

        loginPage.enterPassword(user.getPassword());
        logger.info("Entered password.");

        loginPage.clickLogin();
        logger.info("Click login button.");

        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();

        assertEquals(expectedTitle, actualTitle, "The page title does not match, login might have failed.");
        logger.info("Login verified successfully for username: {}", user.getUsername());
    }

    static Stream<Arguments> validUsersProvider() {
        return Stream.of(
                Arguments.of(User.standardUser()),
                Arguments.of(User.lockedOutUser()),
                Arguments.of(User.problemUser()),
                Arguments.of(User.performanceGlitchUser()),
                Arguments.of(User.errorUser()),
                Arguments.of(User.visualUser())
        );
    }
    @AfterEach
    public void tearDown() {
        DriverSingleton.quitDriver();
    }
}
