import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    public void firstArgNullHorseTest(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
    }


}
