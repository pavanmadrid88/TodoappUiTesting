<h3> Todo app UI Tests </h3>
<p>
<i><strong>This project contains UI tests for Todo app, implemented using TestNg,Java(Selenium-Webdriver)</strong></i>
</p>

### Features
* Test cases defined and driven by TestNg.xml
* Maven build tool setup  
* Page object model classes to define page objects/action methods.
* slf4j logging  
* Utility methods for listeners and reporting.
* Properties file to drive environment test data.
* Support for Extent-html-reports.

### To Get Started
#### Pre-requisites
- Install Java 8.x
- Install Maven 
- Install IntelliJ or your favourite IDE
- Start the Todo app server in your local
#### Project Setup
- Open IntelliJ
- From file menu open project -> select project root folder

#### Build 
- Make sure Todo app server is running
- Once the test project is imported successfully through IntelliJ,run the tests with the help of 'mvn clean test' command


### Reports
The Extent report is integrated with maven and generates a report in HTML format.
#### Extent HTML report
The HTML report gives a graphical representation of the test results.
![extent-report-html](readme-files/images/extent_report.png)
