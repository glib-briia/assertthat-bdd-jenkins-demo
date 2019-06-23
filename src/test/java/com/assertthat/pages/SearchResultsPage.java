package com.assertthat.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultsPage extends BasePage {

    @FindBy(css = ".hits h3 span span span")
    private WebElement searchResult;

    @FindBy(css = ".discoverNavigationHeader ~div div div h2")
    private WebElement searchResultHeader;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }


    public String getResult() {
        wait.until(
                ExpectedConditions.visibilityOf(searchResult));
        return searchResult.getText();
    }

    public boolean isLoaded() {
        wait.until(
                ExpectedConditions.visibilityOf(searchResultHeader));
        return searchResultHeader.getText().equals("Search results");
    }
}
