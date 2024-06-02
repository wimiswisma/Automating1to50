import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

// Step 1: Open Browser
WebUI.openBrowser('')

// Step 2: Navigate to the game
WebUI.navigateToUrl('https://zzzscore.com/1to50/en/')

// Step 3: Initialize list for number elements
List<TestObject> numberElements = new ArrayList<>()

// Step 4: Find and store initial 25 numbers
for (int i = 1; i <= 25; i++) {
    TestObject numberElement = new TestObject("number" + i)
    numberElement.addProperty('xpath', ConditionType.EQUALS, "//*[@id='grid']/div[text()='" + i + "']")
    numberElements.add(numberElement)
}

// Step 5: Click numbers in order from 1 to 25
for (int i = 0; i < numberElements.size(); i++) {
    WebUI.waitForElementVisible(numberElements.get(i), 2)
    WebUI.click(numberElements.get(i))
    
    // Step 5b: Find and store the next number that appears
    if (i < 24) { // Only need to do this for the first 24 clicks
        int nextNumber = i + 26
        TestObject nextNumberElement = new TestObject("number" + nextNumber)
        nextNumberElement.addProperty('xpath', ConditionType.EQUALS, "//*[@id='grid']/div[text()='" + nextNumber + "']")
        numberElements.add(nextNumberElement)
    }
}

// Step 6: Continue clicking numbers 26 to 50
for (int i = 25; i < numberElements.size(); i++) {
    WebUI.waitForElementVisible(numberElements.get(i), 2)
    WebUI.click(numberElements.get(i))
}

// Step 7: Close Browser
WebUI.closeBrowser()
