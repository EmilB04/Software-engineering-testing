package emil;
import java.util.ArrayList;

public abstract class ExamTool {
    public static int countGradeInList(String gradeToCount, ArrayList<String> grades){
        int count = 0;
        for (String gradeX : grades){
            if (gradeX.equals(gradeToCount)) count++;
        }
        return count;
    }
}
