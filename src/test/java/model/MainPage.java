package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    //auth page
    @FindBy(id = "loginEmail")
    private WebElement loginEmailField;

    @FindBy(id = "loginPassword")
    private WebElement loginPasswordField;

    @FindBy(xpath = "//div[@id='authPage']//button")
    private WebElement authSubmitButton;

    @FindBy(id = "invalidEmailPassword")
    private WebElement invalidDataErrorMessage;

    @FindBy(id = "emailFormatError")
    private WebElement emailFormatErrorMessage;

    //inputs page
    @FindBy(id = "dataEmail")
    private WebElement dataEmailField;

    @FindBy(id = "dataName")
    private WebElement nameField;

    @FindBy(id = "dataGender")
    private WebElement select;

    @FindBy(id = "dataCheck11")
    private WebElement checkBox11;

    @FindBy(id = "dataCheck12")
    private WebElement checkBox12;

    @FindBy(id = "dataSelect22")
    private WebElement select22;

    @FindBy(xpath = "//div[@id='inputsPage']//button")
    private WebElement inputSubmitButton;

    @FindBy(xpath = "//div[@class='uk-margin uk-modal-content']")
    private WebElement successMessage;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage fillLoginEmailField(String value) {
        loginEmailField.sendKeys(value);
        return this;
    }

    public MainPage fillPasswordField(String value) {
        loginPasswordField.sendKeys(value);
        return this;
    }

    public MainPage clickLogInButton() {
        authSubmitButton.click();
        return this;
    }

    public MainPage logIn(String email, String password) {
        fillLoginEmailField(email)
                .fillPasswordField(password)
                .clickLogInButton();
        return this;
    }

    public String getInvalidDataMessageText() {
        return invalidDataErrorMessage.getText();
    }

    public String getEmailFormatErrorText() {
        return emailFormatErrorMessage.getText();
    }

    public MainPage fillDataEmailField(String value) {
        dataEmailField.sendKeys(value);
        return this;
    }

    public MainPage fillNameField(String value) {
        nameField.sendKeys(value);
        return this;
    }

    public MainPage selectFromDropdown(String value) {
        new Select(select).selectByVisibleText(value);
        return this;
    }

    public MainPage clickCheckBox11() {
        checkBox11.click();
        return this;
    }

    public MainPage clickCheckBox12() {
        checkBox12.click();
        return this;
    }

    public MainPage clickSelect22() {
        select22.click();
        return this;
    }

    public MainPage clickInputSubmitButton() {
        inputSubmitButton.click();
        return this;
    }

    public boolean isInputSubmitButtonDisplayed() {
        return inputSubmitButton.isDisplayed();
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }

    public void waitSuccessMessageToBeVisible(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
    }
}
