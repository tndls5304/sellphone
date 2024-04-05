package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCTemplate;
import main.Main;

public class QnaController {

	// 메뉴
	public void printMenu() throws Exception {
		System.out.println("----- QNA -----");

		System.out.println("1.문의 작성");
		System.out.println("2.전체 조회 번호이용");
		System.out.println("3.게시글 상세 조회 (번호 이용해서)");
		System.out.println("4.문의 검색 (제목으로)");
		System.out.println("5.문의 내용 수정");
		System.out.println("6.문의 삭제");

		System.out.print("메뉴 번호 입력 : ");
		String num = Main.SC.nextLine();

		switch (num) {
		case "1":
			userWrite();
			break;
		case "2":
			selectQnaOne();
			break;
		case "3":
			selectQnaTwo();
			break;
		case "4":
			searchByTitle();
			break;
		case "5":
			userEditContent();
			break;
		case "6":
			userDelete();
			break;
		
		default:
			System.out.println("잘못된 입력입니다.");
		}
	}

	// 1문의 작성(유저만)
	private void userWrite() throws Exception {

		// 로그인 해야만
		if (Main.loginMember == null) {
			System.out.println("로그인 해야만");
			return;
		}

		if (Main.loginMember.getId().equals("ADMIN")) {
			System.out.println("관리자 전용");
			Connection conn = JDBCTemplate.getConn();

			// sql 준비
			String sql = "INSERT INTO QNA(NO, MASTER_TITLE, MASTER_CONTENTS,CATEGORY) VALUES(SEQ_QNA_NO.NEXTVAL , ?,? , ?)";

			System.out.print("관리자 제목 : ");
			String masterTitle = Main.SC.nextLine();
			System.out.print("관리자 내용: ");
			String masterContents = Main.SC.nextLine();
			System.out.println("목차 : ");
			String category = Main.SC.nextLine();

			// sql 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, masterTitle);
			pstmt.setString(2, masterContents);
			pstmt.setString(3, category);
			
			int result = pstmt.executeUpdate();

			// 결과 처리
			if (result != 1) {
				System.out.println("문의 작성 실패 ...");
				return;
			}
			System.out.println("문의 작성 성공 !");
		} else {
			System.out.println("유저 전용");
			Connection conn = JDBCTemplate.getConn();

			// if(Main.loginMember.getuserId().equals("admin")) {

//			if (Main.loginMember == null) {
			// System.out.println("로그인 하고 오세요");
			// return;

			// sql 준비
			String sql = "INSERT INTO QNA(NO, USER_TITLE, USER_CONTENTS,CATEGORY,WRITER_NO) VALUES(SEQ_QNA_NO.NEXTVAL , ?, ?,? ,?)";

			System.out.print("유저 제목 : ");
			String userTitle = Main.SC.nextLine();
			System.out.print("유저 내용: ");
			String userContents = Main.SC.nextLine();
			System.out.print("목차: ");
			String category = Main.SC.nextLine();

//			System.out.print("작성 번호: "); // FIXME: 여기 문제있음 // 글쓴이 누구인가?
//			String writerNo = Main.SC.nextLine(); // FIXME: 여기 문제있음

			// 사장은 답변
			// 일반 유저가 하
			// 사장을 판별하기 위해서
			// 사장id를 admin으로 하고
			// 문자 비교를 한다
			// membervo에서

			// sql 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userTitle);
			pstmt.setString(2, userContents);
			pstmt.setString(3, category);
			pstmt.setString(4, Main.loginMember.getNo()); // pstmt.setString(4, Main.loginMember.getNo()); // 6
			int result = pstmt.executeUpdate();

			// 결과 처리
			if (result != 1) {
				System.out.println("문의 작성 실패 ...");
				return;
			}

			// JDBCTemplate에서 conn.setAutoCommit(false); 라서 커밋
		
			System.out.println("문의 작성 성공 !");
		}
	}

	// conn 준비
//		Connection conn = db.JDBCTemplate.getConn();
//
//		// if(Main.loginMember.getuserId().equals("admin")) {
//
////		if (Main.loginMember == null) {
//		// System.out.println("로그인 하고 오세요");
//		// return;
//
//		// sql 준비
//		String sql = "INSERT INTO QNA(NO, USER_TITLE, USER_CONTENTS,CATEGORY,WRITER_NO) VALUES(SEQ_QNA_NO.NEXTVAL , ?, ?,? ,?)";
//
//		System.out.print("유저 제목 : ");
//		String userTitle = Main.SC.nextLine();
//		System.out.print("유저 내용: ");
//		String userContents = Main.SC.nextLine();
//		System.out.print("목차: ");
//		String category = Main.SC.nextLine();
//
////		System.out.print("작성 번호: "); // FIXME: 여기 문제있음 // 글쓴이 누구인가?
////		String writerNo = Main.SC.nextLine(); // FIXME: 여기 문제있음
//
//		// 사장은 답변
//		// 일반 유저가 하
//		// 사장을 판별하기 위해서
//		// 사장id를 admin으로 하고
//		// 문자 비교를 한다
//		// membervo에서
//
//		// sql 실행
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, userTitle);
//		pstmt.setString(2, userContents);
//		pstmt.setString(3, category);
//		pstmt.setString(4, Main.LoginMember.getNo()); // pstmt.setString(4, Main.loginMember.getNo()); // 6
//		int result = pstmt.executeUpdate();
//
//		// 결과 처리
//		if (result != 1) {
//			System.out.println("문의 작성 실패 ...");
//			return;
//		}
//
//		// JDBCTemplate에서 conn.setAutoCommit(false); 라서 커밋
//		conn.commit();
//		System.out.println("문의 작성 성공 !");
//	}// method
//
//	// 2문의 작성 (관리자만)
//	private void masterWrite() throws Exception {
//
//		// conn 준비
//		Connection conn = db.JDBCTemplate.getConn();
//
//		// sql 준비
//		String sql = "INSERT INTO QNA(NO, MASTER_TITLE, MASTER_CONTENTS,CATEGORY) VALUES(SEQ_QNA_NO.NEXTVAL , ?,? , ?)";
//
//		System.out.print("관리자 제목 : ");
//		String masterTitle = Main.SC.nextLine();
//		System.out.print("관리자 내용: ");
//		String masterContents = Main.SC.nextLine();
//		System.out.println("목차 : ");
//		String category = Main.SC.nextLine();
//
//		// sql 실행
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, masterTitle);
//		pstmt.setString(2, masterContents);
//		pstmt.setString(3, category);
//		int result = pstmt.executeUpdate();
//
//		// 결과 처리
//		if (result != 1) {
//			System.out.println("문의 작성 실패 ...");
//			return;
//		}
//		System.out.println("문의 작성 성공 !");
//	}// method

	// 3문의 내용 수정(유저것만)
	private void userEditContent() throws Exception {

		if (Main.loginMember == null) {
			System.out.println("로그인 해야만");
			return;
		}

		if (Main.loginMember.getId().equals("admin")) {
			System.out.println("관리자 전용");
			Connection conn = JDBCTemplate.getConn();

			// conn

			System.out.print("수정할 관리자 제목 : ");
			String masterTitle = Main.SC.nextLine();
			System.out.print("수정할 관리자 내용 : ");
			String masterContents = Main.SC.nextLine();
			System.out.print("게시글 번호 : ");
			String no = Main.SC.nextLine();
			System.out.println("목차 : ");
			String category = Main.SC.nextLine();

			// sql
			String sql = "UPDATE QNA SET MASTER_TITLE = ? , MASTER_CONTENTS = ? ,CATEGORY =?  WHERE NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, masterTitle);
			pstmt.setString(2, masterContents);
			pstmt.setString(3, category);
			pstmt.setString(4, no);
			int result = pstmt.executeUpdate();

			// result
			if (result != 1) {
				System.out.println("관리자 문의 수정 실패 ...");
				return;
			}
			System.out.println("관리자 문의 수정 성공 !");
		}

		// 결과 처리

		else {
			System.out.println("유저 전용");
			Connection conn = JDBCTemplate.getConn();

		
			
			

			// sql
			String sql = "UPDATE QNA SET USER_TITLE = ?, USER_CONTENTS = ?, CATEGORY = ? WHERE NO = ? ";
			
			System.out.print("게시글 번호 : ");
			String no = Main.SC.nextLine();
			System.out.print("수정할 유저 제목 : ");
			String userTitle = Main.SC.nextLine();
			System.out.print("수정할 유저 내용 : ");
			String userContents = Main.SC.nextLine();
//			System.out.print("게시글 유저 번호 : ");
//			String writerNo = Main.SC.nextLine();
			System.out.println("목차 : ");
			String category = Main.SC.nextLine();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userTitle);
			pstmt.setString(2, userContents);
//			pstmt.setString(3, writerNo);  WRITER_NO = ?,
			pstmt.setString(3, category);
			pstmt.setString(4, no);
			int result = pstmt.executeUpdate();

			// result
			if (result != 1) {
				System.out.println("문의 수정 실패 ...");
				return;
			}
			System.out.println("문의 수정 성공 !");
			
		}
//		{
//			System.out.println("유저 전용");
			// 4 문의 내용 수정(관리자것만)

			// conn
//			Connection conn = db.JDBCTemplate.getConn();
//
//			System.out.print("수정할 관리자 제목 : ");
//			String masterTitle = Main.SC.nextLine();
//			System.out.print("수정할 관리자 내용 : ");
//			String masterContents = Main.SC.nextLine();
//			System.out.print("게시글 번호 : ");
//			String no = Main.SC.nextLine();
//			System.out.println("목차 : ");
//			String category = Main.SC.nextLine();
//
//			// sql
//			String sql = "UPDATE QNA SET MASTER_TITLE = ? , MASTER_CONTENTS = ? ,CATEGORY =?  WHERE NO = ?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, masterTitle);
//			pstmt.setString(2, masterContents);
//			pstmt.setString(3, category);
//			pstmt.setString(4, no);
//			int result = pstmt.executeUpdate();
//
//			// result
//			if (result != 1) {
//				System.out.println("관리자 문의 수정 실패 ...");
//				return;
//			}
//			System.out.println("관리자 문의 수정 성공 !");
		}
	

	// 5유저 문의 삭제
	private void userDelete() throws Exception {
		System.out.println("-----문의 삭제-----");
		// conn
		Connection conn = JDBCTemplate.getConn();

		System.out.print("삭제할 문의 번호 :");
		String no = Main.SC.nextLine();

		// sql
		String sql = "UPDATE QNA SET DEL_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();

		// result
		if (result != 1) {
			System.out.println("게시글 삭제 실패 ...");
			return;
		}
		System.out.println("게시글 삭제 성공 !");
	}

	// 6문의 검색 (제목으로)
	private void searchByTitle() throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConn();

		System.out.print("검색할 제목 : ");
		String value = Main.SC.nextLine();

		// sql
		String sql =   "SELECT * FROM QNA WHERE USER_TITLE LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		ResultSet rs = pstmt.executeQuery();

		// rs
		List<QnaVo> voList = new ArrayList<QnaVo>();
		while (rs.next()) {
			String no = rs.getString("NO");
			String writerNo = rs.getString("WRITER_NO");
			String userTitle = rs.getString("USER_TITLE");
			String userContents = rs.getString("USER_CONTENTS");
			String enrollDate = rs.getString("ENROLL_DATE");
			String masterTitle = rs.getString("MASTER_TITLE");
			String masterContents = rs.getString("MASTER_CONTENTS");
			String category = rs.getString("CATEGORY");
			QnaVo vo = new QnaVo(no, writerNo, userTitle, userContents, enrollDate, masterTitle, masterContents,
					category, null, null);
			voList.add(vo);
		}

		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("작성일시");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.println();

		for (QnaVo vo : voList) {
			System.out.print(vo.getNo());
			System.out.print(" | ");
			System.out.print(vo.getEnrollDate());
			System.out.print(" | ");
			System.out.print(vo.getUserTitle());
			System.out.println();
		}

	}

// 7(전체 조회 번호이용
	private void selectQnaOne() throws Exception {
		//System.out.println("자신의 아이디를 검색하세요");
		//if (Main.LoginMember.getId().equals(Main.SC.nextLine())) {
		//	System.out.println("관리자 전용");
		// conn
		Connection conn = JDBCTemplate.getConn();

		System.out.print("전체 조회 할 게시글 번호 : ");

		// sql
		String sql = "SELECT * FROM QNA WHERE WRITER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
	//	String num = Main.SC.nextLine();
		pstmt.setString(1, Main.loginMember.getNo());
		ResultSet rs = pstmt.executeQuery();

		// rs
		QnaVo vo = null;
		if (rs.next()) {
			String no = rs.getString("NO");
			String userTitle = rs.getString("USER_TITLE");
			String userContents = rs.getString("USER_CONTENTS");
			String enrollDate = rs.getString("ENROLL_DATE");
			String category = rs.getString("CATEGORY");
			String writerNo = rs.getString("WRITER_NO");
			String masterTitle = rs.getString("MASTER_TITLE");
			String masterContents = rs.getString("MASTER_CONTENTS");
			String delYn = rs.getString("DEL_YN");

			vo = new QnaVo(no, userTitle, userContents, enrollDate, category, writerNo, masterTitle, masterContents,
					delYn, null);
		}

		if (vo == null) {
			System.out.println("문의 전체 조회 실패 ...");
			return;
		}

		System.out.println("문의 전체 조회 성공 !");
		System.out.println("-------------");
		System.out.println("번호 : " + vo.getNo());
		System.out.println("제목 : " + vo.getUserTitle());
		System.out.println("작성일시 : " + vo.getEnrollDate());
		System.out.println("내용 : " + vo.getUserContents());
		System.out.println("-------------");

		}
	// class

	// 8게시글 상세 조회 (번호 이용해서)
	private void selectQnaTwo() throws Exception {
		System.out.println("게시물 번호 조회");
		Connection conn = JDBCTemplate.getConn();
		String sql = "SELECT * FROM QNA WHERE NO =? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		System.out.print("조회할 게시판 번호를 입력해주세요 : ");
		String n = Main.SC.nextLine();
		pstmt.setString(1, n);
		ResultSet rs = pstmt.executeQuery();

		QnaVo vo = null;
		if (rs.next()) {
			String no = rs.getString("NO");
			String userTitle = rs.getString("USER_TITLE");
			String userContents = rs.getString("USER_CONTENTS");
			String enrollDate = rs.getString("ENROLL_DATE");
			String category = rs.getString("CATEGORY");
			String writerNo = rs.getString("WRITER_NO");
			String masterTitle = rs.getString("MASTER_TITLE");
			String masterContents = rs.getString("MASTER_CONTENTS");

			vo = new QnaVo(no, userTitle, userContents, enrollDate, category, writerNo, masterTitle, masterContents,
					null, null);
		}
		if (vo == null) {
			System.out.println("게시글 상세조회 실패,,,");
			return;
		}
		System.out.println("게시글 상세 조회 성공!");
		System.out.println("---------------------");
		System.out.println("번호 : " + vo.getNo());
		System.out.println("제목 : " + vo.getUserTitle());
		System.out.println("작성일시 : " + vo.getEnrollDate());
		System.out.println("내용 : " + vo.getUserContents());
		System.out.println("----------------------");

	}
}