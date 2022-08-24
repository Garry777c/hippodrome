import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    @DisplayName("Test for any null in arg")
    public void checkNullHippodromeTest(){
        assertEquals("Horses cannot be null.", assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null)).getMessage());

    }

    @Test
    @DisplayName("Test for any empty List")
    public void checkEmptyListHippodromeTest(){
        assertEquals("Horses cannot be empty.", assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>())).getMessage());
    }

    @Test
    @DisplayName("Test to check line of Horses")
    public void getHorsesTest(){
        List<Horse> listToCheck = new ArrayList<>();
                for (int i=1; i<=30; i++){
                    listToCheck.add(new Horse("Name "+i, i+1, i+10));
                }

            Hippodrome hippodromeToTest = new Hippodrome(listToCheck);

        assertEquals(listToCheck, hippodromeToTest.getHorses());
    }

    @Test
    @DisplayName("Test to check move for each Horse")
    public void moveTest(){
        List<Horse> listToCheck = new ArrayList<>();
        for (int i=1; i<=50; i++){
            listToCheck.add(mock(Horse.class));
        }

        Hippodrome hippodromeToTest = new Hippodrome(listToCheck);
        hippodromeToTest.move();

        for (Horse testHorse : listToCheck) {
            verify(testHorse).move();
        }
    }

    @Test
    @DisplayName("Test to check getWinner")
    public void hetWinnerTest(){
        List<Horse> listToCheck = new ArrayList<>();
        for (int i=1; i<=10; i++){
            listToCheck.add(new Horse("Name "+i, 1, i*10));
        }

        Hippodrome hippodromeTest = new Hippodrome(listToCheck);

        assertSame(listToCheck.get(9), hippodromeTest.getWinner());
    }


}
