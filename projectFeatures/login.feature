@Login
Feature: The Login Page
	As a returning customer
	I want to login to the application
	So that I can view my account balance

Background:
	Given the user is on the login page

@GoodLogin
Scenario: the user should be able to login with valid credentials
	When the user enters valid credentials
	Then the user should be able to view account balance

@BadLogin
Scenario: the user should not be able to login with bad credentials
	When the user enters bad credentials 
	Then the user should not be able to login
	And the user should get an invalid login message