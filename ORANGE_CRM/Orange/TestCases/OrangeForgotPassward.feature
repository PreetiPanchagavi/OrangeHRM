@OrangeForgotPasswordFunctionality
Feature: OrangeCRM LoginAsDifferentUser Test cases

  Background: Sheet configuration

  Scenario: TC003_Orange_CRM_LoginAsDifferentUser
    When Configure my data sheet path "./ORANGE_CRM/Orange/DriverFile/UIMAP.xlsx" and spread sheet name "WebLocator"
    Given Launch browser and enter orange crm url for ScenarioId "TC003_Orange_CRM_LoginAsDifferentUser"
    And The Below Step Description is "Click on Login As Differenr Role Button"
    Then Click on element "Login_As_Diff_User"
    And The Below Step Description is "Select Admistration to login into the application"
    Then Click on element "Login_As_Admin"
    And The Below Step Description is "Verify the user is landed on Home Page after succefully login"
    Then Get Text from element "HomePage_Text" "HomePage"
    And The Below Step Description is "Close the Orange CRM web application"
    Then Close the OrangeCRM Web Application
