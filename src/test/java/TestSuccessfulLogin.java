import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Артем on 12.03.2017.
 */
public class TestSuccessfulLogin {

    WebDriver webDriver = null;
    private String login;
    private String password;

    @Parameters({"login", "password"})
    @BeforeTest
    public void setup(String login, String password){
        webDriver = new FirefoxDriver();
        webDriver.get("http://webmail.itransition.com/");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.login = login;
        this.password = password;
    }

    @Test
    public void testLogin() throws Exception{
        webDriver.findElement(By.id("username")).sendKeys(login);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("SubmitCreds")).click();
        webDriver.findElement(By.cssSelector("button[aria-label^=Меню]")).click();

        String email = webDriver.findElement(By.cssSelector("div._pe_02 span[autoid=_pe_p]")).getText();
        assertTrue(email.contains(login));
    }

    @AfterTest
    public void teardown(){
        webDriver.quit();
    }
}
