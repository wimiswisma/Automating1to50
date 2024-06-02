import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

// Step 1: Open Browser
WebUI.openBrowser('')

// Step 2: Navigate to the game
WebUI.navigateToUrl('https://zzzscore.com/1to50/en/')

// Step 3: Initialize the XPath pattern for the number elements
String numberSelector = '//*[@id="grid"]/div[text()="%s"]'

// Step 4: Loop through numbers 1 to 50
for (int x = 1; x <= 50; x++) {
	println("Looking for " + x)
	String currentNumberXPath = String.format(numberSelector, x)
	TestObject numberElement = new TestObject("number" + x)
	numberElement.addProperty('xpath', ConditionType.EQUALS, currentNumberXPath)
	
	// Wait for the element to be visible
	WebUI.waitForElementVisible(numberElement, 1)
	
	// Click the element
	WebUI.click(numberElement)
}

// Step 5: Wait for and get the final score
TestObject resultsElement = new TestObject("results")
resultsElement.addProperty('css', ConditionType.EQUALS, '.resultContent > .level')
WebUI.waitForElementVisible(resultsElement, 1)
String finalScore = WebUI.getText(resultsElement)
println("Final Score: " + finalScore)

// Step 6: Take a screenshot
WebUI.takeScreenshot("results.png")

// Step 7: Close Browser
WebUI.closeBrowser()

