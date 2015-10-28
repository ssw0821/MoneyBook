package com.appium.cucumber.Moneybook1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class App1 {

//	protected AppiumDriver driver;
	private AndroidDriver driver;

	@Before
	public void setUp() throws Exception {

		File classpathRoot = new File("D:/Appium_Cucumber_Moneybook/Moneybook");
		File appDir = new File(classpathRoot, "/Downloadapk");
		File app = new File(appDir, "moneybook_1.2.6.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "3b9162a6");
		capabilities.setCapability("platformVersion", "5.0");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.nhn.android.moneybook");
		capabilities.setCapability("unicodeKeyboard", true);
		// capabilities.setCapability("appActivity","com.nhn.android.moneybook");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		

	}

	
	@Given("^아이디 비밀번호 입력 후 로그인$")
	public void 아이디_비밀번호_입력_후_로그인() throws Throwable {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> ID_PW_element = driver
				.findElementsByClassName("android.widget.EditText");
		ID_PW_element.get(0).sendKeys("nvhb21");
		ID_PW_element.get(1).sendKeys("govlqls21");
		driver.findElement(
				By.id("com.nhn.android.moneybook:id/nloginglobal_normal_signin_bt_signin"))
				.click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	@When("^sms자동인식 화면 확인 선택$")
	public void sms자동인식_화면_확인_선택() throws Throwable {

		Thread.sleep(5000);
		driver.findElement(
				By.id("com.nhn.android.moneybook:id/enable_sms_auto_save"))
				.click();
	}

	@Then("^가계부 홈화면 확인$")
	public void 가계부_홈화면_확인() throws Throwable {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		assertTrue(driver.findElement(
				By.id("com.nhn.android.moneybook:id/default_face_icon"))
				.isDisplayed());

		
		driver.findElement(
				By.id("com.nhn.android.moneybook:id/close_noti_for_touch_face_icon"))
				.click();
	}
	
	//TC28
	@Given("^자산현황> 임의의 자산 금액수정 후 저장$")
	public void 자산현황_임의의_자산_금액수정_후_저장() throws Throwable {
		System.out.println("TC28 - 자산현황> 순자산> 각 자산/부채 별 금액 수정");
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/go_statistics_page")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/btnReportAssetStatus")).click();
		
		driver.findElement(By.name("적금통장")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/tv_item_desc")).click();
		Thread.sleep(500);
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/clear_outgo_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).sendKeys("7500");
		for (;;){
            if (driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).getText().equals("7,500. 편집 중입니다.")) {
                        System.out.println("금액 입력 성공");
                        break;
            } else if (driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).getText().equals("7,500")) {
            	System.out.println("금액 입력 성공");
                break;
            }else {            
                driver.findElement(By.id("com.nhn.android.moneybook:id/clear_outgo_amt")).click();
                driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).click();
                driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).sendKeys("7500");
             }                            
        }
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
		Thread.sleep(1000);
		
		assertEquals(driver.findElement(By.id("com.nhn.android.moneybook:id/tv_item_amt")).getText(), "7,500");
		assertEquals(driver.findElement(By.id("com.nhn.android.moneybook:id/tv_item_asset")).getText(), "7,500");
		
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Thread.sleep(500);
	}

	@When("^자산현황> 임의의 부채 금액수정 후 저장$")
	public void 자산현황_임의의_부채_금액수정_후_저장() throws Throwable {
		driver.swipe(182, 1543, 182, 830, 1000);
		driver.swipe(182, 1543, 182, 830, 1000);
		
		driver.findElement(By.name("대출통장")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/tv_item_desc")).click();
		Thread.sleep(500);
		
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/clear_outgo_amt")).click();
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).sendKeys("10000");
		for (;;){
            if (driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).getText().equals("10,000. 편집 중입니다.")) {
                        System.out.println("금액 입력 성공");
                        break;
            } else if (driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).getText().equals("10,000")) {
            	System.out.println("금액 입력 성공");
                break;
            }else {            
                driver.findElement(By.id("com.nhn.android.moneybook:id/clear_outgo_amt")).click();
                driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).click();
                driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).sendKeys("10000");
             }                            
        }
		driver.findElement(By.id("com.nhn.android.moneybook:id/save_outgo_item")).click();
		Thread.sleep(1000);
		
		assertEquals(driver.findElement(By.id("com.nhn.android.moneybook:id/tv_item_amt")).getText(), "10,000");
		assertEquals(driver.findElement(By.id("com.nhn.android.moneybook:id/tv_item_asset")).getText(), "-10,000");
		
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Thread.sleep(500);
	}


	@Then("^자산현황홈> 수정한 카테고리 금액 변경 및 자산/부채 금액 확인$")
	public void 자산현황홈_수정한_카테고리_금액_변경_및_자산_부채_금액_확인() throws Throwable {
		List<WebElement> 내역일치확인하기 = driver.findElementsById("com.nhn.android.moneybook:id/total_amt");
		assertEquals(내역일치확인하기.get(2).getText(), "-15,000");
		assertEquals(내역일치확인하기.get(3).getText(), "-10,000");
		assertEquals(내역일치확인하기.get(4).getText(), "-10,000");
		
		driver.swipe(182, 830, 182, 1543, 1000);
		driver.swipe(182, 830, 182, 1543, 1000);
		
		assertEquals(내역일치확인하기.get(1).getText(), "1,380,000");
		assertEquals(내역일치확인하기.get(5).getText(), "7,500");
		assertEquals(내역일치확인하기.get(6).getText(), "7,500");
	}






	public void Spending() throws Exception{  //지출 클릭, 금액 입력, 분류 선택
		  
		driver.findElement(By.id("com.nhn.android.moneybook:id/type_out")).click(); //지출버튼 클릭
		driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).sendKeys("5000"); //금액 입력
		for (;;){
            if (driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).getText().equals("5,000. 편집 중입니다.")) {
                        System.out.println("금액 입력 성공");
                        break;
            } else if (driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).getText().equals("5,000")) {
            	System.out.println("금액 입력 성공");
                break;
            }else {            
                driver.findElement(By.id("com.nhn.android.moneybook:id/clear_outgo_amt")).click();
                driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).click();
                driver.findElement(By.id("com.nhn.android.moneybook:id/outgo_amt")).sendKeys("5000");
             }                            
        }
		try {
			driver.findElement(By.id("com.nhn.android.moneybook:id/cat_etc")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.id("com.nhn.android.moneybook:id/lcat_btn")).click();
		}		  
	  }

	public void Income() throws Exception{  //수입 클릭, 금액 입력, 분류 선택
		  
		driver.findElement(By.id("com.nhn.android.moneybook:id/income_icon")).click(); //수입버튼 클릭
		driver.findElement(By.id("com.nhn.android.moneybook:id/income_amt")).sendKeys("7000"); //금액 입력
		for (;;){
            if (driver.findElement(By.id("com.nhn.android.moneybook:id/income_amt")).getText().equals("7,000. 편집 중입니다.")) {
                        System.out.println("금액 입력 성공");
                        break;
            } else if (driver.findElement(By.id("com.nhn.android.moneybook:id/income_amt")).getText().equals("7,000")) {
            	System.out.println("금액 입력 성공");
                break;
            }else {            
                driver.findElement(By.id("com.nhn.android.moneybook:id/clear_income_amt")).click();
                driver.findElement(By.id("com.nhn.android.moneybook:id/income_amt")).click();
                driver.findElement(By.id("com.nhn.android.moneybook:id/income_amt")).sendKeys("7000");
             }                            
        }
		try {
			driver.findElement(By.id("com.nhn.android.moneybook:id/cat_etc")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.id("com.nhn.android.moneybook:id/lcat_btn")).click();
		}		  
	  }	
		
	@After
	public void tearDown() throws Exception {
//		driver.quit();

	}
}



//driver.sendKeyEvent(AndroidKeyCode.HOME);
//Thread.sleep(1000);
//driver.findElement(By.name("네이버 가계부")).click();