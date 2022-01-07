package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Login {
    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userSelect")
    public WebElement select;
    @FindBy(xpath = "//button[@class=\"btn btn-default\"]")
    public WebElement loginbutton;
    @FindBy(xpath = "//button[@class=\"btn logout\"]")
    public WebElement logoutbutton;
    @FindBy(xpath = "//button[@class=\"btn home\"]")
    public WebElement homeButton;


    public void login(int x){
        this.select.click();
        Select select = new Select(this.select);
        select.selectByIndex(x);
        this.loginbutton.click();
    }
}
