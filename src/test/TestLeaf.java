package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLeaf {



    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.leafground.com/home.html");
        WebElement edit = driver.findElement(By.xpath("//a[@href='pages/Edit.html']"));
        edit.click();
        WebElement textbox1 = driver.findElement(By.id("email"));
        textbox1.sendKeys("test@gmail.com");
        WebElement textbox2 = driver.findElement(By.xpath("//input[@value='Append '"));
        textbox2.sendKeys("test user");
        textbox2.sendKeys(Keys.TAB);
        WebElement textbox3 = driver.findElement(By.xpath("//input[@name='username' and@value='TestLeaf']"));
        String defaultValue = textbox3.getAttribute("value");
        System.out.println(defaultValue);
        WebElement textbox4 = driver.findElement(By.xpath("//input[@name='username' and @value='Clear me']"));
        textbox4.clear();
        WebElement textbox5 = driver.findElement(By.xpath("//input[@style=\"width:350px;background-color:LightGrey;\"]"));
        String disabled = textbox5.getAttribute("disabled");
        System.out.println(disabled);
        //botton
        WebElement button = driver.findElement(By.xpath("//a[@href=\"pages/Button.html\"]"));
        button.click();
        WebElement homepage = driver.findElement(By.xpath("//button[@id=\"home\"]"));
        WebElement position = driver.findElement(By.xpath("//button[@id=\"position\"]"));
        WebElement color = driver.findElement(By.xpath("//button[@id=\"color\"]"));
        WebElement size = driver.findElement(By.xpath("//button[@id=\"size\"]"));
        // Hyperlink
        WebElement hyperlink = driver.findElement(By.xpath("//a[@href=\"pages/Link.html\"]"));
        hyperlink.click();
        WebElement home = driver.findElement(By.xpath("(//a[@href=\"../home.html\"and text() = 'Go to Home Page'])[1]"));
        WebElement home2 = driver.findElement(By.xpath("(//a[@href=\"../home.html\"and text() = 'Go to Home Page'])[2]"));
        WebElement find = driver.findElement(By.xpath("//a[@href=\"Button.html\"]"));
        WebElement last = driver.findElement(By.xpath("//a[@href=\"error.html\"]"));
        //Image
        WebElement image = driver.findElement(By.xpath("//a[@href=\"pages/Image.html\"]"));
        image.click();
        WebElement homeimage = driver.findElement(By.xpath("//img[@src=\"../images/home.png\"]"));
        WebElement brokenimage = driver.findElement(By.xpath("//img[@src=\"../images/abcd.jpg\"]"));
        WebElement keyboard = driver.findElement(By.xpath("//img[@src=\"../images/keyboard.png\"]"));
        //dropdown
        WebElement dropdown = driver.findElement(By.xpath("//a[@href=\"pages/Dropdown.html\"]"));
        dropdown.click();
        WebElement index = driver.findElement(By.xpath("//select[@id=\"dropdown1\"]"));
        WebElement text = driver.findElement(By.xpath("//select[@name=\"dropdown2\"]"));
        WebElement value = driver.findElement(By.xpath("//select[@name=\"dropdown3\"]"));
        WebElement options = driver.findElement(By.xpath("//select[@class=\"dropdown\"]"));
        WebElement select = driver.findElement(By.xpath("//option[@selected=\"selected\"and@disabled=\"true\"]"));
        //radiobutton
        WebElement radio = driver.findElement(By.xpath("//a[@href=\"pages/radio.html\"]"));
        radio.click();
        WebElement first = driver.findElement(By.xpath("(//div[@class=\"large-6 small-12 columns\"])[1]"));
        WebElement second = driver.findElement(By.xpath("(//div[@class=\"large-6 small-12 columns\"])[2]"));
        WebElement third = driver.findElement(By.xpath("(//div[@class=\"large-6 small-12 columns\"])[3]"));
        //checkbox
        WebElement checkbox = driver.findElement(By.xpath("//a[@href=\"pages/checkbox.html\"]"));
        checkbox.click();
        WebElement languages = driver.findElement(By.xpath("(//div[@class=\"example\"])[1]"));
        WebElement checked = driver.findElement(By.xpath("(//div[@class=\"example\"])[2]"));
        WebElement deselect = driver.findElement(By.xpath("(//div[@class=\"example\"])[3]"));
        WebElement below = driver.findElement(By.xpath("(//div[@class=\"example\"])[4]"));
        //table
        WebElement table = driver.findElement(By.xpath("//a[@href=\"pages/table.html\"]"));
        table.click();
        //alert
        WebElement alert = driver.findElement(By.xpath("//a[@href=\"pages/Alert.html\"]"));
        alert.click();
        WebElement alertbox = driver.findElement(By.xpath("//button[@onclick=\"normalAlert()\"]"));
        WebElement confirmbox = driver.findElement(By.xpath("//button[@onclick=\"confirmAlert()\"]"));
        WebElement promptbox = driver.findElement(By.xpath("//button[@onclick=\"confirmPrompt()\"]"));
        WebElement linebox = driver.findElement(By.xpath("//button[@onclick=\"lineBreaks()\"]"));
        WebElement sweetalert = driver.findElement(By.xpath("//button[@onclick=\"sweetalert()\"]"));
        //frame
        WebElement frame = driver.findElement(By.xpath("//a[@href=\"pages/frame.html\"]"));
        frame.click();
        WebElement frame1 = driver.findElement(By.xpath("//button[@id=\"Click\"]"));
        WebElement frame2 = driver.findElement(By.xpath("//button[@id=\"Click1\"]"));
        WebElement frame3 = driver.findElement(By.xpath("(//div[@id=\"wrapframe\"])[3]"));
        //window
        WebElement window = driver.findElement(By.xpath("//a[@href=\"pages/Window.html\"]"));
        window.click();
        WebElement homewindow = driver.findElement(By.xpath("//button[@id=\"home\"]"));
        WebElement openwindow = driver.findElement(By.xpath("//button[@onclick=\"openWindows()\"]"));
        WebElement donotclosewindow = driver.findElement(By.xpath("//button[@style=\"background-color:lightgreen\"]"));
        WebElement lastwindow = driver.findElement(By.xpath("//button[@onclick=\"openWindowsWithWait();\"]"));
        //callendar
        WebElement calendar = driver.findElement(By.xpath("//a[@href=\"pages/Calendar.html\"]"));

        driver.quit();







    }
}
