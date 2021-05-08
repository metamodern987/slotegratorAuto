package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author gosha
 * @created 08.05.2021
 */

public class MainPage {

    public WebDriver driver;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    @FindBy(id="UserLogin_username")
    WebElement login;

    @FindBy(id="UserLogin_password")
    WebElement password;

    @FindBy(css = "input[class='btn btn-primary btn-lg btn-block']")
    WebElement buttonSignIn;

    public void clickSignIn(){
        buttonSignIn.click();
    }

    public void setLogin(String loginUser) {
        login.sendKeys(loginUser);
    }

    public void setPassword(String pass) {
        password.sendKeys(pass);
    }
}
