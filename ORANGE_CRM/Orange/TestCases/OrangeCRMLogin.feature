@OrangeLoginFunctionality
Feature: OrangeCRM Login Test cases

  Background: Sheet configuration

  Scenario: TC001_Orange_CRM_LoginWithvalidCredentials
    When Configure my data sheet path "./ORANGE_CRM/Orange/DriverFile/UIMAP.xlsx" and spread sheet name "WebLocator"
    Given Launch browser and enter orange crm url for ScenarioId "TC001_Orange_CRM_LoginWithvalidCredentials"
    And The Below Step Description is "Verify user is on Login Page"
    Then Get Text from element "Login_Page_Text" "LoginPage_Title"
    And The Below Step Description is "Clear the Username from CRM login page which is already there in field and Enter new one"
    Then Enter value "Username" in "Username_Element" web
    And The Below Step Description is "Clear the Password from CRM login page which is already there in field and Enter new one"
    Then Enter value "Password" in "Password_Element" web
    And The Below Step Description is "Click on Login Button"
    Then Click on element "Login"
    And The Below Step Description is "Verify the user is landed on Home Page after succefully login"
    Then Get Text from element "HomePage_Text" "HomePage"
    And The Below Step Description is "Close the Orange CRM web application"
    Then Close the OrangeCRM Web Application

  Scenario: TC002_Orange_CRM_LoginWithInvalidCredentials
    When Configure my data sheet path "./ORANGE_CRM/Orange/DriverFile/UIMAP.xlsx" and spread sheet name "WebLocator"
    Given Launch browser and enter orange crm url for ScenarioId "TC002_Orange_CRM_LoginWithInvalidCredentials"
    And The Below Step Description is "Clear the Username from CRM login page which is already there in field and Enter new one"
    Then Enter value "Username" in "Username_Element" web
    And The Below Step Description is "Clear the Password from CRM login page which is already there in field and Enter new one"
    Then Enter value "Password" in "Password_Element" web
    And The Below Step Description is "Click on Login Button"
    Then Click on element "Login"
    And The Below Step Description is "Verify the user is unable to login using invalid credentials"
    Then Get Text from element "Validation_Message" "Validation_Popup"
    And The Below Step Description is "Close the Orange CRM web application"
    Then Close the OrangeCRM Web Application
