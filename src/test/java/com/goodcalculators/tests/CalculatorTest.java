package com.goodcalculators.tests;

import com.goodcalculators.pages.RoomSizeCalculatorPage;
import com.goodcalculators.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CalculatorTest {
    private WebDriver driver;
    RoomSizeCalculatorPage roomCal;
    @BeforeMethod
    public void setup(){

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        /*WebDriverManager.firefoxdriver().setup();
        driver=new FirefoxDriver();*/
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.get("https://goodcalculators.com/room-size-calculator/");
        BrowserUtils.waitPlease(2);

    }
    @AfterMethod
    public void tearDOwn(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    /**
     * Test for meter unit
     * We can use same type of test to validate feet unit too
     */


    @Test (dataProvider = "AreaLengthAndWidth")
    public void findAreaForAllFieldsInMeterTest(int firstWidth,int firstLength,int secondWidth,int secondLength){
        roomCal =new RoomSizeCalculatorPage(driver);
        long area= roomCal.findAreaInMeter(firstWidth,firstLength,secondWidth,secondLength);
        roomCal.submitAndCalculate.click();
        Assert.assertEquals(roomCal.returnIntegerTotalArea(),area);
        Assert.assertEquals(roomCal.returnDoubleTotalAreaWithWastage(),area*1.1);
    }
    @Test
    public void afterClickOnClearButtonAreasBeZero(){
        roomCal =new RoomSizeCalculatorPage(driver);
        roomCal.selectMetersUnit.click();
        roomCal.clearButton.click();
        Assert.assertEquals(roomCal.returnIntegerTotalArea(), 0);
        Assert.assertEquals(roomCal.returnDoubleTotalAreaWithWastage(), 0);
    }
    @Test
    public void meterUnitConfirmationTest(){
        roomCal =new RoomSizeCalculatorPage(driver);
        BrowserUtils.waitForClickablility(driver,roomCal.selectMetersUnit,10).click();
        roomCal.submitAndCalculate.click();
        Assert.assertEquals(roomCal.returnUnitForSelected(),"m.");

    }
    @Test
    public void feetUnitConfirmationTest(){
        roomCal =new RoomSizeCalculatorPage(driver);
        BrowserUtils.waitForClickablility(driver,roomCal.selectFeetUnit,10).click();
        roomCal.submitAndCalculate.click();
        Assert.assertEquals(roomCal.returnUnitForSelected(),"ft.");
    }

    @DataProvider (name="AreaLengthAndWidth")
    public static Object[][] getData(){
        return new Object[][]{
                {5,2,10,20},
                {2,10,10,10},
                {100,50,150,60},
                {1,1,1,1}
        };
    }
}
