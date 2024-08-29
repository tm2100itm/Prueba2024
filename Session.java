
public class Session {

    private int successes;
    private int errors;
    
    public Session(){
		successes=0;
		errors=0;
		}

    public int getTotalCorrect() {
        return successes;
    }

    public int getTotalIncorrect() {
        return errors;
    }

    public void increseCorrect() {
        successes++;
    }

    public void increseInCorrect() {
        errors++;
    }
    
    public int getTotalGeneratedQuestions(){
    return successes + errors;
    }

    public double average() {
        return (successes + errors) / successes;
    }
}
