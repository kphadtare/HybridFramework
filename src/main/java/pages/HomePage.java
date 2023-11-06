package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;

import utils.Wrapper;

public class HomePage {
	Wrapper objWrapper;

	
	public HomePage() {
		 objWrapper= new Wrapper(); 
	}
	
	By btnAddToCart = By.id("add-to-cart-sauce-labs-backpack");
	By iconCart = By.xpath("//a[@class='shopping_cart_link']");
	By btnCheckout = By.id("checkout");
	By inpFirstName = By.id("first-name");
	By inpLastName = By.id("last-name");
	By inpZip = By.id("postal-code");
	By btnContinue = By.id("continue");
	By checkoutOverview = By.xpath("//span[text()='Checkout: Overview']");
	By btnFinish = By.id("finish");
	By locThankYou = By.xpath("//h2[text()='Thank you for your order!']");
	
	
	
	public void verifyAddToCartBtnDisplayed() {
		boolean result = objWrapper.isDisplayed(btnAddToCart);
		if(result== false) {
			Assert.fail("Failed to Login, Please check user credentials");
		//assert.fail("Failed to Login, Please check user credentials");
			
	}
	}
	
	public void clickAddToCartBtn() {
		objWrapper.sleep(1);
		objWrapper.clickWebElement(btnAddToCart);
		
	}
	
	public void clickCartIcon() {
		objWrapper.sleep(1);
		objWrapper.clickWebElement(iconCart);
		
	}
	
	public void clickCheckoutBtn() {
		objWrapper.sleep(1);
		objWrapper.clickWebElement(btnCheckout);
		
	}
	
	public void enterFirstName(String firstName) {
		objWrapper.sleep(1);
		objWrapper.setText(inpFirstName,  firstName);
		
	}
	public void enterLastname(String lastName) {
		objWrapper.sleep(1);
		objWrapper.setText(inpLastName,  lastName);
		
	}
	public void enterZipCode(String zipCode) {
		objWrapper.sleep(1);
		objWrapper.setText(inpZip,  zipCode);
		
	}
	
	public void verifyCheckoutOverviewDisplayed() {
		objWrapper.sleep(1);
		boolean result = objWrapper.isDisplayed(checkoutOverview);
		assertTrue(result, "Verified Checkout Overview displayed");
	}
	
	public void clickFinishBtn() {
		objWrapper.sleep(1);
		objWrapper.clickWebElement(btnFinish);
		
	}
	
	public void clickContinueBtn() {
		objWrapper.sleep(1);
		objWrapper.clickWebElement(btnContinue);
		
	}
	
	public void verifyOrderPlacedSuccessfully(String successMessge) {
		objWrapper.sleep(1);
		String actualMessage = objWrapper.getText(locThankYou);
		
		assertEquals(actualMessage, successMessge);
		
	}
	

}
