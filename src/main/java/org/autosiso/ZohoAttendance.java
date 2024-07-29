package org.autosiso;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ZohoAttendance {
  public static void main(String[] args) throws InterruptedException,IOException {

    System.out.println("App started successfully....");
    LocalDate localDate = LocalDate.now();
    int currentDay = localDate.getDayOfWeek().getValue();

    LocalTime localTime = LocalTime.now();
    int currentHour = localTime.getHour();

    UserInfo user = new UserInfo();
    Holiday holiday = new Holiday();

    Map<String,String> userInfo = user.getUserInfo();
    List<LocalDate> listOfHoliday = holiday.getListHoliday();

    if(listOfHoliday.stream().noneMatch(s -> s.equals(localDate)) && currentDay <= 5 ) {
        for (Map.Entry<String, String> entry : userInfo.entrySet())
          automateLogIn(entry.getKey(),entry.getValue(),currentHour);
    }
  }

  private static void automateLogIn(String userEmail, String pass,int currentHour) throws InterruptedException,IOException{
      System.out.println("username = " + userEmail);
      ChromeOptions options = new ChromeOptions();

//      options.addArguments("--headless");
//      options.addArguments("--no-sandbox");
//      options.addArguments("--disable-dev-shm-usage");
      System.out.println("Starting driver.....");
      WebDriver driver = new ChromeDriver(options);

      driver.get("https://accounts.zoho.in/signin?servicename=zohopeople&signupurl=https://www.zoho.com/people/signup.html");
      Thread.sleep(5000);
      driver.findElement(By.xpath("//*[@id=\"login_id\"]")).sendKeys(userEmail);
      driver.findElement(By.xpath("//*[@id=\"nextbtn\"]")).submit();
      Thread.sleep(10000);
      driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pass);
      driver.findElement(By.xpath("//*[@id=\"nextbtn\"]")).submit();
      Thread.sleep(30000);
      String check = driver.findElement(By.xpath("//*[@id=\"ZPD_Top_Att_Stat\"]")).getText();
      SendGridEmailer sendEmail = new SendGridEmailer();
      if(check.equals("Check-in") && currentHour>=9 && currentHour<10){
        triggerCheckInCheckOut(driver);
        try {
          System.out.println("Sending sign-in mail.....");
          sendEmail.sendSignInEmail(userEmail);
        }catch (IOException e){
          throw new IOException(e);
        }
      }else if (check.equals("Check-in") && currentHour>=10 && currentHour<11) {
        try {
          System.out.println("Due to some problem Sign-in was not successfully.....");
          sendEmail.sendEmailToCheckInManually(userEmail);
        } catch (IOException e) {
          throw new IOException(e);
        }
      }else if(check.equals("Check-out") && currentHour>=18 && currentHour<19) {
        triggerCheckInCheckOut(driver);
        try {
          System.out.println("Sending sign-out mail.....");
          sendEmail.sendSignOutEmail(userEmail);
        } catch (IOException e) {
          throw new IOException(e);
        }
      }


    driver.findElement(By.xpath("//*[@id=\"zpeople_userimage\"]")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//*[@id=\"zp_modal_body\"]/div/div[1]/div/a[2]")).click();
    Thread.sleep(5000);
    System.out.println("Closing driver.....");
    driver.close();
  }

  private static void triggerCheckInCheckOut(WebDriver driver) throws InterruptedException{
    driver.findElement(By.xpath("//*[@id=\"ZPD_Top_Att_Stat\"]")).click();
    Thread.sleep(5000);
  }
}


