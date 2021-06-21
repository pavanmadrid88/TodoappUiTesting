package pages.todo;

import com.paulhammant.ngwebdriver.ByAngularModel;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BaseClass;

import java.io.IOException;
import java.util.List;

public class TodoPage extends BaseClass {

    @ByAngularModel.FindBy(model = "formData.text")
    WebElement inputTextBox;

    @FindBy(xpath = "//h1[contains(text(),'ToDo')]")
    WebElement headerElement;

    @FindBy(xpath = "//span[contains(@class,'label')]")
    WebElement toDoListCountElement;

    @FindBy(xpath = "//button[text() = 'Add']")
    WebElement addButton;

    private static final Logger logger = LoggerFactory.getLogger(TodoPage.class);
    private By deleteTodoListElementCheckboxXpath = By.xpath("//div[@id='todo-list']//input[@type='checkbox']");
    WebDriverWait wait = new WebDriverWait(webDriver, 10);


    public TodoPage() throws IOException {
        super();
        PageFactory.initElements(webDriver, this);
        ngWebDriver.waitForAngularRequestsToFinish();
    }


    public void enterTodoItem(String item) {
        logger.info("Setting text box");
        wait.until(ExpectedConditions.visibilityOf(inputTextBox));
        inputTextBox.sendKeys(item);
    }

    private Boolean isHeaderElementVisible() {
        return headerElement.isDisplayed();
    }

    private Boolean isToDoListCountElementVisible() {
        return toDoListCountElement.isDisplayed();
    }

    private Boolean isAddButtonVisible() {
        return addButton.isDisplayed();
    }


    //******* Public Methods***************//
    public void clickAddButton() {
        wait.until(ExpectedConditions.visibilityOf(addButton));
        addButton.click();
    }

    public Boolean checkDisplayOfBasicElementsInPage() {
        return (isHeaderElementVisible() && isHeaderElementVisible() && isAddButtonVisible() && isToDoListCountElementVisible());
    }

    public int getCurrentTodoAddedListCount() {
        wait.until(ExpectedConditions.visibilityOf(toDoListCountElement));
        logger.info("returning current count as :" + toDoListCountElement.getText());
        return Integer.parseInt(toDoListCountElement.getText());
    }

    public void clearAllTodoListItems() throws InterruptedException {
        logger.info("Clearing all to do list items if any...");
        List<WebElement> toDoListElementCheckboxes = webDriver.findElements(deleteTodoListElementCheckboxXpath);

        for (int i = 0; i < toDoListElementCheckboxes.size(); i++) {
            for (int j = 0; j < 20; j++) {
                try {
                    webDriver.findElement(By.xpath("//input[@type='checkbox']")).click();

                    break;
                } catch (StaleElementReferenceException se) {
                    logger.info("handling stale element reference exception:" + i);
                }
            }
        }
    }


}
