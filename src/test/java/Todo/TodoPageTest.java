package Todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.base.BaseClass;
import pages.todo.TodoPage;

import java.io.IOException;

public class TodoPageTest extends BaseClass {

    TodoPage todoPage;
    private static final Logger logger = LoggerFactory.getLogger(TodoPageTest.class);

    public TodoPageTest() throws IOException {
        super();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        logger.info("Enter Test Setup");
        init();
        todoPage = new TodoPage();
    }


    @Test
    public void todoPageSanity() {
        logger.info("Test:To do page sanity check");
        Assert.assertTrue(todoPage.checkDisplayOfBasicElementsInPage(), "Validating if basic elements/widgets are displayed in Todopage");
    }

    @Test
    public void addSingleTodoList() throws InterruptedException {
        logger.info("Test: adding single item to Todo list");
        todoPage.clearAllTodoListItems();
        todoPage.enterTodoItem(properties.get("singleItemTodoList").toString());
        todoPage.clickAddButton();
        Assert.assertEquals(todoPage.getCurrentTodoAddedListCount(), 1, "Validating the todo list count for a single item addition. Found:" + todoPage.getCurrentTodoAddedListCount());
    }

    @Test
    public void addMultipleTodoList() throws InterruptedException {

        logger.info("Test: adding multiple items to Todo list");
        todoPage.clearAllTodoListItems();
        String[] itemsToAdd = properties.getProperty("multipleItemsTodoList").toString().split(";");
        for (int i = 0; i < itemsToAdd.length; i++) {
            String itemToAdd = itemsToAdd[i];
            todoPage.enterTodoItem(itemToAdd);
            todoPage.clickAddButton();
        }

        Assert.assertEquals(itemsToAdd.length, todoPage.getCurrentTodoAddedListCount(), "Validating the todo list count for multiple addition.Found:" + todoPage.getCurrentTodoAddedListCount());
    }

    @Test
    public void addMultipleItemsAndDeleteThemAll() throws InterruptedException {

        logger.info("Test: adding multiple items to Todo list and clearing them all");
        addMultipleTodoList();
        todoPage.clearAllTodoListItems();
        Assert.assertEquals(todoPage.getCurrentTodoAddedListCount(), 0, "Validating the todo list count after clearing all items.Found:" + todoPage.getCurrentTodoAddedListCount());

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        todoPage.clearAllTodoListItems();
        logger.info("Enter Test TearDown");
        webDriver.close();
        webDriver.quit();
    }


}
