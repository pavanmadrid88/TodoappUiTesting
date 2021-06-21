package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.base.BaseClass;

import java.io.File;
import java.io.IOException;


public class TestUtils extends BaseClass {

    public TestUtils() throws IOException {
    }

    public static void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File( "/screenshots/" + System.currentTimeMillis() + ".png"));
    }
}
