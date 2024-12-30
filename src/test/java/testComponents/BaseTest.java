package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import haniehsadatiacademy.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	
	public WebDriver initializeDriver() throws IOException
	{
		// in java there is a  properties class which read global properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				              +"\\src\\main\\java\\resources\\GlobalData.properties");

		// we have to load a file
		prop.load(fis);
		
		// to read browser value which is coming from Maven (video 182) ot from global file
		// if property browser exist and it not null in the maven terminal,  it means it passed through Maven-->    
		// regression means run regression test cases
		//     mvn test -PRegression -Dbrowser=Firefox
		//     mvn test -PRegression -Dbrowser=chromeheadless
		// then we have to use the value we got from maven terminal only
		// otherwise we get value from globalData file
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		// give me the browser property
		//String browserName = prop.getProperty("browser");
		
		//if(browserName.equalsIgnoreCase("chrome"))
		if(browserName.contains("chrome"))
		{
			// you can set up any configuration for chrome by creating this object
			ChromeOptions options = new ChromeOptions();
			
			if(browserName.contains("headless"))
			{
				// run test cases in headless mode in chrome
				options.addArguments("headless");				
			}

			
			
			
			// by this code chrome driver will be automatically downloaded into your system based on your chrome driver version
	        WebDriverManager.chromedriver().setup();
	        //driver = new ChromeDriver();
	        
	        // since we want test case to be run in headless mode we need to pass Objects parameter
	        // if headless is not passed options will be null 
	        driver = new ChromeDriver(options);
	        
	        // run it in a full screen mode
	        driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			// write code for edge browser
			driver = new EdgeDriver();
		}
		
		
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

        return driver;
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
	    if (driver != null) {
	        landingPage = new LandingPage(driver);
	        landingPage.goTo();
	        return landingPage;
	    }
	    // Handle the case when driver initialization fails
	    return null;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() 
	{
       //driver.close();
		driver.quit();
	}
	
	
	
	
	
	// video 174-- read Json file and convert it to hashmap
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
		// read the Jason file and convert it to string variable
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert string to hashmap
		// go to MAVEN REPOSITORY and get   "jackson databind"
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		
		return data;
	}
	
	
	
	
	// this method gets screen shot and returns where that screen shot got saved in your local system
	// this method should only be executed when test case fails
	// we need to attach screen shot to HTML report  (extend report)in case of failure
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        // if you want to take screenshop --> you have to convert your webdriver object into screen shot object
		// we have to cast our driver object to take screen shot by using method getScreenshotAs
		// OutputType.FILE   output that screenshot as a file format
		//then it stores screenshot inside SRC object
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir")+ "//reports//" +testCaseName+".png";
		File file = new File(filePath);
		// copy file from source object to your local machine
		FileUtils.copyFile(src, file);
		
		return filePath;
	}
	
	
	
}
