package test.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.utils.driverUtil;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestLeafExel {
    WebDriver driver;
    Map<String, Map<String, String>> data;
    @BeforeTest
    public void setUp() throws IOException {
        driver = driverUtil.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        data = Exel2.readexcel2();
    }

    @Test
    public void testTextBox() throws IOException {
        driver.get("http://www.leafground.com/");
        // xpath , css selector
        WebElement editLink = driver.findElement(By.xpath("//a[@href='pages/Edit.html']"));
        editLink.click();
        WebElement textBox1 = driver.findElement(By.id("email")); // //input[@id='email']
        Map<String, String> data = Exel2.readexcel2().get("Textbox");
        textBox1.sendKeys(data.get("input1"));
//        textBox1.sendKeys("test@gmail.com");
        WebElement textBox2 = driver.findElement(By.xpath("//input[@value='Append ']"));
        textBox2.sendKeys(data.get("input2"));
        textBox2.sendKeys(Keys.TAB);//ask sarat
        WebElement textBox3 = driver.findElement(By.xpath("//input[@name='username' and @value='TestLeaf']"));
        String defaultValue = textBox3.getAttribute("value");
        Assert.assertEquals(defaultValue, "TestLeaf");
        WebElement textBox4 = driver.findElement(By.xpath("//input[@name='username' and @value='Clear me!!']"));
        textBox4.clear();
        WebElement textBox5 = driver.findElement(By.xpath("//input[@style=\"width:350px;background-color:LightGrey;\"]"));
        String disabled = textBox5.getAttribute("disabled");
        Assert.assertEquals(disabled, "true", data.get("input4"));
    }

    @Test
    public void testButton() throws IOException {
        driver.get("http://www.leafground.com/pages/Button.html");
        driver.findElement(By.xpath("//button[@id=\"home\"]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/home.html");
        driver.get("http://www.leafground.com/pages/Button.html");
        int x = driver.findElement(By.xpath("//button[@id=\"position\"]")).getLocation().getX();
        int y = driver.findElement(By.xpath("//button[@id=\"position\"]")).getLocation().getY();
        Map<String, String> data = Exel2.readexcel2().get("testButton");
        Assert.assertEquals(x, data.get("input1"));
        Assert.assertEquals(y, data.get("input2"));
        String s = driver.findElement(By.xpath("//button[@id=\"color\"]")).getAttribute("style");
        Assert.assertTrue(s.contains(data.get("input3")));
        int width = driver.findElement(By.xpath("//button[@id=\"size\"]")).getSize().getWidth();
        int height = driver.findElement(By.xpath("//button[@id=\"size\"]")).getSize().getHeight();
        Assert.assertEquals(width, data.get("input4"));
        Assert.assertEquals(height, data.get("input5"));


    }

    @Test
    public void testHyperLink() throws IOException {
        driver.get("http://www.leafground.com/pages/Link.html");
        driver.findElement(By.xpath("(//a[@href=\"../home.html\"and text() = 'Go to Home Page'])[1]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/home.html");
        driver.get("http://www.leafground.com/pages/Link.html");
        String href = driver.findElement(By.xpath("//a[text()='Find where am supposed to go without clicking me?']")).getAttribute("href");
        System.out.println(href);
        List<WebElement> list = driver.findElements(By.xpath("//a[@href=\"../home.html\"]"));
        WebElement element = list.get(2);
        element.click();
        Map<String, String> data = Exel2.readexcel2().get("testHyperLink");
        Assert.assertEquals(driver.getCurrentUrl(), data.get("input1"));
        driver.get("http://www.leafground.com/pages/Link.html");
        List<WebElement> list2 = driver.findElements(By.xpath("//a"));
//        WebElement element = driver.findElement(By.xpath("//a"));
        Assert.assertEquals(list2.size(), data.get("input2"));
    }

    @Test
    public void testBrokenLink() throws IOException, IOException {
        driver.get("http://www.leafground.com/pages/Link.html");
        WebElement brokenLink = driver.findElement(By.xpath("//a[@href=\"error.html\"]"));
//        WebElement brokenLink = driver.findElement(By.xpath("//a[text()='Find where am supposed to go without clicking me?']"));
        String url = brokenLink.getAttribute("href");
        URL u = new URL(url);
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        int respCode = huc.getResponseCode();
        if (respCode >= 400) {
            System.out.println("Broken link");
        } else {
            System.out.println("Valid link");
        }
    }

    @Test
    public void testImage() throws IOException {
        driver.get("http://www.leafground.com/pages/Image.html");
        WebElement element = driver.findElement(By.xpath("//img[@src=\"../images/keyboard.png\"]"));
        Actions actions = new Actions(driver);
        actions.click(element).perform();
        Map<String, String> data = Exel2.readexcel2().get("testImage");
        Assert.assertEquals(driver.getCurrentUrl(), data.get("input1"));
    }

    @Test
    public void testDropDown() {
        driver.get("http://www.leafground.com/pages/Dropdown.html");
        WebElement element = driver.findElement(By.xpath("//select[@id=\"dropdown1\"]"));
//        element.sendKeys("Loadrunner");
        Select select = new Select(element);
        select.selectByValue("1");
        select.selectByIndex(2);
        select.selectByVisibleText("Loadrunner");
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            String text = option.getText();
            System.out.println(text);
        }

        if (select.isMultiple()) {
            select.selectByIndex(2);
            select.selectByIndex(3);
            List<WebElement> selectedOptions = select.getAllSelectedOptions();
        }
    }

    @Test
    public void testRadioButton() {
        driver.get("http://www.leafground.com/pages/radio.html");
        driver.findElement(By.id("yes")).click();

        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@name=\"news\"]"));
        for (WebElement element : radioButtons) {
            if (element.isSelected()) {
                System.out.println(element.getAttribute("value"));
            }
        }
        String groups = driver.findElement(By.xpath("//input[@name=\"age\"]//parent::div")).getText();

        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "1 - 20 years");
        map.put(1, "21 - 40 years");
        map.put(2, "Above 40 years");

        String age = "30";
        List<WebElement> ageGroups = driver.findElements(By.xpath("//input[@name=\"age\"]"));
//        ageGroups.get(key).click();
    }

    @Test
    public void testTable() {
        driver.get("http://www.leafground.com/pages/table.html");
        WebElement table = driver.findElement(By.id("table_id"));
        List<WebElement> columnheaders = table.findElements(By.xpath("//th"));
        System.out.println("number of columns" + columnheaders.size());
        List<WebElement> rows = table.findElements(By.xpath("tr"));
        int rowscount = rows.size() - 1;
        System.out.println("number of rows" + rowscount);


        for (int i = 1; i < rowscount + 1; i++) {
            List<WebElement> columns = rows.get(i).findElements(By.xpath("td"));
            for (int j = 0; j < columns.size(); j++) {
                String text = columns.get(j).getText();
                System.out.println(text);
                if (text.equals("Learn to Interact with Elements")) {
                    System.out.println(columns.get(j + 1).getText());
                    break;
                }
            }
        }
        List<WebElement> progress = driver.findElements(By.xpath("//td[2]"));
        List<WebElement> vitals = driver.findElements(By.xpath("//td[3]"));
        int min = 0;
        for (int i = 0; i < progress.size(); i++) {
            if (min > i) {
                min = i;

            }


        }
        System.out.println(min);
    }

    @Test
    public void nestedFor() {
        for (int i = 0; i < 5; i++) {
            System.out.println("the value of i " + i);
            for (int j = 0; j < 5; j++) {
                System.out.println("the value of j" + j);

            }

        }

    }

    @Test
    public void testAlert() throws IOException {
        driver.get("http://www.leafground.com/pages/Alert.html");
        driver.findElement(By.xpath("//button[@onclick=\"normalAlert()\"]")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//button[@onclick=\"confirmPrompt()\"]")).click();
        Map<String, String> data = Exel2.readexcel2().get("testAlert");
        driver.switchTo().alert().sendKeys(data.get("input1"));
        driver.switchTo().alert().accept();
        String actual = driver.findElement(By.id("result1")).getText();
        Assert.assertEquals(actual, data.get("input2"));
        driver.findElement(By.xpath("//button[@onclick=\"lineBreaks()\"]")).click();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(text, data.get("input3"));
        driver.findElement(By.xpath("//button[@onclick=\"confirmAlert()\"]")).click();
        driver.switchTo().alert().dismiss();
        String actual1 = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(actual1, data.get("input4"));
        driver.findElement(By.id("btn")).click();
        driver.findElement(By.xpath("//button[@class=\"swal-button swal-button--confirm\"]")).click();
    }

    @Test
    public void testFrames() throws IOException {
        driver.get("http://www.leafground.com/pages/frame.html");
        WebElement frame = driver.findElement(By.xpath("//iframe[@src=\"default.html\"]"));
        driver.switchTo().frame(frame);
        driver.findElement(By.id("Click")).click();
        String actual = driver.findElement(By.id("Click")).getText();
        Map<String, String> data = Exel2.readexcel2().get("testFrames");
        Assert.assertEquals(actual, data.get("input1"));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(1);
        driver.switchTo().frame(0);
        driver.findElement(By.id("Click1")).click();
        actual = driver.findElement(By.id("Click1")).getText();
        Assert.assertEquals(actual, data.get("input1"));
        driver.switchTo().defaultContent();
        List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
        int firstcount = frames.size();
        int counter = firstcount;
        for (int i = 0; i < frames.size(); i++) {
            driver.switchTo().frame(i);
            List<WebElement> frame1 = driver.findElements(By.xpath("//iframe"));
            counter = counter + frame1.size();
            driver.switchTo().parentFrame();
        }

        Assert.assertEquals(counter, data.get("input2"));


    }

//    //
//    @Test
//    public void testWindows() {
//        driver.get("http://www.leafground.com/pages/Window.html");
//        driver.findElement(By.id("home")).click();
//        Set<String> windows = driver.getWindowHandles();
//        ArrayList<String> list = new ArrayList<>(windows);
//        driver.switchTo().window(list.get(1));
//        driver.findElement(By.xpath("//a[@href=\"pages/Edit.html\"]")).click();
//        driver.close();
//        driver.switchTo().window(list.get(0));
//        driver.findElement(By.xpath("//button[@onclick=\"openWindows()\"]")).click();
//        windows = driver.getWindowHandles();
//        Assert.assertEquals(windows.size(), 3);
//        driver.close();
//        driver.switchTo().window(list.get(0));
//        driver.findElement(By.xpath("//button[@onclick=\"openWindows();\"]")).click();
//        windows = driver.getWindowHandles();
//        driver.switchTo().windows(list.get(1));
//        driver.close();
//        driver.switchTo().windows(list.get(0));
//
//
//
//
//
//
//
//
//
//
//
//    }
//        driver.findElement(By.xpath("//button[@onclick=\"openWindows();\"]")).click();
//        Set<String> windows = driver.getWindowHandles();
//        ArrayList<String> list = new ArrayList<>(windows);
//        driver.switchTo().window(list.get(1)).close();
//        driver.switchTo().window(list.get(2)).close();
//        driver.findElement(By.id("home")).click();
//        Set<String> windows1 = driver.getWindowHandles();
//        ArrayList<String> list1 = new ArrayList<>(windows);
//        driver.switchTo().window(list1.get(1));
//        driver.findElement(By.xpath("//a[@href=\"pages/Edit.html\"]")).click();
//        driver.close();
//        driver.switchTo().window(list1.get(0));
//        driver.findElement(By.xpath("//button[@onclick=\"openWindows()\"]")).click();
//        windows1 = driver.getWindowHandles();
//        Assert.assertEquals(windows1.size(), 3);
//        driver.findElement(By.id("color")).click();
//    }

    @Test
    public void testCalendar () throws IOException {
        driver.get("http://www.leafground.com/pages/Calendar.html");
        Map<String, String> data = Exel2.readexcel2().get("testCalendar");
        driver.findElement(By.id("datepicker")).sendKeys(data.get("input1"));
        driver.findElement(By.id("datepicker")).click();
        driver.findElement(By.xpath("//a[@class=\"ui-datepicker-next ui-corner-all\"]")).click();
        driver.findElement(By.xpath("(//a[@href=\"#\"])[10]")).click();
    }

    @Test
    public void testDragabble () {
        driver.get("http://www.leafground.com/pages/drag.html");
        WebElement element = driver.findElement(By.id("draggable"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(element, 110, 250).perform();
    }

    @Test
    public void testDroppabble () {
        driver.get("http://www.leafground.com/pages/drop.html");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }

    @Test
    public void testSelectable () {
        driver.get("http://www.leafground.com/pages/selectable.html");
        List<WebElement> list = driver.findElements(By.xpath("//div[@id=\"mydiv\"]//li"));
        Actions actions = new Actions(driver);
        actions.click(list.get(0)).keyDown(Keys.CONTROL).click(list.get(list.size() - 1)).perform();

    }

    @Test
    public void testSortable () {
        driver.get("http://www.leafground.com/pages/sortable.html");
        WebElement item1 = driver.findElement(By.xpath("//li[@class=\"ui-state-default ui-sortable-handle\"][1]"));
        WebElement item5 = driver.findElement(By.xpath("//li[@class=\"ui-state-default ui-sortable-handle\"][5]"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(item1, item5).perform();
    }

    @Test
    public void testAutocomplete () throws InterruptedException, IOException {
        driver.get("http://www.leafground.com/pages/autoComplete.html");
        Map<String, String> data = Exel2.readexcel2().get("testAutoComplete");
        driver.findElement(By.id("tags")).sendKeys(data.get("input1"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"ui-menu-item-wrapper\"]")).click();


    }

    @Test
    public void testDownloadfile () throws InterruptedException {
        driver.get("http://www.leafground.com/pages/download.html");
        File folder = new File("C:\\Users\\kirill savvun\\Downloads");
        int before = folder.listFiles().length;
        driver.findElement(By.xpath("//a[@href=\"../testleaf.xlsx\"]")).click();
        Thread.sleep(2000);
        int after = folder.listFiles().length;
        Assert.assertEquals(before, after - 1);

    }

    @Test
    public void testUploadfile () throws IOException {
        driver.get("http://www.leafground.com/pages/upload.html");
        Map<String, String> data = Exel2.readexcel2().get("testUploadFile");
        driver.findElement(By.xpath("//input[@name=\"filename\"]")).sendKeys(data.get("input1"));
    }

    @Test
    public void testToolTip () {
        driver.get("http://www.leafground.com/pages/tooltip.html");
        WebElement element = driver.findElement(By.id("age"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"ui-tooltip-content\"]")).isDisplayed());


    }
    @Test
    public void testWaittoDissapear () throws IOException {
        driver.get("http://www.leafground.com/pages/disapper.html");
        WebElement element = driver.findElement(By.id("btn"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOf(element));
        String text = driver.findElement(By.id("show")).getText();
        Map<String, String> data = Exel2.readexcel2().get("testWaitToDissapear");
        Assert.assertEquals(text, data.get("input1"));

    }
    @Test
    public void testWaittoAppear () throws IOException {
        driver.get("http://www.leafground.com/pages/appear.html");
        WebElement element = driver.findElement(By.id("btn"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = driver.findElement(By.id("btn")).getText();
        Map<String, String> data = Exel2.readexcel2().get("testWaitToAppear");
        Assert.assertEquals(text, data.get("input1"));

    }
    @Test
    public void testWaitforchange () {
        driver.get("http://www.leafground.com/pages/TextChange.html");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("btn"), "Click ME!"));
    }
    @Test
    public void testWaitforAlert () {
        driver.get("http://www.leafground.com/pages/alertappear.html");
        driver.findElement(By.id("alert")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
    }
    @Test
    public void testHoverMouse () throws IOException {
        driver.get("http://www.leafground.com/pages/mouseOver.html#");
        WebElement element = driver.findElement(By.xpath("//a[@class=\"btnMouse\"]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        WebElement element1 = driver.findElement(By.xpath("(//a[@class=\"listener\"])[1]"));
        actions.moveToElement(element1).click().perform();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Map<String, String> data = Exel2.readexcel2().get("testHover");
        Assert.assertEquals(text, data.get("input1"));
        driver.switchTo().defaultContent();
        WebElement element2 = driver.findElement(By.xpath("(//a[@class=\"listener\"])[2]"));
        actions.moveToElement(element2).click().perform();
        String text1 = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(text1, data.get("input2"));
        driver.switchTo().defaultContent();
        WebElement element3 = driver.findElement(By.xpath("(//a[@class=\"listener\"])[3]"));
        actions.moveToElement(element3).click().perform();
        String text2 = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(text2, data.get("input3"));


    }
    @Test
    public void testLastTableBoss () {
        driver.get("http://www.leafground.com/pages/sorttable.html");


    }


    @AfterTest
    public void tearDown () {
        driver.quit();
    }
}
