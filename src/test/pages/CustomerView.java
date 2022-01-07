package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerView {
    WebDriver driver;

    public CustomerView(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//button[@class=\"btn btn-lg tab\"])[2]")
    public WebElement deposit;
    @FindBy(xpath = "//input[@placeholder=\"amount\"]")
    public WebElement depositinput;
    @FindBy(xpath = "//button[@class=\"btn btn-default\"]")
    public WebElement depositbutton;
    @FindBy(xpath = "//span[@class=\"error ng-binding\"]")
    public WebElement sucessmesssage;
    @FindBy(xpath = "//strong[@class=\"ng-binding\"][2]")
    public WebElement balance;
    @FindBy(xpath ="//button[@ng-class=\"btnClass3\"]")
    public WebElement withdrawalnutton;
    @FindBy(xpath = "//input[@class=\"form-control ng-pristine ng-untouched ng-invalid ng-invalid-required\"]")
    public WebElement withdrawalinput;
    @FindBy(xpath = "//button[@class=\"btn btn-default\"]")
    public WebElement withdrawal;
    @FindBy(xpath = "//span[@class=\"error ng-binding\"]")
    public WebElement successwithdrawal;
    @FindBy(xpath = "//button[@class=\"btn logout\"]")
    public WebElement logoutbutton;
    @FindBy(id = "accountSelect")
    public WebElement acountselectdropdown;
    @FindBy(xpath = "//strong[@class=\"ng-binding\"][1]")
    public WebElement accNum;
    @FindBy(xpath = "//td[@class=\"ng-binding\"][2]")
    public WebElement transTable;
    @FindBy(xpath = "//button[@ng-class=\"btnClass1\"]")
    public WebElement transButton;
    @FindBy(xpath = "(//td[@class=\"ng-binding\"])[6]")
    public WebElement transType;



}
