package com.powerreviews.demostoretest;

import com.powerreviews.demostoretest.confmodel.Config;
import com.powerreviews.demostoretest.util.UI;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest
public class ReviewsTest extends AbstractTestNGSpringContextTests {

	Logger log = LoggerFactory.getLogger(ReviewsTest.class);

	@Autowired
	Config config;

	@Autowired
	BaseClass baseClass;
	private static boolean buttonNextIsDisplayed;
	private int count;
	private int pageNumber;

	@BeforeClass
	public void startUInstance() {
		baseClass.ui.startInstance();
	}

	@Test
	public void validateThatExpectedNumberOfReviewsEqualsActual() {
		Elements allArticles = getAllArticles(config.getUrlConfigModel().getDemo_store_base_url() +
						config.getUrlConfigModel().getProducts() +
						config.getUrlConfigModel().getWomens_fleet_jacket(),
				"//*[@id=\"pr-rd-main-header\"]/div[1]/h1"
		);
		int numberOfArticles = allArticles.size();
		String numberOfReviews = baseClass.ui.driver().findElement(By.xpath("//*[@class=\"pr-snippet-review-count\"]")).getText();
		log.info("Expecting " + numberOfReviews);
		log.info("Found: " + allArticles.size() + " Reviews");
		log.info("Scanned: " + pageNumber + " pages total");
		Assertions.assertThat(numberOfReviews).contains(String.valueOf(numberOfArticles));
	}

	public Elements getAllArticles(String url, String waitForElement) {
		log.info(url);
		baseClass.ui.driver().get(url);
		baseClass.ui.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(waitForElement)));
		Elements allArticles = new Elements();
		String previousValue;
		count = 0;
		pageNumber = 0;

		while (isArticleSearchAllowed()) {
			pageNumber++;
			log.info("Scanning page number: " + pageNumber);
			Document document = Jsoup.parse(baseClass.ui.driver().getPageSource());
			Elements currentArticles = document.getElementsByClass("pr-review");
			for (Element article : currentArticles) {
				allArticles.add(article);
			}
			log.info("Added " + currentArticles.size() + " reviews from page: " + pageNumber);
			if (buttonNextIsDisplayed) {
				previousValue = baseClass.ui.driver().findElement(By.xpath("//*[@class=\"pr-rd-review-position\"]/span[2]")).getText();
				baseClass.ui.driver().findElement(By.xpath("//button[@aria-label=\"Next\"]")).click();

				int maxAmountOfAttempts = 5;
				for (int i = 1; i < maxAmountOfAttempts; i++) {
					boolean sucessfulChangeOfThePage;
					boolean valueIsChanged = didTextChangeByXpath(previousValue, "//*[@class=\"pr-rd-review-position\"]/span[2]");
					if (valueIsChanged == true) {
						sucessfulChangeOfThePage = true;
						break;
					} else {
						log.error("Couldn't click button 'Next' to switch the page. Attempt: " + i + " out of " + maxAmountOfAttempts);
						log.error("Waiting for 3 seconds");
						sucessfulChangeOfThePage = false;
						sleep(3000);
					}
				}
			}
		}
		return allArticles;
	}

	public boolean isArticleSearchAllowed() {
		try {
			baseClass.ui.driver().findElement(By.xpath("//button[@aria-label=\"Next\"]")).isDisplayed();
			buttonNextIsDisplayed = true;
		} catch (Exception ex) {
			buttonNextIsDisplayed = false;
		}
		if (buttonNextIsDisplayed) {
			return true;
		} else if (count == 0) {
			count++;
			return true;
		} else {
			return false;
		}
	}


	public void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean didTextChangeByXpath(String previousValue, String xpath) {
		String currentValue = baseClass.ui.driver().findElement(By.xpath(xpath)).getText();
		if (currentValue.equals(previousValue)) {
			return false;
		} else {
			return true;
		}
	}

	@AfterClass
	public void killUIinstance() {
		baseClass.ui.killInstance();
	}
}