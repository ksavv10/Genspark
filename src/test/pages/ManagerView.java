package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerView {
    WebDriver driver;
    public ManagerView(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//button[@class=\"btn btn-lg tab\"][1]")
    public WebElement addcustomer;
    @FindBy(xpath = "(//input[starts-with(@class, 'form-control ng-pristine ng')])[1]")
    public WebElement nameinput;
    @FindBy(xpath = "(//input[@class=\"form-control ng-pristine ng-untouched ng-invalid ng-invalid-required\"])[1]")
    public WebElement lastnameinput;
    @FindBy(xpath = "//input[@placeholder=\"Post Code\"]")
    public WebElement postalcodeinput;
    @FindBy(xpath = "//button[@class=\"btn btn-default\"]")
    public WebElement addcustomerbutton;
    @FindBy(xpath = "//select[@name=\"userSelect\"]")
    public WebElement selectuser;
    @FindBy(xpath = "//button[@ng-click=\"openAccount()\"]")
    public WebElement openAccountbutton;
    @FindBy(xpath = "//select[@name=\"currency\"]")
    public WebElement selectCurrency;
    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement processbutton;
    @FindBy(xpath = "(//button[@ng-click=\"deleteCust(cust)\"])[1]")
    public WebElement deletebutton;
    @FindBy(xpath = "//button[@ng-click=\"showCust()\"]")
    public WebElement custumerbutton;
    @FindBy(xpath = "//input[@placeholder=\"Search Customer\"]")
    public WebElement searchbar;
    @FindBy(xpath = "//td[@class=\"ng-binding\"][1]")
    public WebElement nameinput1;
    @FindBy(xpath = "//td[@class=\"ng-binding\"][2]")
    public WebElement lastnameinput1;
    @FindBy(xpath = "//td[@class=\"ng-binding\"][3]")
    public WebElement postalinput;

}
