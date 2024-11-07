package com.epam.training.maryna_yatskevych.final_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    public String ERROR_USERNAME_MESSAGE = "Epic sadface: Username is required";
    public String ERROR_PASSWORD_MESSAGE = "Epic sadface: Password is required";

    protected static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
