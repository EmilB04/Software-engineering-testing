import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import emil.ExamTool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import java.util.Collections;

public class ExamToolTests {
    @Test
    @DisplayName("Count grade i list of grades")

    public void countGradeInList() {
        //ExamTool examTool = new ExamTool();

        ArrayList<String> grades = new ArrayList<>();
        Collections.addAll(grades, "A", "B", "C", "D", "A", "A", "B", "C", "D", "F");

        int countA = ExamTool.countGradeInList("A", grades);
        int countB = ExamTool.countGradeInList("B", grades);
        int countC = ExamTool.countGradeInList("C", grades);
        int countD = ExamTool.countGradeInList("D", grades);
        int countF = ExamTool.countGradeInList("F", grades);
        
        Assertions.assertEquals(3, countA);
        Assertions.assertEquals(2, countB);
        Assertions.assertEquals(2, countC);
        Assertions.assertEquals(2, countD);
        Assertions.assertEquals(1, countF);
    }
}
