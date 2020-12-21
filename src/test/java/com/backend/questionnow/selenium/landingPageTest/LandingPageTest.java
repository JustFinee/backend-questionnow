package com.backend.questionnow.selenium.landingPageTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LandingPageTest {
    private WebDriver driver;
    private final String URL = "http://localhost:3000";

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Documents\\Projects\\src\\test\\java\\com\\backend\\questionnow\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void clean() {
        driver.close();
    }

    @Test
    public void loginPageTest() {
        driver.get(URL);
        String currentUrl = driver.getCurrentUrl();
        WebElement loginForm = driver.findElement(By.className("LoginForm"));
        assertEquals("div", loginForm.getTagName());
        assertEquals(URL + "/login", currentUrl);
    }

    @Test
    public void registerPageTest() {
        driver.get(URL);
        WebElement registerPage = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/h3/a"));
        registerPage.click();
        WebElement signUpText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/h2"));
        List<WebElement> inputs = driver.findElements(By.className("InputField"));
        assertEquals("Sign up", signUpText.getText());
        System.out.println(inputs.get(0));
        assertEquals(3, inputs.size());
    }

    @Test
    public void loginIntoApplicationTest() {
        driver.get(URL);
        WebElement email = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[1]/input"));
        WebElement password = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[2]/input"));
        email.sendKeys("bartek");
        password.sendKeys("123");
        WebElement signInButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/button"));
        signInButton.click();
        WebElement myQuestionnairesText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/h1"));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(URL + "/user/questionnaires", currentUrl);
        assertEquals("My Questionnaires", myQuestionnairesText.getText());
    }

    @Test
    public void registerAlreadyCreatedAccountTest() {
        driver.get(URL);
        WebElement registerPage = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/h3/a"));
        registerPage.click();
        WebElement nameInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[1]/input"));
        WebElement emailInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[2]/input"));
        WebElement passwordInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[3]/input"));
        WebElement registerButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/button"));
        nameInput.sendKeys("Bartek");
        emailInput.sendKeys("test@gmail.com");
        passwordInput.sendKeys("1234567");
        registerButton.click();
        WebElement errorText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/p"));
        assertEquals("Email already is used", errorText.getText());
    }
}
