package free_board;

public class Free_boardVo {
   private String no;
   private String title;
   private String content;
   private String enroll_date;
   private String modify_date;
   private String delete_yn;
   private String writer_no;
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
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getEnroll_date() {
      return enroll_date;
   }
   public void setEnroll_date(String enroll_date) {
      this.enroll_date = enroll_date;
   }
   public String getModify_date() {
      return modify_date;
   }
   public void setModify_date(String modify_date) {
      this.modify_date = modify_date;
   }
   public String getDelete_yn() {
      return delete_yn;
   }
   public void setDelete_yn(String delete_yn) {
      this.delete_yn = delete_yn;
   }
   public String getWriter_no() {
      return writer_no;
   }
   public void setWriter_no(String writer_no) {
      this.writer_no = writer_no;
   }
   @Override
   public String toString() {
      return "FreeboardVo [no=" + no + ", title=" + title + ", content=" + content + ", enroll_date=" + enroll_date
            + ", modify_date=" + modify_date + ", delete_yn=" + delete_yn + ", writer_no=" + writer_no + "]";
   }
   public Free_boardVo(String no, String title, String content, String enroll_date, String modify_date,
         String delete_yn, String writer_no) {
      super();
      this.no = no;
      this.title = title;
      this.content = content;
      this.enroll_date = enroll_date;
      this.modify_date = modify_date;
      this.delete_yn = delete_yn;
      this.writer_no = writer_no;
   }
   public Free_boardVo() {
      
   }
   
   
}

