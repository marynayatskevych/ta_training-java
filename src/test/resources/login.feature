Feature: Login Functionality

  Scenario: Test login form with empty credentials
    Given I open the login page
    When I enter username "standard_user" and password "secret_sauce"
    And I clear all input fields
    And I hit the "Login" button
    Then I should see the error message "Epic sadface: Username is required"

  Scenario: Test login with username only
    Given I open the login page
    When I enter username "standard_user" and password "secret_sauce"
    And I clear the password field
    And I hit the "Login" button
    Then I should see the error message "Epic sadface: Password is required"

  Scenario Outline: Test login with valid credentials
    Given I open the login page
    When I enter username "<username>" and password "<password>"
    And I hit the "Login" button
    Then I should be redirected to the page with title "Swag Labs"
    Examples:
    | username  | password |
    | standard_user | secret_sauce |
    |locked_out_user | secret_sauce |
    |problem_user | secret_sauce |
    |performance_glitch_user | secret_sauce|
    |error_user  | secret_sauce |
    |visual_user | secret_sauce |






