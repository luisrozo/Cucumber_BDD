@Login
Feature: The Login Page
	As a returning customer
	I want to login to the application
	So that I can view my account balance

Background:
	Given the user is on the login page

@GoodLogin
@ignore
Scenario: the user should be able to login with valid credentials
	When the user enters valid credentials
	Then the user should be able to view account balance

@BadLogin
@ignore
Scenario: the user should not be able to login with bad credentials
	When the user enters bad credentials 
	Then the user should not be able to login
	And the user should get an invalid login message


# Use of variables passed to Java functions
@ignore
Scenario: the user should be able to login
	When the user enters username as "tim@testemail.com"
	And the user enters password as "trpass"
	And the user clicks on login
	Then the user should be able to view account balance
	
@ignore
Scenario Outline: the user should be able to login
	When the user enters "<username>" and "<password>"
	Then the user should be able to view account balance

# Data (one execution per row)

Examples:
| username | password |
| tim@testemail.com | trpass |
| lisa@testemail.com | lpass |

# Example with local data table

Scenario: after a failed attempt, the user should be able to login again
	When the user enters set of username and password
	| username | password |
	| tim@testemail.com | trpass |
	| lisa@testemail.com | lpass |
	Then the user should be able to view account balance

	