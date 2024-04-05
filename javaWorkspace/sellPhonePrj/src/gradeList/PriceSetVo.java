package gradeList;

public class PriceSetVo {
	private String no;
	private String modelName;
	private String grade;
	private String firstPrice;
	private String gradePrice;
	private String percentage;
	private String enrollDate;
	public PriceSetVo(String no, String modelName, String grade, String firstPrice, String gradePrice,
			String percentage, String enrollDate) {
		super();
		this.no = no;
		this.modelName = modelName;
		this.grade = grade;
		this.firstPrice = firstPrice;
		this.gradePrice = gradePrice;
		this.percentage = percentage;
		this.enrollDate = enrollDate;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getFirstPrice() {
		return firstPrice;
	}
	public void setFirstPrice(String firstPrice) {
		this.firstPrice = firstPrice;
	}
	public String getGradePrice() {
		return gradePrice;
	}
	public void setGradePrice(String gradePrice) {
		this.gradePrice = gradePrice;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
}
