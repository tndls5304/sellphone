package scratch;

public class ScratchVo {
	private String no;
	private String num;
	private String score;
	private String enrolldate;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public ScratchVo(String no, String num, String score, String enrolldate) {
		super();
		this.no = no;
		this.num = num;
		this.score = score;
		this.enrolldate = enrolldate;
	}

	public ScratchVo(String num, String score, String enrolldate) {
		super();
		this.num = num;
		this.score = score;
		this.enrolldate = enrolldate;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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
