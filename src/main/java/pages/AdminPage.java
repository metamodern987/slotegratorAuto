package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author gosha
 * @created 08.05.2021
 */

public class AdminPage {

    public WebDriver driver;

    public AdminPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    @FindBy(css = "a[data-target='#s-menu-users']")
    WebElement usersMenu;

    @FindBy(linkText = "Players")
    WebElement playersOnMenu;

    @FindBy(css = "table table-hover table-striped table-bordered table-condensed")
    WebElement table;

    @FindBy(css="div[class='summary']")
    WebElement displayingText;

    @FindBy(css="input[name='PlayerSearch[name]']")
    WebElement inputPlayerSearchName;

    @FindBy(css = "tr[class='odd']")
    List<WebElement> oddRows;

    @FindBy(css = "tr[class='even']")
    List<WebElement> evenRows;

    public void clickUsers(){
        usersMenu.click();
    }

    public void clickPlayersOnMenu(){
        playersOnMenu.click();
    }

    public WebElement getPlayersOnMenu() {
        return playersOnMenu;
    }

    public WebElement getTable(){
        return table;
    }

    public WebElement getDisplayingText(){
        return displayingText;
    }

    public void sortPlayersOnMenu(String sortString) {
        inputPlayerSearchName.sendKeys(sortString);
        inputPlayerSearchName.sendKeys(Keys.ENTER);
    }

    public List<WebElement> getOddRows(){
        return oddRows;
    }
    public List<WebElement> getEvenRows(){
        return evenRows;
    }
}
