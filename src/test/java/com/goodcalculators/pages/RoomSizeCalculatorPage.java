package com.goodcalculators.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RoomSizeCalculatorPage {
    WebDriver driver;
    public RoomSizeCalculatorPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (xpath = "//a[contains(text(),'Other Calculators')]") public WebElement otherCal;
    //locator for conversion between different units
   @FindBy (id="calculate-ft") public WebElement selectFeetUnit;
   @FindBy (id="calculate-mt") public WebElement selectMetersUnit;
   //locators for area-meters unit
   @FindBy (id="wth-m1") public WebElement firstWidthMeter;
   @FindBy (id="len-m1") public WebElement firstLengthMeter;
   @FindBy (id="wth-m2") public WebElement secondWidthMeter;
   @FindBy (id="len-m2") public WebElement secondLengthMeter;
    //locators for area-feet unit
    @FindBy (id="w-ft1") public WebElement firstWidthFeet;
    @FindBy (id="w-in1") public WebElement firstWidthInch;
    @FindBy (id="len-ft1") public WebElement firstLengthFeet;
    @FindBy (id="len-in1") public WebElement firstLengthInch;
    @FindBy (id="w-ft2") public WebElement secondWidthFeet;
    @FindBy (id="w-in2") public WebElement secondWidthInch;
    @FindBy (id="len-ft2") public WebElement secondLengthFeet;
    @FindBy (id="len-in2") public WebElement secondLengthInch;

   @FindBy (xpath = "//button[contains(text(),'Calculate')]") public WebElement submitAndCalculate;
   @FindBy (xpath = "//button[contains(text(),'Clear')]") public WebElement clearButton;
   @FindBy(id = "totarea-res") public WebElement totalArea;
   @FindBy(id = "totarea-res-10-perc") public WebElement totalAreaWithWastage;

   @FindBy(xpath = "//div[@id='ezmobfooter']/span") public WebElement alertAdvert;

   public long findAreaInMeter(int firstWidth,int firstLength,int secondWidth,int secondLength){
       selectMetersUnit.click();
       firstWidthMeter.clear();
       firstWidthMeter.sendKeys(String.valueOf(firstWidth));
       firstLengthMeter.clear();
       firstLengthMeter.sendKeys(String.valueOf(firstLength));
       secondWidthMeter.clear();
       secondWidthMeter.sendKeys(String.valueOf(secondWidth));
       secondLengthMeter.clear();
       secondLengthMeter.sendKeys(String.valueOf(secondLength));
       long area=firstLength*firstWidth+secondLength*secondWidth;
       return area;
   }
   public double returnDoubleTotalArea(){
       String num=totalArea.getText();
       String numberPart=num.substring(0,num.indexOf(" "));
       Double doubleNumber=Double.valueOf(numberPart);
       return doubleNumber;
   }
    public int returnIntegerTotalArea(){
        String num=totalArea.getText();
        String numberPart=num.substring(0,num.indexOf(" "));
        Integer intNumber=Integer.valueOf(numberPart);
        return intNumber;
    }
    public double returnDoubleTotalAreaWithWastage(){
        String num=totalAreaWithWastage.getText();
        String numberPart=num.substring(0,num.indexOf(" "));
        Double doubleNumber=Double.valueOf(numberPart);
        return doubleNumber;
    }
    public String returnUnitForSelected(){
       String textUnit=totalArea.getText();
       String unitPart=textUnit.substring(textUnit.indexOf(".")+2);
        return unitPart;
    }
}
