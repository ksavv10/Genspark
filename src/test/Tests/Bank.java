package test.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.pages.CustomerView;
import test.pages.HomePage;
import test.pages.Login;
import test.pages.ManagerView;
import test.utils.LoadProperties;
import test.utils.driverUtil;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Bank {
    WebDriver driver;
    String url = LoadProperties.getProperty("url");

    @BeforeTest
    public void setUp() {
        driver = driverUtil.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void setupTest() {
//        String url = LoadProperties.getProperty("url");
//        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        driver.get(url);
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        Assert.assertTrue(login.select.isDisplayed(),"it is not displayed");
    }

    @Test
    public void selectName() {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        Assert.assertTrue(login.logoutbutton.isDisplayed(), "it is not present");
    }

    @Test
    public void deposit() throws InterruptedException, IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        Assert.assertTrue(login.logoutbutton.isDisplayed(), "it is not present");
        CustomerView customerView = new CustomerView(driver);
//        Map<String, String> data = Exel.readexcel().get("deposit");
        customerView.deposit.click();
//        customerView.depositinput(data.get("input1"));
        customerView.depositinput.sendKeys("100");
        customerView.depositbutton.click();
        Thread.sleep(4000);
        Assert.assertEquals(customerView.sucessmesssage.getText(), "Deposit Successful");
        Assert.assertEquals(customerView.balance.getText(), "100");
        customerView.transButton.click();
        Thread.sleep(4000);
        Assert.assertEquals(customerView.transTable.getText(),"100");

    }

//    @Test
//    public void withdrawal() {
//        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
//        HomePage homePage = new HomePage(driver);
//        homePage.customer.click();
//        Login login = new Login(driver);
//        login.select.click();
//        Select select = new Select(login.select);
//        select.selectByIndex(2);
//        login.loginbutton.click();
//        Assert.assertTrue(login.logoutbutton.isDisplayed(), "it is not present");
//        CustomerView customerView = new CustomerView(driver);
//        customerView.withdrawalnutton.click();
//        customerView.withdrawalinput.sendKeys("100");
//        customerView.withdrawal.click();
//        Assert.assertEquals(customerView.successwithdrawal.getText(), "Transaction Failed. You can not withdraw amount more than the balance.");
//        Assert.assertEquals(customerView.balance.getText(),"0");
//        customerView.transButton.click();
//        Assert.assertEquals(customerView.transType.getText(),"Debit");
//    }

    @Test
    public void managerLogin() {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.manager.click();
        ManagerView managerView = new ManagerView(driver);
        Assert.assertTrue(managerView.custumerbutton.isDisplayed(),"It is not present");
    }

    @Test
    public void addCustomer() {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.manager.click();
        ManagerView managerView = new ManagerView(driver);
        managerView.addcustomer.click();
        managerView.nameinput.click();
        managerView.nameinput.sendKeys("Kirill");
        managerView.lastnameinput.sendKeys("Savvun");
        managerView.postalcodeinput.sendKeys("27513");
        managerView.addcustomerbutton.click();
        String alert = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alert, "Customer added successfully with customer id :6");

    }

    @Test
    public void openAccount() throws InterruptedException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.manager.click();
        ManagerView managerView = new ManagerView(driver);
        managerView.openAccountbutton.click();
        managerView.selectuser.click();
        Select select = new Select(managerView.selectuser);
        select.selectByIndex(3);
        managerView.selectCurrency.click();
        Select select1 = new Select(managerView.selectCurrency);
        select1.selectByIndex(2);
        managerView.processbutton.click();
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        Assert.assertEquals(alertText, "Account created successfully with account Number :1016");


    }
    @Test
    public void deleteCustomer(){
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.manager.click();
        ManagerView managerView = new ManagerView(driver);
        managerView.custumerbutton.click();
        managerView.deletebutton.click();

    }
    @Test
    public void logout(){
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        login.logoutbutton.click();
        Assert.assertTrue(login.homeButton.isDisplayed(),"oops wrong way bud");
    }
    @Test
    public void dropdown(){
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        Assert.assertTrue(login.logoutbutton.isDisplayed(), "it is not present");
        CustomerView customerView = new CustomerView(driver);
        customerView.acountselectdropdown.click();
        Select select1 = new Select(customerView.acountselectdropdown);
        select1.selectByIndex(2);
        Assert.assertEquals(customerView.accNum.getText(),"1009");
        //Assert for account number.
    }
    @Test
    public void customersearch(){
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.manager.click();
        ManagerView managerView = new ManagerView(driver);
        managerView.custumerbutton.click();
        managerView.searchbar.click();
        managerView.searchbar.sendKeys("Ron");
        Assert.assertTrue(managerView.nameinput1.getText().contains("Ron"), "does not contain Ron");
        Assert.assertTrue(managerView.lastnameinput1.getText().contains("Weasly"),"oops something went wrong");
        Assert.assertTrue(managerView.postalinput.getText().contains("E55555"), "oops something went wrong");

    }
    @Test
    public void depositwithdrawal(){
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        Assert.assertTrue(login.logoutbutton.isDisplayed(), "it is not present");
        CustomerView customerView = new CustomerView(driver);
        customerView.deposit.click();
        customerView.depositinput.sendKeys("200");
        customerView.depositbutton.click();
        customerView.withdrawalnutton.click();
        customerView.withdrawalinput.sendKeys("100");
        customerView.withdrawal.click();
        Assert.assertEquals(customerView.successwithdrawal.getText(), "Transaction successful");
        Assert.assertEquals(customerView.balance.getText(),"100");
        customerView.transButton.click();
        Assert.assertEquals(customerView.transType.getText(),"Debit");
    }
//    @Test
//    public void homeButton(){
//        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
//        HomePage homePage = new HomePage(driver);
//        homePage.customer.click();
//        Login login = new Login(driver);
//        login.homeButton.click();
//        Assert.assertTrue(homePage.customer.isDisplayed(),"it is displayed");
//    }



    // implement screenshot
    // implement testNGlistener
    // implement separate the test data into excel
    // create excel put all test cases in column 1, then inputs should be in next column


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
