package haniehsadatiacademy.SeleniumFrameDesign;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import haniehsadatiacademy.pageObjects.CartPage;
import haniehsadatiacademy.pageObjects.ProductCatalog;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {

        landingPage.loginApplication("sara123@yahoo.com","Test1238888884");
        Assert.assertEquals("Incorrect email or password.,,,,", landingPage.getErrorMessage());
	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("sara@yahoo.com","Test1234");
        
        
        //ProductCatalog productCatalog = new ProductCatalog(driver);
		
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();

        //CartPage cartPage= new CartPage(driver);
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
        

	}

}
