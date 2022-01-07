package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//button[@class=\"btn btn-primary btn-lg\"])[1]")
    public WebElement customer;
    @FindBy(xpath = "(//button[@class=\"btn btn-primary btn-lg\"])[2]")
    public WebElement manager;

}
