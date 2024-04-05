package QNA;

public class QnaVo {

	private String 	no;
	private String	userTitle;
	private String	userContents;
	private String	enrollDate;
	private String	category;
	private String	writerNo;
	private String	masterTitle;
	private String	masterContents;
	private String	modifyDate;
	private String	delYn;
	
	public QnaVo() {
	
		// TODO Auto-generated constructor stub
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getUserContents() {
		return userContents;
	}

	public void setUserContents(String userContents) {
		this.userContents = userContents;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(String writerNo) {
		this.writerNo = writerNo;
	}

	public String getMasterTitle() {
		return masterTitle;
	}

	public void setMasterTitle(String masterTitle) {
		this.masterTitle = masterTitle;
	}

	public String getMasterContents() {
		return masterContents;
	}

	public void setMasterContents(String masterContents) {
		this.masterContents = masterContents;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public QnaVo(String no, String userTitle, String userContents, String enrollDate, String category,
			String writerNo, String masterTitle, String masterContents, String modifyDate, String delYn) {
		super();
		this.no = no;
		this.userTitle = userTitle;
		this.userContents = userContents;
		this.enrollDate = enrollDate;
		this.category = category;
		this.writerNo = writerNo;
		this.masterTitle = masterTitle;
		this.masterContents = masterContents;
		this.modifyDate = modifyDate;
		this.delYn = delYn;
	}
	@Override
	public String toString() {
		return "QnaBoard [no=" + no + ", userTitle=" + userTitle + ", userContents=" + userContents + ", enrollDate="
				+ enrollDate + ", category=" + category + ", writerNo=" + writerNo + ", masterTitle=" + masterTitle
				+ ", masterContents=" + masterContents + ", modifyDate=" + modifyDate + ", delYn=" + delYn + "]";
	}
	}