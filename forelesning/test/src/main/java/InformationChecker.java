public class InformationChecker {
    public int controlAge(int age) { // Dette er en enhet
        if (age < 0) {
            return 0;
        } 
        else if (age > 120) {
            return 120;
        } 
        else {
            return age;
        }
    }
    
}
