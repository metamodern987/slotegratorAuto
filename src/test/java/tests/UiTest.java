package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

/**
 * @author gosha
 * @created 08.05.2021
 */

public class UiTest {

    final String LOGIN = "admin1";
    final String PASS = "[9k<k8^z!+$$GkuP";
    final String FILTER_SORT = "Au";

    public static WebDriver driver;


    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://test-app.d6.dev.devcaz.com/admin/login");

    }

    @Test
    public void loginAdmin(){

        MainPage mainPage = new MainPage(driver);
        mainPage.setLogin(LOGIN);
        mainPage.setPassword(PASS);
        mainPage.clickSignIn();
        Assert.assertTrue(driver.getCurrentUrl().contains("configurator/dashboard/index"));

    }

    @Test
    public void openListPlayers() {

        MainPage mainPage = new MainPage(driver);
        mainPage.setLogin(LOGIN);
        mainPage.setPassword(PASS);
        mainPage.clickSignIn();

        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickUsers();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement players = wait.until(ExpectedConditions.visibilityOf(adminPage.getPlayersOnMenu()));
        adminPage.clickPlayersOnMenu();
        String resultsOfTable = adminPage.getDisplayingText().getText().substring(19,23);

        Assert.assertTrue(Integer.parseInt(resultsOfTable) != 0);
    }

    @Test
    public void sortingPlayers(){

        MainPage mainPage = new MainPage(driver);
        mainPage.setLogin(LOGIN);
        mainPage.setPassword(PASS);
        mainPage.clickSignIn();

        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickUsers();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement players = wait.until(ExpectedConditions.visibilityOf(adminPage.getPlayersOnMenu()));
        adminPage.clickPlayersOnMenu();

        adminPage.sortPlayersOnMenu(FILTER_SORT);
        Assert.assertTrue(adminPage.getOddRows().get(0).getText().contains(FILTER_SORT) &&
                                   adminPage.getEvenRows().get(0).getText().contains(FILTER_SORT));

    }


    @After
    public void closeDriver(){
        driver.quit();
    }
}
