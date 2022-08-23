import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    @DisplayName("Test for any null in first arg")
    public void firstArgNullAndMessageHorseTest(){
        assertEquals("Name cannot be null.", assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1)).getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test for any whitespace chars in first arg")
    @ValueSource (strings = {" ", "", "\n", "\t", "\r"}) //all possible whitespace chars/strings
    public void firstAgrSpaceSymbols (String spaceString){

        assertEquals("Name cannot be blank.", assertThrows(IllegalArgumentException.class, () -> new Horse(spaceString, 1,1)).getMessage());
    }
    @ParameterizedTest
    @DisplayName("Test second arg in Horse constructor, if negative")
    @ValueSource(ints = {-1, -200, -4000})//several negative ints, may use one negative
    public void secondArgNegativeValue(Integer speedValue){
        assertEquals("Speed cannot be negative.", assertThrows(IllegalArgumentException.class, () -> new Horse("Name", speedValue,1)).getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test third arg in Horse constructor, if negative")
    @ValueSource(ints = {-1, -200, -4000})//several negative ints, may use one negative
    public void thirdArgNegativeValue(Integer distanceValue){
        assertEquals("Distance cannot be negative.", assertThrows(IllegalArgumentException.class, () -> new Horse("Name", 1,distanceValue)).getMessage());
    }

    @Test
    public void getNameTest(){
        String initialName = "NameToCheck";
        Horse testHorse = new Horse(initialName, 1, 1);
        assertEquals(initialName, testHorse.getName());
    }

    @Test
    public void getSpeedTest(){
        int initialSpeed = 99;
        Horse testHorse = new Horse("defaultName", initialSpeed, 1);
        assertEquals(initialSpeed, testHorse.getSpeed());
    }

    @Test
    public void getDistanceTest(){
        int initialDistance = 888;
        Horse testHorse = new Horse("defaultName", 1, initialDistance);
        assertEquals(initialDistance, testHorse.getDistance());
    }

    @Test
    public void getDistanceZeroTest(){
        Horse testHorse = new Horse("defaultName", 1);
        assertEquals(0, testHorse.getDistance());
    }

    @Test
    public void moveTest(){
        try(MockedStatic<Horse> testHorse = Mockito.mockStatic(Horse.class)){
            new Horse("testName", 1,1).move();
            testHorse.verify(() ->Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"10.0, 30.0"})
    public void moveTestDistanceCheck(Double speedTest, Double distanceTest){
        try(MockedStatic<Horse> testHorse = Mockito.mockStatic(Horse.class)){
            Horse myHorse = new Horse("testName",speedTest,distanceTest);
            myHorse.move();

            testHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(myHorse.getDistance());
            assertEquals(myHorse.getDistance(), Horse.getRandomDouble(0.2, 0.9));
        }
    }


}
