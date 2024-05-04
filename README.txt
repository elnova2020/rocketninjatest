========= General ========= 
Author: Elizabeth Novak
email: vetika@gmail.com

========== Description ==========
The project contains tests suite for testing of search form on web site - https://www.rocketjumpninja.com/mouse-search .
Tests have been developed using TestNG framework and Selenium framework for web elements manipulations.

The tests are located in com.rocketninja.automation.search_mouse.ui.tests package.

src/test/java/com/rocketninja/automation/search_mouse/ui/tests/InvalidSearchParametersValuesTests.java file
contains data driven test with 12 configurations for testing. There are failures in this group since there are real bugs on the site.

src/test/java/com/rocketninja/automation/search_mouse/ui/tests/SearchFormUIBehaviorTests.java
contains 2 tests, they fail, real bugs had been discovered.

src/test/java/com/rocketninja/automation/search_mouse/ui/tests/SearchMouseByParamsTest.java
contains data driven test with 3 configurations. Verifies that the result mice list matches search parameters values.
The tests pass.

There is a Test Plan file for the 'search mouse' feature in documentation folder.

========== Execute Tests ==========

This section describes how to execute tests.

== 1. Prerequisites

1.a Please be sure you have installed maven and java jre on your computer.

You can follow these instructions To install maven - https://phoenixnap.com/kb/install-maven-windows.
The test were developed with java version 8 sdk. So be sure you have java 8 at least.

1.b The tests have been developed and executed on Chrome version 124.
The src folder contains chrome driver that is compatible with chrome version 124.
The default (v.124) chromedriver.exe is in search-mouse/driver folder.

If you have another version of Chrome browser please download the compatible version of chrome driver from
https://chromedriver.chromium.org/downloads .

1.c Optionally - plase install git.

== 2. Get project to your computer and configure test run properties
 
2.a Create a working directory for the project.
2.b Go to github repository https://github.com/elnova2020/rocketninjatest,
	clone the project [git clone https://github.com/elnova2020/rocketninjatest.git] or download and open zip file to your work directory.

== 3. Test run configuration
 ``	3.a There is src/test/resources/test-config.properties .
	The default values defines that test will run with default chromedriver.exe from the resource folder and
	the default running mode in headless mode. That means that the browser won't be open windows on the screen while tests running.
	
	If you use chromedriver installed on your computer you need configure it in test-config.properties file:
	
	chrome.driver.home=<full path to your version of chrome driver>
	
	Below there is an example of the chrome driver value:
	chrome.driver.home=C:/Dev/Selenium/drivers/chromedriver_win32/124/chromedriver.exe
	
	You should put full path of the driver location on your computer.
	
	If you want to see windows of the browser on your screen you should change the default value of chrome.headless to be false :
	chrome.headless=false
	
	3.b There is test_suite.xml file in src/test/resources/tests-run folder.
		In this file you can remove/add tests group from execution in <groups><run> section.
											  
== 4. Execute tests.

4.a Build the project :
	in the directory where you cloned the project enter the command > cd rocketninjatest
	and then enter the command > mvn clean install -DslipTests 

4.b Execute all tests with command : > mvn test

The tests run should finish with the line :

Tests run: 6, Failures: 3, Errors: 0, Skipped: 0

Three failures are expected since there are bugs and tests find them. 

4.c The detailed test reports you can see in 
	target/surefire-reports/html/index.html and 
	target/surefire-reports/SearchMouseUI/SearchMouseFormTests.html.
	
	Tests report will contain 15 passed tests and 3 failed tests. These are real bugs that tests find.

Enjoy !
	