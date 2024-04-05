package faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCTemplate;

public class FaqController {
   public void printMenu() throws Exception {
      System.out.println("=======자주 묻는 질문=========");
      System.out.println("1.최신순으로 게시물 조회");
      System.out.println("2.제목으로 검색");
      System.out.println("3.내용으로 검색");
      System.out.println("4.관리자-게시글 작성");
      System.out.println("5.관리자-게시글 수정");
      System.out.println("6.관리자-게시글 삭제");
      System.out.println("0.돌아가기");
      

      System.out.print("원하시는 번호를 입력하세요:");
      String num=Main.SC.nextLine();
      
      switch (num) {
      case "1":latestPostsSearch();
         break; 
      case "2":SearchByTitle();
         break;
      case "3":SearchByContents ();
         break;
      case "4":write();
         break;
      case "5":edit();
         break;
      case "6":delete();
         break;
      case "0":
         System.out.println("돌아가기");
         return;
      default:
         System.out.println("잘못누름");
      }

   }
   
   
   //최신순으로 게시물 조회
   public void latestPostsSearch() throws Exception {
      // conn
      Connection conn = JDBCTemplate.getConn();

      // sql
      String sql = "SELECT * FROM FAQ ORDER BY ENROLL_DATE DESC, MODIFY_DATE DESC";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
     
      // rs
      ArrayList<FaqVo> voList = new ArrayList<FaqVo>();

      
      while ( rs.next() ) {
         
         String no = rs.getString("NO");
         String title = rs.getString("TITLE");
         String contents = rs.getString("CONTENTS");
         String category = rs.getString("CATEGORY");
         String enrollDate=rs.getString("ENROLL_DATE");
   //      String modifyDate = rs.getString("MODIFY_DATE");

         FaqVo faqvo = new FaqVo(no, title, contents, category, enrollDate,null,null);
         voList.add(faqvo);
      }
      System.out.print("번호|");
      System.out.print("분류");
      System.out.print("   | ");
      System.out.print("제목          ");
      System.out.print(" | ");
      System.out.print("      내용");
      System.out.print("                     |");
      System.out.print("   등록일   ");
      System.out.println("|" );
      System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
     
      for (FaqVo x : voList) {
         System.out.print(x.getNo()+" |");
         System.out.print( x.getCategory());
         System.out.print("|" );
         System.out.print( x.getTitle());
         System.out.print("|" );
         System.out.print(x.getContents());
         System.out.print("|" );
         System.out.print(x.getEnrollDate());
         System.out.println("|" );
      }    
   }
   
   //제목으로 검색
   public void SearchByTitle() throws Exception {

      Connection conn = JDBCTemplate.getConn();

      System.out.print("제목에서 조회) 조회할 제목을 적어주세요 :");
      String value = Main.SC.nextLine();
      //★★★★★★★
      String sql = "SELECT *FROM FAQ WHERE TITLE LIKE '%' || ? || '%' ";   //★★★★★★★★★★
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, value);

      // 위치 result★★★★★★★★★★★★
      ResultSet rs = pstmt.executeQuery();
      
      // 결과처리
      ArrayList<FaqVo> voList = new ArrayList<FaqVo>();

      while (rs.next()) {
          String no = rs.getString("NO");
          String title = rs.getString("TITLE");
          String contents = rs.getString("CONTENTS");
          String category = rs.getString("CATEGORY");
          String enrollDate=rs.getString("ENROLL_DATE");
    //      String modifyDate = rs.getString("MODIFY_DATE");

          FaqVo faqvo = new FaqVo(no, title, contents, category, enrollDate,null,null);
          voList.add(faqvo);          
       }

      System.out.print("번호|");
      System.out.print("분류");
      System.out.print("   | ");
      System.out.print("제목          ");
      System.out.print(" | ");
      System.out.print("      내용");
      System.out.print("                     |");
      System.out.print("   등록일   ");
      System.out.println("|" );
      System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
     
      for (FaqVo x : voList) {
         System.out.print(x.getNo()+"  |");
         System.out.print( x.getCategory());
         System.out.print("|" );
         System.out.print( x.getTitle());
         System.out.print("|" );
         System.out.print(x.getContents());
         System.out.print("|" );
         System.out.print(x.getEnrollDate());
         System.out.print("|" );
      }
   }
   //내용으로 검색
   public void SearchByContents () throws Exception {
      Connection conn = JDBCTemplate.getConn();

         System.out.print("내용에서 조회) 조회할 키워드를 적어주세요 :");
         String value = Main.SC.nextLine();
         //★★★★★★★
         String sql = "SELECT *FROM FAQ WHERE CONTENTS LIKE '%'||?||'%' ";   //★★★★★★★★★★
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, value);

         // 위치 result★★★★★★★★★★★★
         ResultSet rs = pstmt.executeQuery();
         
         // 결과처리
         ArrayList<FaqVo> voList = new ArrayList<FaqVo>();

         while (rs.next()) {
             String no = rs.getString("NO");
             String title = rs.getString("TITLE");
             String contents = rs.getString("CONTENTS");
             String category = rs.getString("CATEGORY");
             String enrollDate=rs.getString("ENROLL_DATE");
       //      String modifyDate = rs.getString("MODIFY_DATE");

             FaqVo faqvo = new FaqVo(no, title, contents, category, enrollDate,null,null);
             voList.add(faqvo);          
          }

         System.out.print("번호|");
         System.out.print("분류");
         System.out.print("   | ");
         System.out.print("제목          ");
         System.out.print(" | ");
         System.out.print("      내용");
         System.out.print("                     |");
         System.out.print("   등록일   ");
         System.out.println("|" );
         System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
         
        
         for (FaqVo x : voList) {
            System.out.print(x.getNo());
            System.out.print("|" );
            System.out.print( x.getCategory());
            System.out.print("|" );
            System.out.print( x.getTitle());
            System.out.print("|" );
            System.out.print(x.getContents());
            System.out.print("|" );
            System.out.print(x.getEnrollDate());
            System.out.print("|" );

         }
   }
   // 관리자-게시글 작성  
   public void write() throws Exception {
      String id=Main.loginmember.getId();
      if(id.equals("ADMIN")) {
        
         // conn준비
        Connection conn = JDBCTemplate.getConn();
        // sql준비
        String sql = "INSERT INTO FAQ( NO,TITLE,CONTENTS,CATEGORY) VALUES(3+ SEQ_FAQ_NO.NEXTVAL , ?, ?, ?)";

        System.out.print("제목 입력 : ");
        String title = Main.SC.nextLine();
        System.out.print("내용 입력 : ");
        String contents = Main.SC.nextLine();
        System.out.print("카테고리 입력하기: ");
        String category= Main.SC.nextLine();

        // sql실행
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, title);
        pstmt.setString(2, contents);
      pstmt.setString(3, category);
      
        // 위치 ★★★★★★★★★★★★★★
      System.out.println("1.실행됨");
        int result = pstmt.executeUpdate();
        System.out.println("2.실행됨");
        // 결과처리
        if (result != 1) {
           System.out.println("게시글 작성실패..");
           return;
        }
        System.out.println("게시글 작성 성공");
         
      }
      else {
      System.out.println("접근 금지! 게시글작성은 관리자만 할 수 있습니다");
      return;
      }
      
   }
   
   
   //관리자-게시글 수정
   public void edit() throws Exception {
      String id=Main.loginmember.getId();
         if(id.equals("ADMIN")) {
            
            // conn준비
           Connection conn = JDBCTemplate.getConn();
           // sql준비
           String sql = "UPDATE FAQ SET TITLE = ? , CONTENTS = ? ,CATEGORY=?, MODIFY_DATE = SYSDATE WHERE NO = ?";

           System.out.print("수정할 게시물 번호 입력: ");
           String no = Main.SC.nextLine();
           System.out.print("수정할 제목 입력 : ");
           String title = Main.SC.nextLine();
           System.out.print("수정할 내용 입력 : ");
           String contents = Main.SC.nextLine();
           System.out.print("수정할 카테고리 입력하기: ");
           String category= Main.SC.nextLine();
           
           // sql실행

           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, title);
           pstmt.setString(2, contents);
         pstmt.setString(3, category);
         pstmt.setString(4, no);
           // 위치 ★★★★★★★★★★★★★★
           int result = pstmt.executeUpdate();
           // 결과처리
           if (result != 1) {
              System.out.println("게시글 수정실패..");
              return;
           }
           System.out.println("게시글 수정 성공");
            
         }
         else {
             System.out.println("접근 금지! 게시글작성은 관리자만 할 수 있습니다");
             return;
             }
   }
   //관리자-게시글 삭제
   
   public void delete() throws Exception {
      String id=Main.loginmember.getId();
         if(id.equals("ADMIN")) {
            
            Connection conn = JDBCTemplate.getConn();

             System.out.print("관리자님. 삭제할 게시물 번호를 입력해주세요 :");
             String value = Main.SC.nextLine();
             //★★★★★★★
             String sql = "UPDATE FAQ SET DEL_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE NO = ?";  
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, value);


              int result = pstmt.executeUpdate();
              // 결과처리
              if (result != 1) {
                 System.out.println("관리자님 게시글 삭제 실패했습니다..");
                 return;
              }
              System.out.println("관리자님 게시글 삭제 성공했습니다");
           }
         else {
             System.out.println("접근 금지! 게시글작성은 관리자만 할 수 있습니다");
             return;
             }
   }

}