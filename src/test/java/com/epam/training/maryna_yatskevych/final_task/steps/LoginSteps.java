package com.epam.training.maryna_yatskevych.final_task.steps;
import com.epam.training.maryna_yatskevych.final_task.driver.DriverSingleton;
import com.epam.training.maryna_yatskevych.final_task.page.LoginPage;
import com.epam.training.maryna_yatskevych.final_task.page.User;
import com.epam.training.maryna_yatskevych.final_task.test.CommonConditions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps extends CommonConditions {
    private LoginPage loginPage;

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("I clear the password field")
    public void iClearThePasswordField() {
        loginPage.clearPasswordField();
    }

    @And("I clear all input fields")
    public void iClearAllInputFields() {
        loginPage.clearFields();
    }

    @And("I hit the {string} button")
    public void iHitTheButton(String button) {
        if ("Login".equals(button)) {
            loginPage.clickLogin();
        }
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(expectedMessage, errorMessage);
    }

    @Then("I should see that the password field is empty")
    public void iShouldSeeThatThePasswordFieldIsEmpty() {
        assertTrue(loginPage.isPasswordFieldEmpty(), "Password field is not empty");
        new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(2)).until(driver ->
                loginPage.isPasswordFieldEmpty()
        );
    }

    @Then("I should be redirected to the page with title {string}")
    public void iShouldSeeThePageTitleAs(String expectedTitle) {
        String actualTitle = DriverSingleton.getDriver().getTitle();
        assertEquals(expectedTitle, actualTitle, "The page title does not match, login might have failed.");
    }

    @When("I enter valid credentials for user {string}")
    public void iEnterValidCredentialsForUser(String userType) {
        User user = getUserByType(userType);
        loginPage.enterUsername(user.getUsername());
        loginPage.enterPassword(user.getPassword());
    }

    private User getUserByType(String userType) {
        switch (userType.toLowerCase()) {
            case "standard_user":
                return User.standardUser();
            case "locked_out_user":
                return User.lockedOutUser();
            case "problem_user":
                return User.problemUser();
            case "performance_glitch_user":
                return User.performance_glitch_user();
            case "error_user":
                return User.error_user();
            case "visual_user":
                return User.visual_user();
            default:
                throw new IllegalArgumentException("Unknown user type: " + userType);
        }
    }
}