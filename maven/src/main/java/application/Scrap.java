package application;

import bo.Game;
import database.GameDAO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Scrap {

    static final String URL = "https://www.unibet.fr/sport/football/espagne/liga";

    public static void main (String[] args) throws ParseException {
        // Declaration and instantiation of driver and URL /////////////////////////////////////////////////////////////

        System.setProperty("webdriver.gecko.driver", "drivers/geckoWin64.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);  // <-- headless set here
        WebDriver driver = new FirefoxDriver(options);
        driver.get(URL);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Clean game table ////////////////////////////////////////////////////////////////////////////////////////////
        //GameDAO gameDAO = new GameDAO();
        //gameDAO.deleteTable();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Assuming full loading of game events ////////////////////////////////////////////////////////////////////////
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bettingbox-date")));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Fetch all available games ///////////////////////////////////////////////////////////////////////////////////
        List<WebElement> fullGamesList = driver.findElements(By.className("bettingbox-item")); // Fetch all days (available)
        for(WebElement dayGamesList : fullGamesList){
            String gameDay = dayGamesList.findElement(By.className("bettingbox-date")).getText(); // Get date of the game
            List<WebElement> gamesOnThisDay = dayGamesList.findElements(By.className("calendar-event")); // List of all games for a single day
            for(WebElement game : gamesOnThisDay){
                System.out.println(game.getAttribute("innerHTML"));
                Game g = new Game(
                        fetchHomeTeam(game.findElement(By.className("cell-event")).getText()),
                        fetchAwayTeam(game.findElement(By.className("cell-event")).getText()),
                        convertStringDate(gameDay),
                        game.findElement(By.className("competition")).getText(),
                        game.findElement(By.className("datetime")).getText(),
                        Double.parseDouble(game.findElements(By.className("odd-price")).get(1).getText()),
                        Double.parseDouble(game.findElements(By.className("odd-price")).get(3).getText()),
                        Double.parseDouble(game.findElements(By.className("odd-price")).get(5).getText())
                        );
                GameDAO gDAO = new GameDAO();
                gDAO.insert(g);
            }
         }
        driver.close();
    }

    public static String fetchHomeTeam(String gameString){
        String substr = " - ";
        return gameString.substring(0, gameString.indexOf(substr));
    }

    public static String fetchAwayTeam(String gameString){
        String substr = " - ";
        return gameString.substring(gameString.indexOf(substr) + substr.length());
    }

    public static String convertStringDate(String dateString) throws ParseException {
        Calendar now = Calendar.getInstance();
        int yearInt = now.get(Calendar.YEAR);
        String year = String.valueOf(yearInt);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM", Locale.FRENCH);
        Date gameDate = formatter.parse(dateString);
        DateFormat targetFormat = new SimpleDateFormat("dd/MM/"+year);
        return targetFormat.format(gameDate);
    }
}
