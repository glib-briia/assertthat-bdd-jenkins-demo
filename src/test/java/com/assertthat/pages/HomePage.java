package com.assertthat.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

   @FindBy(css=".search-box")
   private WebElement searchBox;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage searchFor(String keyword){
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.RETURN);
       return new SearchResultsPage(driver);
   }

   public boolean isLoaded(){
        return driver.getTitle().equals("Explore apps for Atlassian products | Atlassian Marketplace");
   }

}
