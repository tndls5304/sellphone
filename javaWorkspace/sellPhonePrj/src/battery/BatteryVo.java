package battery;

public class BatteryVo {
	private String percentage;
	private String score;
	private String enrolldate;

	public BatteryVo(String percentage, String score, String enrolldate) {
		super();
		this.percentage = percentage;
		this.score = score;
		this.enrolldate = enrolldate;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(String enrolldate) {
		this.enrolldate = enrolldate;
	}

}
