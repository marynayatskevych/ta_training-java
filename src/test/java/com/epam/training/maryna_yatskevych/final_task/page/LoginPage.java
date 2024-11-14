package com.epam.training.maryna_yatskevych.final_task.page;
import com.epam.training.maryna_yatskevych.final_task.driver.DriverSingleton;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage
{

    private static final String PAGE_URL = "https://www.saucedemo.com";

    @FindBy(xpath = "//input[@id = 'user-name']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id = 'password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id = 'login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage() {
        super(DriverSingleton.getDriver());
        PageFactory.initElements(DriverSingleton.getDriver(), this);
    }

    public void open() {
        logger.info("Opening the login page: {}", PAGE_URL);
        driver.get(PAGE_URL);
    }

    public boolean isUsernameFieldEmpty() {
        return usernameField.getAttribute("value").isEmpty();
    }

    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        usernameField.sendKeys(username);
    }

    public boolean isPasswordFieldEmpty() {
        return passwordField.getAttribute("value").isEmpty();
    }
    public void enterPassword(String password) {
        logger.info("Entering password");
        passwordField.sendKeys(password);
    }

    public void clearFields() {
        usernameField.click();
        usernameField.sendKeys(Keys.CONTROL + "a");
        usernameField.sendKeys(Keys.DELETE);

        passwordField.click();
        passwordField.sendKeys(Keys.CONTROL + "a");
        passwordField.sendKeys(Keys.DELETE);
    }
    public void clearPasswordField() {
        passwordField.click();
        passwordField.sendKeys(Keys.CONTROL + "a");
        passwordField.sendKeys(Keys.DELETE);
    }

    public void clickLogin() {
        logger.info("Clicking the login button");
        loginButton.click();
    }

    public String getErrorMessage() {
        waitForVisibility(errorMessage);
        return errorMessage.getText();
    }

}
