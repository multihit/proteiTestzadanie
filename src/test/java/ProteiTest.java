import base.BaseTest;
import model.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProteiTest extends BaseTest {
    private final Logger logger = (Logger) LogManager.getLogger();

    @Test // Проверка авторизации с корректными данными
    public void testLoginTruth() {
        boolean actualResult =
                openURL()
                        .fillLoginEmailField(getLogin())
                        .fillPasswordField(getPassword())
                        .clickLogInButton()
                        .isInputSubmitButtonDisplayed();

        Assertions.assertTrue(actualResult);
        logger.info("testLoginTruth successfully, the second form has opened ");
    }

    @Test // Проверка авторизации, пароль в верхнем регистре
    public void testLoginNegativeUpperCasePassword() {
        boolean actualResult =
                openURL()
                        .fillLoginEmailField(getLogin())
                        .fillPasswordField(getPassword().toUpperCase())
                        .clickLogInButton()
                        .isInputSubmitButtonDisplayed();

        Assertions.assertFalse(actualResult);
        logger.info("LogInNegativeUpperCasePassword successfully, wrong password ");
    }

    @Test // Проверка авторизации, пароль неверен
    public void verifyLoginErrorMessageWrongPassword() {
        String actualErrorMessage =
                openURL()
                        .logIn(getLogin(), "wrongPass")
                        .clickLogInButton()
                        .getInvalidDataMessageText();

        Assertions.assertEquals(actualErrorMessage, "Неверный E-Mail или пароль");
        logger.info("verifyLoginErrorMessageWrongPassword successfully, message done");
    }

    @Test // Проверка авторизации, без ввода данных
    public void verifyLoginErrorMessageNoData() {
        String actualErrorMessage =
                openURL()
                        .clickLogInButton()
                        .getEmailFormatErrorText();

        Assertions.assertEquals(actualErrorMessage, "Неверный формат E-Mail");
        logger.info("verifyLoginErrorMessageNoData successfully, message done");
    }


    @Test // Прогон теста от начала и до конца
    public void testFormE2EHappyPath() {
        openURL()
                .logIn(getLogin(), getPassword())
                .fillDataEmailField("youshould@hire.me")
                .fillNameField("Alex")
                .selectFromDropdown("Мужской")
                .clickCheckBox11()
                .clickCheckBox12()
                .clickSelect22()
                .clickInputSubmitButton()
                .waitSuccessMessageToBeVisible(getWait());


        Assertions.assertEquals(new MainPage(getDriver()).getSuccessMessageText(), "Данные добавлены.");
        logger.info("the test is passed completely");
    }

    @Test // Проверка без ввода данных во второй форме
    public void verifyEmptyFormErrorMessage() {
        openURL()
                .logIn(getLogin(), getPassword())
                .clickInputSubmitButton();

        Assertions.assertEquals(new MainPage(getDriver()).getEmailFormatErrorText(), "Неверный формат E-Mail");
        logger.info("verifyEmptyFormErrorMessage successfully");

    }
}
