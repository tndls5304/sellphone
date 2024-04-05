package free_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCTemplate;

public class Free_board {

   public void printMenu() throws Exception {
      System.out.println("----- 자유 게시판 -----");
      System.out.println("1. 게시글 작성");
      System.out.println("2. 게시글 상세 검색(제목)");
      System.out.println("3. 게시글 상세 검색(내용)");
      System.out.println("4. 게시글 수정");
      System.out.println("5. 게시글 삭제(유저)");
      System.out.println("6. 게시글 전체 목록 조회");
      System.out.println("7. 게시글 상세 목록 조회(자기꺼)");
      System.out.println("8. 게시글 삭제 (관리자)");
      System.out.println("0. 돌아가기 ..");

      System.out.println("번호를 입력하세요 : ");
      String num = Main.SC.nextLine();

      switch (num) {
      case "1":
         write();
         break;
      case "2":
         titleSearch();
         break;
      case "3":
         contentSearch();
         break;
      case "4":
         updateWtite();
         break;
      case "5":
         DeleteWrite();
         break;
      case "6":
         selectWriteAll();
         break;
      case "7":
         selectWtiteMine();
         break;
      case "8":
         adminDeleteWrite();
         break;
      case "0":
         System.out.println("돌아가기");
         return;
      default:
         System.err.println("잘못 입력하셨습니다.");

      }

   }

   // 게시글 작성
   public void write() throws Exception {

      if (Main.loginMember == null) {
         System.out.println("로그인 해주세요.");
         return;
      }

      Connection conn = JDBCTemplate.getConn();

      System.out.println("제목 : ");
      String title = Main.SC.nextLine();
      System.out.println("내용 : ");
      String content = Main.SC.nextLine();

      String sql = "INSERT INTO FREE_BOARD(NO,TITLE,CONTENT,WRITER_NO) VALUES(SEQ_FREE_BOARD_NO.NEXTVAL,?,?,?)";

      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, title);
      pstmt.setString(2, content);
      pstmt.setString(3, Main.loginMember.getNo());

      int result = pstmt.executeUpdate();

      if (result != 1) {
         System.out.println("게시글 작성 실패..");
         return;
      }
      System.out.println("게시글 작성 성공!");
   }

   // 게시글 검색(제목)
   public void titleSearch() throws Exception {
      Connection conn = JDBCTemplate.getConn();

      System.out.println("검색할 게시글의 제목 입력 : ");
      String title = Main.SC.nextLine();

      String sql = "SELECT * FROM FREE_BOARD WHERE TITLE LIKE '%'|| ? ||'%'";

      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, title);

      ResultSet rs = pstmt.executeQuery();

      ArrayList<Free_boardVo> voList = new ArrayList<Free_boardVo>();
      while (rs.next()) {
         String no = rs.getString("NO");
         String dbtitle = rs.getString("TITLE");
         String content = rs.getString("CONTENT");
         String enroll_date = rs.getString("ENROLL_DATE");
         String modify_date = rs.getString("MODIFY_DATE");
         String writer_no = rs.getString("WRITER_NO");

         Free_boardVo vo = new Free_boardVo(no, dbtitle, content, enroll_date, modify_date, null, writer_no);
         voList.add(vo);
      }

      for (Free_boardVo x : voList) {
         System.out.println("-------------------------------");
         System.out.print("글 번호 : " + x.getNo());
         System.out.println(" | ");
         System.out.print("제목 : " + x.getTitle());
         System.out.println(" | ");
         System.out.print("내용 : " + x.getContent());
         System.out.println(" | ");
         System.out.print("작성일시 : " + x.getEnroll_date());
         System.out.println(" | ");
         System.out.print("작성자 번호 : " + x.getWriter_no());
         System.out.println(" | ");
         System.out.println("-------------------------------");
      }

   }

   // 게시글 검색(내용)
   public void contentSearch() throws Exception {
      Connection conn = JDBCTemplate.getConn();

      System.out.println("검색할 게시글의 내용 입력 : ");
      String content = Main.SC.nextLine();

      String sql = "SELECT * FROM FREE_BOARD WHERE CONTENT LIKE '%'||?||'%'AND DELETE_YN = 'N'";

      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, content);

      ResultSet rs = pstmt.executeQuery();

      ArrayList<Free_boardVo> voList = new ArrayList<Free_boardVo>();
      while (rs.next()) {
         String no = rs.getString("NO");
         String title = rs.getString("TITLE");
         String dbcontent = rs.getString("CONTENT");
         String enroll_date = rs.getString("ENROLL_DATE");
         String modify_date = rs.getString("MODIFY_DATE");
         String writer_no = rs.getString("WRITER_NO");

         Free_boardVo vo = new Free_boardVo(no, title, dbcontent, enroll_date, modify_date, null, writer_no);
         voList.add(vo);
      }

      for (Free_boardVo x : voList) {
         System.out.println("----------------------------");
         System.out.print("글 번호 : " + x.getNo());
         System.out.println(" | ");
         System.out.print("제목 : " + x.getTitle());
         System.out.println(" | ");
         System.out.print("내용 : " + x.getContent());
         System.out.println(" | ");
         System.out.print("작성일시 : " + x.getEnroll_date());
         System.out.println(" | ");
         System.out.print("작성자 번호 : " + x.getWriter_no());
         System.out.println(" | ");
         System.out.println("----------------------------");

      }

   }

   // 게시글 수정
   public void updateWtite() throws Exception {

      if (Main.loginMember == null) {
         System.out.println("로그인 해주세요.");
         return;
      }

      Connection conn = JDBCTemplate.getConn();

      System.out.println("수정할 게시글 번호 : ");
      String no = Main.SC.nextLine();
      System.out.println("수정할 제목 : ");
      String title = Main.SC.nextLine();
      System.out.println("수정할 내용 : ");
      String content = Main.SC.nextLine();

      String sql = "UPDATE FREE_BOARD SET TITLE = ?, CONTENT = ?, MODIFY_DATE = SYSDATE WHERE NO = ? AND DELETE_YN = 'N'";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, title);
      pstmt.setString(2, content);
      pstmt.setString(3, no);

      int result = pstmt.executeUpdate();

      if (result != 1) {
         System.out.println("게시글 수정 실패");
         return;
      }
      System.out.println("게시글 수정 성공!");

   }

   // 일반유저 게시글 삭제 (자기 게시글만 삭제)
   public void DeleteWrite() throws Exception {

      if (Main.loginMember == null) {
         System.out.println("로그인 해주세요.");
         return;
      }

      Connection conn = JDBCTemplate.getConn();

      System.out.println("로그인 한 유저 번호 : ");
      String writer_no = Main.SC.nextLine();
      System.out.println("삭제할 게시글 번호 : ");
      String no = Main.SC.nextLine();

      String sql = "UPDATE FREE_BOARD SET DELETE_YN = 'Y', MODIFY_DATE = SYSDATE WHERE WRITER_NO = ? AND NO = ?";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, writer_no);
      pstmt.setString(2, no);

      int result = pstmt.executeUpdate();

      if (result != 1) {
         System.out.println("게시글이 삭제되지 않았습니다.");
         return;
      }
      System.out.println("게시글이 삭제되었습니다.");
   }

   // 게시글 전체 목록 조회
   public void selectWriteAll() throws Exception {
      Connection conn = JDBCTemplate.getConn();

      String sql = "SELECT * FROM FREE_BOARD ORDER BY NO DESC";

      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      ArrayList<Free_boardVo> volist = new ArrayList<Free_boardVo>();
      Free_boardVo vo = null;
      while (rs.next()) {
         String no = rs.getString("NO");
         String title = rs.getString("TITLE");
         String content = rs.getString("CONTENT");
         String enroll_date = rs.getString("ENROLL_DATE");
         String modify_date = rs.getString("MODIFY_DATE");
         String delete_yn = rs.getString("DELETE_YN");
         String writer_no = rs.getString("WRITER_NO");

         vo = new Free_boardVo(no, title, content, enroll_date, modify_date, delete_yn, writer_no);
         volist.add(vo);
      }
//      System.out.println(volist);
      for (Free_boardVo x : volist) {
         System.out.println("-------------------------");
         System.out.println("번호 : " + x.getNo());
         System.out.println("제목 : " + x.getTitle());
         System.out.println("내용 : " + x.getContent());
         System.out.println("작성일자 : " + x.getEnroll_date());
         System.out.println("수정일자 : " + x.getModify_date());
         System.out.println("작성자 : " + x.getWriter_no());
         System.out.println("삭제 상태 : " + x.getDelete_yn());
         System.out.println("-------------------------");
      }

   }

   // 게시글 상세 목록 조회(개인)
   public void selectWtiteMine() throws Exception {
      if (Main.loginMember == null) {
         System.out.println("로그인 후 이용해 주세요~");
      }

      Connection conn = JDBCTemplate.getConn();

      String sql = "SELECT * FROM FREE_BOARD WHERE WRITER_NO = ? AND DELETE_YN = 'N'";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, Main.loginMember.getNo());

      ResultSet rs = pstmt.executeQuery();

      Free_boardVo vo = null;
      ArrayList<Free_boardVo> volist = new ArrayList<Free_boardVo>();
      while (rs.next()) {
         String dbno = rs.getString("NO");
         String title = rs.getString("TITLE");
         String content = rs.getString("CONTENT");
         String enroll_date = rs.getString("ENROLL_DATE");
         String modify_date = rs.getString("MODIFY_DATE");
         String delete_yn = rs.getString("DELETE_YN");
         String writer_no = rs.getString("WRITER_NO");

         vo = new Free_boardVo(dbno, title, content, enroll_date, modify_date, delete_yn, writer_no);
         volist.add(vo);
      }
      for (Free_boardVo x : volist) {
         System.out.println("-------------------------");
         System.out.println("번호 : " + x.getNo());
         System.out.println("제목 : " + x.getTitle());
         System.out.println("내용 : " + x.getContent());
         System.out.println("작성일자 : " + x.getEnroll_date());
         System.out.println("작성일자 : " + x.getModify_date());
         System.out.println("작성자 : " + x.getWriter_no());
         System.out.println("-------------------------");

      }

   }

   // 관리자 게시글 삭제
   public void adminDeleteWrite() throws Exception {
      if (Main.loginMember == null) {
         System.out.println("관리자 로그인 후 이용해 주세요.");
         return;
      }

      if (Main.loginMember.getId().equals("ADMIN")) {
         Connection conn = JDBCTemplate.getConn();

         System.out.println("삭제할 게시글 번호 : ");
         String no = Main.SC.nextLine();

         String sql = "UPDATE FREE_BOARD SET DELETE_YN = 'Y', MODIFY_DATE = SYSDATE WHERE NO = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, no);

         int result = pstmt.executeUpdate();

         if (result != 1) {
            System.out.println("게시글이 삭제되지 않았습니다.");
            return;
         }
         System.out.println("게시글이 삭제되었습니다.");
      }

   }

}
