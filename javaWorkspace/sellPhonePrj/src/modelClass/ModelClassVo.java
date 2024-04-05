package modelClass;

public class ModelClassVo {
	private String no;
	private String grade;
	private String totalScore;
	private String enrollDate;
	public ModelClassVo(String no, String grade, String totalScore, String enrollDate) {
		super();
		this.no = no;
		this.grade = grade;
		this.totalScore = totalScore;
		this.enrollDate = enrollDate;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
	
}
