========= General ========= 
Author: Elizabeth Novak
email: vetika@gmail.com

========== Description ==========
The project contains tests suite for testing of search form on web site - http://rocketninja .
Tests have been developed using TestNG framework and Selenium framework for web elements manipulations.

The tests are located in com.rocketninja.automation.search_mouse.ui.tests package.

/search_mouse/src/test/java/com/rocketninja/automation/search_mouse/ui/tests/InvalidSearchParametersValuesTests.java file
contains data driven test with 12 configurations for testing. There are failures in this group since there are real bugs on the site.

/search_mouse/src/test/java/com/rocketninja/automation/search_mouse/ui/tests/SearchFormUIBehaviorTests.java
contains 2 tests, they fail, real bugs had been discovered.

/search_mouse/src/test/java/com/rocketninja/automation/search_mouse/ui/tests/SearchMouseByParamsTest.java
contains data driven test with 3 configurations. Verifies that the result mice list matches search parameters values.
The tests pass.

In search-mouse/documentation folder there is Test Plan for the 'search mouse' feature.

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
 
2.a Create a working directory for test project.
2.b Go to github repository https://github.com/elnova2020/rocketninjatest,
	clone the project or download and open zip file to your work directory.

== 3. Test run configuration
 ``	3.a There is /search_mouse/src/test/resources/test-config.properties .
	The default values defines that test will run with default chromedriver from resource folder in headless mode
	that means that the browser won't be opened on the screen while tests running.
	
	If you use chromedriver installed on your computer you need configure it in test-config.properties file:
	
	chrome.driver.home=C:/Dev/Selenium/drivers/chromedriver_win32/124/chromedriver.exe
	
	You should put full path of the driver location on your computer.
	
	If you want to see windows of browser on your screen you should change the default value of chrome.headless to be false :
	chrome.headless=false
	
	3.b There is test_suite.xml file in  /search_mouse/src/test/resources/tests-run folder.
		In this file you can remove/add tests group from execution in <groups><run> section.
											  
== 4. Execute tests.
4.a Build the project - in the project directory enter the command mvn clean install -DslipTests

4.b Execute all tests - command : mvn test

4.c The results you can see in target/surefire-reports/html/index.html and /search_mouse/target/surefire-reports/SearchMouseUI/SearchMouseFormTests.html.
	Tests report will contain 3 failed tests. These are real bugs that tests found.

Enjoy !
	