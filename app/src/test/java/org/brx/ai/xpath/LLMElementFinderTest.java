package org.brx.ai.xpath;

import org.brx.ai.LLMElementFinder;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LLMElementFinderTest {
	private static final String GEMINI_AI_KEY = "<Enter your api key here>",
			                    GEMINI_MODEL_NAME="gemini-1.5-flash";
	
	WebDriver driver = new ChromeDriver();
	String elementDescrption = "Query field on the page";

	@Test
	public void geminiLLMFindGoogleSearchBoxByDescription() {
		try {
			driver.get("https://www.google.com");
			LLMElementFinder finder = initFinder();
			WebElement qryElement = finder.findElement(elementDescrption);
			assertThat(qryElement.getTagName(), is("textarea"));
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void  geminiLLMFindGoogleSearchBoxByContextElement() {
		try {
			WebElement contextElement = getContextElement();
			LLMElementFinder finder = initFinder();
			WebElement qryElement = finder.findElement(elementDescrption,contextElement);
			assertThat(qryElement.getTagName(), is("textarea"));
		} finally {
			driver.quit();
		}
	}
	
	
	private LLMElementFinder initFinder() {
		LLMXpathGenerator aiXpathGenerator = new GeminiXpathGenerator(GEMINI_AI_KEY,GEMINI_MODEL_NAME);
		return new LLMElementFinder(driver, aiXpathGenerator);
	}
	
	
	private WebElement getContextElement() {
		driver.get("https://www.google.com");
		return driver.findElement(By.xpath("//form[@action='/search']"));
	}
	

}
