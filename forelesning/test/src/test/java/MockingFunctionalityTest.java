import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MockingFunctionalityTest {
    @Test
    @DisplayName("Test mocking functionality")
    public void MockingFunctionality() {
        //Calculator mockCalculator = Mockito.mock(Calculator.class);
        Mockito.when(mockCalculator.addition(2, 5)).thenReturn(7);
        Mockito.when(mockCalculator.addition(3, 5)).thenReturn(8);
        
        int result = mockCalculator.addition(2, 5);
        int result2 = mockCalculator.addition(3, 5);


        assertEquals(7, result);
        assertEquals(8, result2);
        Mockito.verify(mockCalculator).addition(2, 5);
        Mockito.verify(mockCalculator, Mockito.times(2)).addition(Mockito.anyInt(), Mockito.anyInt());
    }
}