package announcement;

public class AnnouncementVo {
	
	   public AnnouncementVo() {
	      
	   }
	   public AnnouncementVo(String no, String id, String title, String content, String category, String enrollDate,
	         String modifyDate, String delYn) {
	      super();
	      this.no = no;
	      this.id = id;
	      this.title = title;
	      this.content = content;
	      this.category = category;
	      this.enrollDate = enrollDate;
	      this.modifyDate = modifyDate;
	      this.delYn = delYn;
	   }
	   
	   private String no;
	   private String id;
	   private String title;
	   private String content;
	   private String category;
	   private String enrollDate;
	   private String modifyDate;
	   private String delYn;
	   public String getNo() {
	      return no;
	   }
	   
	   public String getId() {
	      return id;
	   }
	   public void setId(String id) {
	      this.id = id;
	   }
	   public void setNo(String no) {
	      this.no = no;
	   }
	   public String getTitle() {
	      return title;
	   }
	   public void setTitle(String title) {
	      this.title = title;
	   }
	   public String getContent() {
	      return content;
	   }
	   public void setContent(String content) {
	      this.content = content;
	   }
	   public String getCategory() {
	      return category;
	   }
	   public void setCategory(String category) {
	      this.category = category;
	   }
	   public String getEnrollDate() {
	      return enrollDate;
	   }
	   public void setEnrollDate(String enrollDate) {
	      this.enrollDate = enrollDate;
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
	   @Override
	   public String toString() {
	      return "AnnouncementVo [no=" + no + ", id=" + id + ", title=" + title + ", content=" + content + ", category="
	            + category + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", delYn=" + delYn + "]";
	   }
	   
	   
	}

