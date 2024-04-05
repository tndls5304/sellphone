package faq;

public class FaqVo {
   
   private String no;
   private String title;
   private String contents;
   private String category;
   private String enrollDate;
   private String modifyDate;
   private String delYn;
   
   
public FaqVo() {
   super();
   // TODO Auto-generated constructor stub
}
public FaqVo(String no, String title, String contents, String category, String enrollDate, String modifyDate,
      String delYn) {
   super();
   this.no = no;
   this.title = title;
   this.contents = contents;
   this.category = category;
   this.enrollDate = enrollDate;
   this.modifyDate = modifyDate;
   this.delYn = delYn;
}
public String getNo() {
   return no;
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
public String getContents() {
   return contents;
}
public void setContents(String contents) {
   this.contents = contents;
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
   return "FaqVo [no=" + no + ", title=" + title + ", contents=" + contents + ", category=" + category
         + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", delYn=" + delYn + "]";
}
   


   
}