package com.assertthat.steps;

import com.assertthat.pages.SearchResultsPage;
import com.assertthat.pages.HomePage;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.assertthat.utils.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class Steps {
    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @Given("I'm on Marketplace home page")
    public void i_m_on_Marketplace_hole_page() {
        driver.get("https://marketplace.atlassian.com");
        homePage = new HomePage(driver);
        assertTrue("Home page is not loaded", homePage.isLoaded());
    }

    @When("I search for {string} plugin")
    public void i_search_for_plugin(String string) {
        homePage.searchFor(string);
        searchResultsPage = new SearchResultsPage(driver);
        assertTrue("Search results page is not loaded", searchResultsPage.isLoaded());
    }

    @Then("I se {string} plugin in search results")
    public void i_se_plugin_in_search_results(String string) {
       assertEquals(string, searchResultsPage.getResult());
    }

    @Before
    public void beforeScenario() {
        driver = DriverFactory.getDriver();
    }

    @After
    public void afterScenario(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE_CHROME).getImage(), "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            scenario.embed(imageInByte, "image/png");
        }
        driver.quit();
    }
}