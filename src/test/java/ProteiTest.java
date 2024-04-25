import base.BaseTest;
import model.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProteiTest extends BaseTest {
    private Logger logger = (Logger) LogManager.getLogger(ProteiTest.class);

    @Test
    public void testLoginHappyPath() {
        boolean actualResult =
                openURL()
                        .fillLoginEmailField(getLogin())
                        .fillPasswordField(getPassword())
                        .clickLogInButton()
                        .isInputSubmitButtonDisplayed();

        Assertions.assertTrue(actualResult);
        logger.info("testLoginHappyPath successfully");
    }

    @Test
    public void testLogInNegativeUpperCasePassword() {
        boolean actualResult =
                openURL()
                        .fillLoginEmailField(getLogin())
                        .fillPasswordField(getPassword().toUpperCase())
                        .clickLogInButton()
                        .isInputSubmitButtonDisplayed();

        Assertions.assertFalse(actualResult);
        logger.info("LogInNegativeUpperCasePassword successfully");
    }

    @Test
    public void verifyLogInErrorMessageWrongPassword() {
        String actualErrorMessage =
                openURL()
                        .logIn(getLogin(), "wrongPass")
                        .clickLogInButton()
                        .getInvalidDataMessageText();

        Assertions.assertEquals(actualErrorMessage, "Неверный E-Mail или пароль");
        logger.info("verifyLogInErrorMessageWrongPassword successfully");
    }

    @Test
    public void verifyLogInErrorMessageNoData() {
        String actualErrorMessage =
                openURL()
                        .clickLogInButton()
                        .getEmailFormatErrorText();

        Assertions.assertEquals(actualErrorMessage, "Неверный формат E-Mail");
        logger.info("verifyLogInErrorMessageNoData successfully");
    }


    @Test
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
        logger.info("testFormE2EHappyPath successfully");
    }

    @Test
    public void verifyEmptyFormErrorMessage() {
        openURL()
                .logIn(getLogin(), getPassword())
                .clickInputSubmitButton();

        Assertions.assertEquals(new MainPage(getDriver()).getEmailFormatErrorText(), "Неверный формат E-Mail");
        logger.info("verifyEmptyFormErrorMessage successfully");
    }
}
