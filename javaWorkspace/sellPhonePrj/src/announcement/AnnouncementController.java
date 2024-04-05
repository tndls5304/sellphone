package announcement;

public class AnnouncementController {
	package announcement;

	import java.sql.Connection;

	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import main.Main;
	import util.Util;

	

	   public void printMenu() throws Exception{
	      System.out.println("번호를 입력해주세요");
	      
	      System.out.println("1. 게시글 작성");
	      System.out.println("2. 게시글 수정");
	      System.out.println("3. 게시글 전체조회");
	      System.out.println("4. 게시글 상세조회"); //번호 내용 카테고리
	      System.out.println("5. 게시글 삭제");
	      System.out.println("0. 돌아가기");
	      
	      String num = Main.SC.nextLine();
	      
	      switch(num) {
	         case "1" : write(); break;
	         case "2" : editBoardselaec(); break;
	         case "3" : selecBoardList(); break;
	         case "4" : selectBoardselect(); break;
	         case "5" : BoardDel(); break;
	         case "0" : System.out.println("이전메뉴로 돌아갑니다."); return;
	         
	         default : System.out.println("잘못된 입력입니다.");
	      }
	      
	   }
	   
	   //게시글 작성
	   public void write() throws Exception {
	      
	      if(Main.loginMember.getId().equals("ADMIN")) {
	         System.out.println("관리자님 환영합니다.");
	         
	         //conn
	         Connection conn = Util.getConn();
	         
	         System.out.println("제목 : ");
	         String intputtitle = Main.SC.nextLine();
	         System.out.println("내용 : ");
	         String intputcontent = Main.SC.nextLine();
	         System.out.println("카테고리 : ");
	         String intputcategory = Main.SC.nextLine();
	         
	         String sql = "INSERT INTO ANNOUNCEMENT(NO, TITLE, CONTENT,CATEGORY) VALUES(SEQ_ANNOUNCEMENT_NO.NEXTVAL , ?, ?, ?)";
	         
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, intputtitle);
	         pstmt.setString(2, intputcontent);
	         pstmt.setString(3, intputcategory);

	         int result = pstmt.executeUpdate();
	         
	         if(result != 1) {
	            System.out.println("게시글 작성 실패");
	            return;
	         }
	         System.out.println("게시글 작성 성공");
	      
	      }else {
	         System.out.println("=====관리자계정만 가능합니다..====");
	         
	      }
	      
	   }
	   
	   
	   //게시글 수정
	   private void editBoardselaec() throws Exception {
	      if(Main.loginMember.getId().equals("ADMIN")) {
	         System.out.println("관리자님 환영합니다.");
	         
	         System.out.println("번호를 입력해주세요");
	         
	         System.out.println("1. 게시글 수정(제목 및 내용)");
	         System.out.println("2. 게시글 수정(제목)");
	         System.out.println("3. 게시글 수정(내용)");
	         String num = Main.SC.nextLine();
	         
	         switch(num) {
	         case "1" : editBoard(); break;
	         case "2" : editBoardTitle(); break;
	         case "3" : editBoardContent(); break;
	         case "0" : System.out.println("이전 메뉴로 돌아갑니다."); return;
	         
	         default : System.out.println("잘못된 입력 값입니다.");
	         
	         }
	         
	      }else{
	         System.out.println("관리자계정이 아닙니다.");
	         return;
	      }
	   }
	   
	   private void editBoardTitle() throws Exception {
	      System.out.println("제목 변경 - 번호입력");
	      
	      Connection conn = Util.getConn();
	      
	      String sql ="UPDATE ANNOUNCEMENT SET TITLE = ? WHERE NO = ?";
	      
	      //유저입력받기
	      System.out.println("번호 : ");
	      String inputNo = Main.SC.nextLine();
	      System.out.println("제목 : ");
	      String inputTitle = Main.SC.nextLine();
	            
	      //SQL 실행을 위한 statement 준비
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, inputTitle);
	      pstmt.setString(2, inputNo);
	      int result = pstmt.executeUpdate();
	            
	      //결과 처리
	      if(result !=1) {
	         System.out.println("제목 수정 실패");
	         return;
	      }
	      System.out.println("제목 수정 성공");
	   }
	   
	   private void editBoardContent() throws Exception {
	      System.out.println("내용 변경 - 번호입력");
	      
	      Connection conn = Util.getConn();
	      
	      String sql ="UPDATE ANNOUNCEMENT SET CONTENT = ? WHERE NO = ?";
	      
	      //유저입력받기
	      System.out.println("번호 : ");
	      String inputNo = Main.SC.nextLine();
	      System.out.println("내용 : ");
	      String inputContent = Main.SC.nextLine();
	            
	      //SQL 실행을 위한 statement 준비
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, inputContent);
	      pstmt.setString(2, inputNo);
	      int result = pstmt.executeUpdate();
	            
	      //결과 처리
	      if(result !=1) {
	         System.out.println("내용 수정 실패");
	         return;
	      }
	      System.out.println("내용 수정 성공");
	   }
	   private void editBoard() throws Exception {
	      System.out.println("제목 및 내용 변경 - 번호입력");
	      
	      Connection conn = Util.getConn();
	      
	      String sql ="UPDATE ANNOUNCEMENT SET TITLE = ? , CONTENT = ? , MODIFY_DATE = SYSDATE WHERE NO = ?";
	      
	      //유저입력받기
	      System.out.println("번호 : ");
	      String inputNo = Main.SC.nextLine();
	      System.out.println("제목 : ");
	      String inputTitle = Main.SC.nextLine();
	      System.out.println("내용 : ");
	      String inputContent = Main.SC.nextLine();
	      //SQL 실행을 위한 statement 준비
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, inputContent);
	      pstmt.setString(2, inputTitle);
	      pstmt.setString(3, inputNo);
	      int result = pstmt.executeUpdate();
	            
	      //결과 처리
	      if(result !=1) {
	         System.out.println("제목,내용 수정 실패");
	         return;
	      }
	      System.out.println("제목,내용 수정 성공");
	   }
	   //게시글 전체조회
	   private void selecBoardList() throws Exception {
	      
	      //conn
	      Connection conn = Util.getConn();
	      String sql = "SELECT * FROM ANNOUNCEMENT WHERE DEL_YN = 'N' ORDER BY NO DESC";
	      
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      
	      ResultSet rs = pstmt.executeQuery();
	      ArrayList<AnnouncementVo> voList = new ArrayList<AnnouncementVo>();
	      
	      while(rs.next()) {
	         String no = rs.getString("NO");
	         String title = rs.getString("TITLE");
	         String content = rs.getString("CONTENT");
	         String category = rs.getString("CATEGORY");
	         String enrollDate = rs.getString("ENROLL_DATE");
	         String modifyDate = rs.getString("MODIFY_DATE");
	         String delYn = rs.getString("DEL_YN");
	         
	         AnnouncementVo vo = new AnnouncementVo(null, no, title, content, category, enrollDate, modifyDate, delYn);
	         voList.add(vo);
	      }
	      System.out.println("-------------");
	      System.out.print("번호");
	      System.out.print(" | ");
	      System.out.print("제목");
	      System.out.print(" | ");
	      System.out.print("내용");
	      System.out.print(" | ");
	      System.out.print("카테고리");
	      System.out.println();
	      for(AnnouncementVo x : voList) {
	      
	         System.out.print(x.getNo());
	         System.out.print(" | ");
	         System.out.print(x.getTitle());
	         System.out.print(" | ");
	         System.out.print(x.getContent());
	         System.out.print(" | ");
	         System.out.print(x.getCategory());
	         System.out.println();
	         
	      }
	      System.out.println("--------------");
	   
	   }
	   //게시글 상세조회(번호,카테고리,내용선택)
	   private void selectBoardselect() throws Exception{
	      System.out.println("번호를 입력해주세요");
	      
	      System.out.println("1. 게시글 상세조회(번호)");
	      System.out.println("2. 게시글 상세조회(카테고리)");
	      System.out.println("3. 게시글 상세조회(제목)");
	      System.out.println("4. 게시글 상세조회(내용)");
	      String num = Main.SC.nextLine();
	      
	      switch(num) {
	      case "1" : selectBoardNo(); break;
	      case "2" : selectBoardCategory(); break;
	      case "3" : selectBoardTitle(); break;
	      case "4" : selectBoardContent(); break;
	      case "0" : System.out.println("이전 메뉴로 돌아갑니다."); return;
	      
	      default : System.out.println("잘못된 입력 값입니다.");
	      
	      }
	   }
	   //게시글 상세조회(번호)
	   private void selectBoardNo() throws Exception{
	      //conn
	   Connection conn = Util.getConn();
	   
	   System.out.println("검색할 번호 : ");
	   String inputNo = Main.SC.nextLine();
	   
	   String sql = "SELECT * FROM ANNOUNCEMENT WHERE NO = ? AND DEL_YN = 'N'";
	   
	   PreparedStatement pstmt = conn.prepareStatement(sql);
	   pstmt.setString(1, inputNo);
	   ResultSet rs = pstmt.executeQuery();
	   
	   
	   ArrayList<AnnouncementVo> voList = new ArrayList<AnnouncementVo>();
	   
	   while(rs.next()) {
	      String no = rs.getString("NO");
	      String title = rs.getString("TITLE");
	      String content = rs.getString("CONTENT");
	      String category = rs.getString("CATEGORY");
	      String enrollDate = rs.getString("ENROLL_DATE");
	      String modifyDate = rs.getString("MODIFY_DATE");
	      String delYn = rs.getString("DEL_YN");
	      
	      AnnouncementVo vo = new AnnouncementVo(inputNo, no, title, content, category, enrollDate, modifyDate, delYn);
	      voList.add(vo);
	   }
	   System.out.println("-------------");
	   System.out.print("번호");
	   System.out.print(" | ");
	   System.out.print("제목");
	   System.out.print(" | ");
	   System.out.print("내용");
	   System.out.print(" | ");
	   System.out.print("카테고리");
	   System.out.println();
	   for(AnnouncementVo x : voList) {
	   
	      System.out.print(x.getNo());
	      System.out.print(" | ");
	      System.out.print(x.getTitle());
	      System.out.print(" | ");
	      System.out.print(x.getContent());
	      System.out.print(" | ");
	      System.out.print(x.getCategory());
	      System.out.println();
	      
	   }
	   System.out.println("--------------");
	}
	   //게시글 상세조회(카테고리)
	   private void selectBoardCategory() throws Exception {
	      Connection conn = Util.getConn();
	      
	      System.out.println("검색할 카테고리 : ");
	      String inputCategory = Main.SC.nextLine();
	      
	      String sql = "SELECT * FROM ANNOUNCEMENT WHERE CATEGORY = ? AND DEL_YN = 'N'";
	      
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, inputCategory);
	      ResultSet rs = pstmt.executeQuery();
	      ArrayList<AnnouncementVo> voList = new ArrayList<AnnouncementVo>();
	      
	      while(rs.next()) {
	      String no = rs.getString("NO");
	      String title = rs.getString("TITLE");
	      String content = rs.getString("CONTENT");
	      String category = rs.getString("CATEGORY");
	      String enrollDate = rs.getString("ENROLL_DATE");
	      String modifyDate = rs.getString("MODIFY_DATE");
	      String delYn = rs.getString("DEL_YN");
	         
	         AnnouncementVo vo = new AnnouncementVo(no, no, title, content, category, enrollDate, modifyDate, delYn);
	         voList.add(vo);
	      }
	      System.out.println("-------------");
	      System.out.print("번호");
	      System.out.print(" | ");
	      System.out.print("제목");
	      System.out.print(" | ");
	      System.out.print("내용");
	      System.out.print(" | ");
	      System.out.print("카테고리");
	      System.out.println();
	      for(AnnouncementVo x : voList) {
	      
	         System.out.print(x.getNo());
	         System.out.print(" | ");
	         System.out.print(x.getTitle());
	         System.out.print(" | ");
	         System.out.print(x.getContent());
	         System.out.print(" | ");
	         System.out.print(x.getCategory());
	         System.out.println();
	         
	      }
	      System.out.println("--------------");
	   }
	   
	   //게시글 상세조회(제목)
	   private void selectBoardTitle() throws Exception {
	   Connection conn = Util.getConn();
	      
	      System.out.println("검색할 제목 : ");
	      String inputTitle = Main.SC.nextLine();
	   
	      String sql = "SELECT * FROM ANNOUNCEMENT WHERE TITLE LIKE '%'||?||'%' AND DEL_YN = 'N'";
	      
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, inputTitle);

	      ResultSet rs = pstmt.executeQuery();
	      ArrayList<AnnouncementVo> voList = new ArrayList<AnnouncementVo>();
	      
	      while(rs.next()) {
	         String no = rs.getString("NO");
	         String title = rs.getString("TITLE");
	         String content = rs.getString("CONTENT");
	         String category = rs.getString("CATEGORY");
	         String enrollDate = rs.getString("ENROLL_DATE");
	         String modifyDate = rs.getString("MODIFY_DATE");
	         String delYn = rs.getString("DEL_YN");
	         
	         AnnouncementVo vo = new AnnouncementVo(null, no, title, content, category, enrollDate, modifyDate, delYn);
	         voList.add(vo);
	      }
	      System.out.println("-------------");
	      System.out.print("번호");
	      System.out.print(" | ");
	      System.out.print("제목");
	      System.out.print(" | ");
	      System.out.print("내용");
	      System.out.print(" | ");
	      System.out.print("카테고리");
	      System.out.println();
	      for(AnnouncementVo x : voList) {
	      
	         System.out.print(x.getNo());
	         System.out.print(" | ");
	         System.out.print(x.getTitle());
	         System.out.print(" | ");
	         System.out.print(x.getContent());
	         System.out.print(" | ");
	         System.out.print(x.getCategory());
	         System.out.println();
	         
	      }
	      System.out.println("--------------");
	   }
	   //게시글 상세조회(내용)
	   private void selectBoardContent() throws Exception {
	      Connection conn = Util.getConn();
	      
	      System.out.println("검색할 내용 : ");
	      String inputTitle = Main.SC.nextLine();
	   
	      String sql = "SELECT * FROM ANNOUNCEMENT WHERE CONTENT LIKE '%'||?||'%' AND DEL_YN = 'N'";
	      
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, inputTitle);

	      ResultSet rs = pstmt.executeQuery();
	      ArrayList<AnnouncementVo> voList = new ArrayList<AnnouncementVo>();
	      
	      while(rs.next()) {
	         String no = rs.getString("NO");
	         String title = rs.getString("TITLE");
	         String content = rs.getString("CONTENT");
	         String category = rs.getString("CATEGORY");
	         String enrollDate = rs.getString("ENROLL_DATE");
	         String modifyDate = rs.getString("MODIFY_DATE");
	         String delYn = rs.getString("DEL_YN");
	         
	         AnnouncementVo vo = new AnnouncementVo(null, no, title, content, category, enrollDate, modifyDate, delYn);
	         voList.add(vo);
	      }
	      System.out.println("-------------");
	      System.out.print("번호");
	      System.out.print(" | ");
	      System.out.print("제목");
	      System.out.print(" | ");
	      System.out.print("내용");
	      System.out.print(" | ");
	      System.out.print("카테고리");
	      System.out.println();
	      for(AnnouncementVo x : voList) {
	      
	         System.out.print(x.getNo());
	         System.out.print(" | ");
	         System.out.print(x.getTitle());
	         System.out.print(" | ");
	         System.out.print(x.getContent());
	         System.out.print(" | ");
	         System.out.print(x.getCategory());
	         System.out.println();
	         
	      }
	      System.out.println("--------------");
	   }
	   //게시글 삭제(관리자만)
	   private void BoardDel() throws Exception {
	      
	      if(Main.loginMember.getId().equals("ADMIN")) {
	         System.out.println("게시글삭제");
	         
	         //conn준비
	         Connection conn = Util.getConn();
	         
	         //sql준비
	         String sql = "UPDATE ANNOUNCEMENT SET DEL_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE NO = ?";
	         //유저입력받기
	         System.out.println("삭제할 게시글의 번호 : ");
	         String inputNo = Main.SC.nextLine();
	         
	         //SQL 실행을 위한 statement 준비
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, inputNo);
	         int result = pstmt.executeUpdate();
	         
	         //결과 처리
	         if(result !=1) {
	            System.out.println("게시글 삭제 실패");
	            return;
	         }
	         System.out.println("게시글 삭제 성공");
	      }
	      
	   }
	   
	}

