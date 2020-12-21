package com.backend.questionnow.selenium.userPanelTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UserPanelTest {

    private WebDriver driver;
    private final String URL = "http://localhost:3000";

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Documents\\Projects\\src\\test\\java\\com\\backend\\questionnow\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        login();
    }

    @After
    public void clean() {
        driver.close();
    }

    private void login() {
        driver.get(URL);
        WebElement email = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[1]/input"));
        WebElement password = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[2]/input"));
        email.sendKeys("bartek");
        password.sendKeys("123");
        WebElement signInButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/button"));
        signInButton.click();
    }

    @Test
    public void userPanelTestAfterLogin() {
        List<WebElement> navItems = driver.findElements(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/ul/li"));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(3, navItems.size());
        assertEquals(URL + "/user/questionnaires", currentUrl);

    }

    @Test
    public void logoutTest() {
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[3]/button"));
        logoutButton.click();
        WebElement loginText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/h2"));
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Sign in", loginText.getText());
        assertEquals(URL + "/login", currentUrl);
    }

    @Test
    public void navTest() {
        WebElement createQuestionnaire = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/ul/li[2]/a"));
        createQuestionnaire.click();
        String createQuestionnaireURL = driver.getCurrentUrl();
        WebElement results = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/ul/li[3]/a"));
        results.click();
        String resultsURL = driver.getCurrentUrl();
        assertEquals(URL + "/user/createQuestionnaires", createQuestionnaireURL);
        assertEquals(URL + "/user/results", resultsURL);
    }

    @Test
    public void openQuestionnaireTest() {
        WebElement firstQuestionnaire = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div/div[1]/a"));
        firstQuestionnaire.click();
        WebElement removeButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/button[2]"));
        assertEquals("Remove Questionnaire", removeButton.getText());
    }

    @Test
    public void changeQuestionnaireTittleTest() {
        WebElement firstQuestionnaire = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div/div[1]/a"));
        firstQuestionnaire.click();
        WebElement changeTittle = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/div/label"));
        changeTittle.click();
        WebElement changeTittleInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/div/textarea"));
        changeTittleInput.sendKeys("Change tittle test");
        WebElement saveTittleButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/div/button"));
        saveTittleButton.click();
        WebElement questionnaireTittle = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/div/h1"));
        assertEquals("Change tittle test", questionnaireTittle.getText());
    }

    @Test
    public void changeQuestionnaireQuestionTest() {
        WebElement firstQuestionnaire = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div/div[1]/a"));
        firstQuestionnaire.click();
        WebElement changeQuestion = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[1]/h2"));
        changeQuestion.click();
        WebElement changeQuestionInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[1]/textarea"));
        changeQuestionInput.clear();
        changeQuestionInput.sendKeys("Change question test");
        WebElement saveQuestionButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[1]/button"));
        saveQuestionButton.click();
        WebElement changedQuestion = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[1]/h2"));
        assertEquals("Change question test", changedQuestion.getText());
    }

    @Test
    public void changeQuestionnaireAnswerTest() {
        WebElement firstQuestionnaire = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div/div[1]/a"));
        firstQuestionnaire.click();
        WebElement changeAnswer = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[1]/div[1]/h4"));
        changeAnswer.click();
        WebElement changeAnswerInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[1]/div[1]/textarea"));
        changeAnswerInput.clear();
        changeAnswerInput.sendKeys("Change answer test");
        WebElement saveAnswerButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[1]/div[1]/button"));
        saveAnswerButton.click();
        WebElement changedAnswer = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[1]/div[1]/h4"));
        assertEquals("Change answer test", changedAnswer.getText());
    }

    @Test
    public void addQuestionTest() {
        WebElement firstQuestionnaire = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div/div[1]/a"));
        firstQuestionnaire.click();
        List<WebElement> questions = driver.findElements(By.className("QuestionWithDeleteButton"));
        int sizeBefore = questions.size();
        WebElement addQuestionButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/button[3]"));
        addQuestionButton.click();
        WebElement questionInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/form/input[1]"));
        WebElement questionNumberInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/form/input[2]"));
        WebElement saveQuestionButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/form/button"));
        questionInput.sendKeys("Question test");
        questionNumberInput.sendKeys("1");
        saveQuestionButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        List<WebElement> questionsAfter = driver.findElements(By.className("QuestionWithDeleteButton"));
        int sizeAfter = questionsAfter.size();
        assertTrue(sizeBefore < sizeAfter);
    }

    @Test
    public void addAnswerTest() {
        WebElement firstQuestionnaire = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div/div[1]/a"));
        firstQuestionnaire.click();
        List<WebElement> answers = driver.findElements(By.className("AnswerWithButton"));
        int sizeBefore = answers.size();
        WebElement addAnswerButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/div[2]/button"));
        addAnswerButton.click();
        WebElement answerInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/form/input[1]"));
        WebElement answerNumberInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/form/input[2]"));
        WebElement nextQuestionNumberInput = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/form/input[3]"));
        answerInput.sendKeys("Answer test");
        answerNumberInput.sendKeys("1");
        nextQuestionNumberInput.sendKeys("2");
        WebElement saveAnswer = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[3]/div[2]/form/button"));
        saveAnswer.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        List<WebElement> answersAfter = driver.findElements(By.className("AnswerWithButton"));
        int sizeAfter = answersAfter.size();
        assertTrue(sizeBefore < sizeAfter);
    }

    @Test
    public void createQuestionnaireTest() {
        List<WebElement> questionnaires = driver.findElements(By.className("QuestionnaireShort"));
        int sizeBefore = questionnaires.size();
        WebElement createQuestionnaireNav = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/ul/li[2]/a"));
        createQuestionnaireNav.click();
        WebElement createQuestionnaireButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/button[2]"));
        createQuestionnaireButton.click();
        List<WebElement> questionnairesAfter = driver.findElements(By.className("QuestionnaireShort"));
        int sizeAfter = questionnairesAfter.size();
        assertTrue(sizeBefore < sizeAfter);
    }
}
