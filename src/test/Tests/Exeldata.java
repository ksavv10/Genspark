package test.Tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
import test.utils.driverUtil;

import java.io.IOException;
import java.security.Provider;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Exeldata {
    Logger Log = Logger.getLogger(Exeldata.class);

    WebDriver driver;
    Map<String, Map<String, String>> data;



    @BeforeTest
    public void setUp() throws IOException {
        PropertyConfigurator.configure("C:\\Users\\kirill savvun\\IdeaProjects\\GensparkMebane\\resources\\Log4j.properties");
        driver = driverUtil.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        data = Exel.readexcel();
    }


    @Test
    public void setupTest() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        Map<String, String> data = Exel.readexcel().get("setuptest");
        Assert.assertTrue(login.select.isDisplayed(),data.get("input1"));
        Log.info("New driver instantiated");
    }

    @Test
    public void selectName() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        Map<String, String> data = Exel.readexcel().get("selectname");
        Assert.assertTrue(login.logoutbutton.isDisplayed(), data.get("input1"));
    }

    @Test
    public void deposit() throws InterruptedException, IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        Map<String, String> data = Exel.readexcel().get("deposit");
        Assert.assertTrue(login.logoutbutton.isDisplayed(), data.get("input2"));
        CustomerView customerView = new CustomerView(driver);
        customerView.deposit.click();
        customerView.depositinput.sendKeys(data.get("input1"));
//        customerView.depositinput.sendKeys("100");
        customerView.depositbutton.click();
        Thread.sleep(4000);
        Assert.assertEquals(customerView.sucessmesssage.getText(), data.get("input3"));
        Assert.assertEquals(customerView.balance.getText(), data.get("input1"));
        customerView.transButton.click();
        Thread.sleep(4000);
        Assert.assertEquals(customerView.transTable.getText(),data.get("input1"));

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
    public void managerLogin() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.manager.click();
        ManagerView managerView = new ManagerView(driver);
        Map<String, String> data = Exel.readexcel().get("managerlogin");
        Assert.assertTrue(managerView.custumerbutton.isDisplayed(),data.get("input1"));
    }

    @Test
    public void addCustomer() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.manager.click();
        ManagerView managerView = new ManagerView(driver);
        managerView.addcustomer.click();
        managerView.nameinput.click();
        Map<String, String> data = Exel.readexcel().get("addCustomer");
//        managerView.nameinput.sendKeys("Kirill");
        managerView.nameinput.sendKeys(data.get("input1"));
        managerView.nameinput.sendKeys(data.get("input2"));
        managerView.nameinput.sendKeys(data.get("input3"));
//        managerView.lastnameinput.sendKeys("Savvun");
//        managerView.postalcodeinput.sendKeys("27513");
        managerView.addcustomerbutton.click();
        String alert = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alert, data.get("input4"));

    }

    @Test
    public void openAccount() throws InterruptedException, IOException {
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
        Map<String, String> data = Exel.readexcel().get("openAccount");
        Assert.assertEquals(alertText, data.get("input1"));


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
    public void logout() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        login.logoutbutton.click();
        Map<String, String> data = Exel.readexcel().get("logout");
        Assert.assertTrue(login.homeButton.isDisplayed(),data.get("input1"));

    }
    @Test
    public void dropdown() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        Map<String, String> data = Exel.readexcel().get("dropdown");
        Assert.assertTrue(login.logoutbutton.isDisplayed(), data.get("input1"));
        CustomerView customerView = new CustomerView(driver);
        customerView.acountselectdropdown.click();
        Select select1 = new Select(customerView.acountselectdropdown);
        select1.selectByIndex(2);
        Assert.assertEquals(customerView.accNum.getText(),data.get("input2"));
        //Assert for account number.
    }
    @Test
    public void customersearch() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.manager.click();
        ManagerView managerView = new ManagerView(driver);
        managerView.custumerbutton.click();
        managerView.searchbar.click();
        Map<String, String> data = Exel.readexcel().get("customerSearch");
        managerView.searchbar.sendKeys(data.get("input1"));

//        managerView.searchbar.sendKeys("Ron");
        Assert.assertTrue(managerView.nameinput1.getText().contains("Ron"), data.get("input2"));
        Assert.assertTrue(managerView.lastnameinput1.getText().contains("Weasly"),data.get("input3"));
        Assert.assertTrue(managerView.postalinput.getText().contains("E55555"), data.get("input3"));

    }
    @Test
    public void depositwithdrawal() throws IOException {
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        HomePage homePage = new HomePage(driver);
        homePage.customer.click();
        Login login = new Login(driver);
        login.login(2);
        Map<String, String> data = Exel.readexcel().get("depositWithdrawal");
        Assert.assertTrue(login.logoutbutton.isDisplayed(), "it is not present");
        CustomerView customerView = new CustomerView(driver);
        customerView.deposit.click();
        customerView.depositinput.sendKeys(data.get("input1"));
//        customerView.depositinput.sendKeys("200");
        customerView.depositbutton.click();
        customerView.withdrawalnutton.click();
//        customerView.withdrawalinput.sendKeys("100");
        customerView.withdrawalinput.sendKeys(data.get("input2"));
        customerView.withdrawal.click();
        Assert.assertEquals(customerView.successwithdrawal.getText(), data.get("input3"));
        Assert.assertEquals(customerView.balance.getText(),data.get("input2"));
        customerView.transButton.click();
        Assert.assertEquals(customerView.transType.getText(),data.get("input4"));
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
