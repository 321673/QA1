package addressbook;

import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class GroupCreationTest {
    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private SelenoidWebDriverProvider swdp;


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        swdp = new SelenoidWebDriverProvider();
        driver = swdp.createDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    public void login(String username, String password) {
        driver.findElement(By.name("user")).click();
        driver.findElement(By.name("user")).clear();
        driver.findElement(By.name("user")).sendKeys(username);
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(password);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
    }

    @Test
    public void testGroups() throws Exception {

        gotoGroupPage("groups");
        initGroupCreation("new");
        fillGroupForm(new GreoupData("Name", "Header", "Footer"));
        submitGroupCreaton();
        returnToGroupPage("group page");
        logout("Logout");
    }

    public void logout(String logout) {
        driver.findElement(By.linkText(logout)).click();
    }

    public void returnToGroupPage(String s) {
        driver.findElement(By.linkText(s)).click();
    }

    public void submitGroupCreaton() {
        driver.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GreoupData greoupData) {
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).clear();
        driver.findElement(By.name("group_name")).sendKeys(greoupData.getName());
        driver.findElement(By.name("group_header")).clear();
        driver.findElement(By.name("group_header")).sendKeys(greoupData.getHeader());
        driver.findElement(By.name("group_footer")).clear();
        driver.findElement(By.name("group_footer")).sendKeys(greoupData.getFooter());
    }

    public void initGroupCreation(String s) {
        driver.findElement(By.name(s)).click();
    }

    public void gotoGroupPage(String groups) {
        driver.findElement(By.linkText(groups)).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
