package phone;

public class PhoneVo {
   private String no;
   private String modelName;
   private String grade;
   private String gradePrice;

   public PhoneVo(String no, String modelName, String grade, String gradePrice) {
      super();
      this.no = no;
      this.modelName = modelName;
      this.grade = grade;
      this.gradePrice = gradePrice;
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

   public String getGradePrice() {
      return gradePrice;
   }

   public void setGradePrice(String gradePrice) {
      this.gradePrice = gradePrice;
   }

   @Override
   public String toString() {
      return "PhoneVo [no=" + no + ", modelName=" + modelName + ", grade=" + grade + ", gradePrice=" + gradePrice
            + "]";
   }

}
