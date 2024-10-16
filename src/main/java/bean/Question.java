package bean;

public class Question {
	private int id; 
	private String subject	;
    private String question; 
    private String optionA; 
    private String optionB;
    private String optionC; 
    private String optionD; 
    private String answerOption;
    
 // Constructor (default)
    public Question() {}

    // Constructor with parameters
    public Question(String subject, String question, String optionA, String optionB, String optionC, String optionD, String answerOption) {
        this.subject = subject;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerOption = answerOption;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getAnswerOption() {
		return answerOption;
	}
	public void setAnswerOption(String answerOption) {
		this.answerOption = answerOption;
	} 
}
