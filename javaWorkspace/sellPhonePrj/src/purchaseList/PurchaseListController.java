package purchaseList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import member.MemberController;
import util.JDBCTemplate;

public class PurchaseListController {

	// 핸드폰 판매하기
	public void sellPhone() throws Exception {
		// 점수
		int score = 100;
		// 차감할 점수 1 (배터리)
		int minusBattry = 0;
		// 차감할 점수 2 (스크래치)
		int minusScrach = 0;
		// 가격
		String price = "";
		// 등급
		String grade = "";
		// 기종명
		String inputPhoneName = "";
		// PhoneNo
		String phoneNumber = "";
		System.out.println("기종을 선택해주세요.");
		System.out.println("1. 아이폰");
		System.out.println("2. 갤럭시");
		String inputNum1 = Main.SC.nextLine();
		// 값 입력받기
		if (inputNum1.equals("1")) {
			System.out.println("1. 기본 모델");
			System.out.println("2. 프로 모델");
			System.out.println("3. Xs");
			String inputNum2 = Main.SC.nextLine();
			if (inputNum2.equals("1")) {
				System.out.println("모델의 넘버를 입력해주세요.");
				String inputNumber1 = Main.SC.nextLine();
				if (Integer.parseInt(inputNumber1) > 15 || Integer.parseInt(inputNumber1) < 11) {
					System.out.println("존재하지 않는 기종입니다. 처음부터 다시 진행해주세요.");
					return;
				}
				inputPhoneName = "IPHONE ";
				inputPhoneName += inputNumber1;
			} else if (inputNum2.equals("2")) {
				System.out.println("모델의 넘버를 입력해주세요.");
				String inputNumber2 = Main.SC.nextLine();
				if (Integer.parseInt(inputNumber2) > 15 || Integer.parseInt(inputNumber2) < 11) {
					System.out.println("존재하지 않는 기종입니다. 처음부터 다시 진행해주세요.");
					return;
				}
				inputPhoneName = "IPHONE PRO ";
				inputPhoneName += inputNumber2;
			} else if (inputNum2.equals("3")) {
				inputPhoneName = "IPHONE XS";
			}
		} else if (inputNum1.equals("2")) {
			System.out.println("시리즈의 넘버를 입력해주세요.");
			String inputNumber3 = Main.SC.nextLine();
			if (Integer.parseInt(inputNumber3) > 24 || Integer.parseInt(inputNumber3) < 21) {
				System.out.println("존재하지 않는 기능입니다. 처음부터 다시 진행해주세요.");
				return;
			}
			inputPhoneName = "GALAXY S";
			inputPhoneName += inputNumber3;
		}

		System.out.println("배터리의 성능 수치를 입력해주세요.");
		String inputBatteryNum = Main.SC.nextLine();
		System.out.println("핸드폰 액정의 스크래치 개수를 입력해주세요.");
		String inputScratchNum = Main.SC.nextLine();

		// 등급 측정하기
		System.out.println("등급 측정 중...");
		int batteryNum = Integer.parseInt(inputBatteryNum);
		int scratchNum = Integer.parseInt(inputScratchNum);

		if (batteryNum > 90 && batteryNum <= 100) {
			minusBattry = 0;
		} else if (batteryNum > 80 && batteryNum <= 90) {
			minusBattry = 10;
		} else if (batteryNum > 70 && batteryNum <= 80) {
			minusBattry = 15;
		} else if (batteryNum > 60 && batteryNum <= 70) {
			minusBattry = 20;
		} else {
			minusBattry = 25;
		}

		System.out.println("점수 차감 .. -" + minusBattry);
		score -= minusBattry;

		minusScrach = scratchNum * 5;
		System.out.println("점수 차감 .. -" + minusScrach);
		score -= minusScrach;

		if (score > 90 && score <= 100) {
			grade = "A";
		} else if (score > 80 && score <= 90) {
			grade = "B";
		} else if (score > 70 && score <= 80) {
			grade = "C";
		} else if (score > 60 && score <= 70) {
			grade = "D";
		} else {
			System.out.println("구매하지 않는 기종입니다.");
			return;
		}
		// 커넥션 가져오기
		Connection conn = JDBCTemplate.getConn();
		// SQL1
		String sql1 = "SELECT NO, GRADE_PRICE FROM PHONE WHERE GRADE = ? AND MODEL_NAME = ?";
		PreparedStatement pstmt1 = conn.prepareStatement(sql1);
		pstmt1.setString(1, grade);
		pstmt1.setString(2, inputPhoneName);
		ResultSet rs = pstmt1.executeQuery();
		// 값 불러오기
		if (rs.next()) {
			String no = rs.getString("NO");
			String gradePrice = rs.getString("GRADE_PRICE");
			phoneNumber += no;
			price += gradePrice;
		}
		System.out.println("판매하실 기종의 가격은 " + price + "원 입니다.");
		System.out.println("진행하시겠습니까?");
		System.out.println("1. 예");
		System.out.println("2. 아니오");
		String str = Main.SC.nextLine();
		if (str.equals("1")) {
			// SQL2
			String sql = "INSERT INTO PURCHASE_LIST(NO, PHONE_NUMBER, MEMBER_ID, BATTERY_NUM, SCRATCH_NUM, TOTAL_SCORE) VALUES(SEQ_PURCHASE_LIST_NO.NEXTVAL, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNumber);
			pstmt.setString(2, Main.loginMember.getId());
			pstmt.setString(3, inputBatteryNum);
			pstmt.setString(4, inputScratchNum);
			pstmt.setInt(5, score);
			// 값 불러오기
			int result = pstmt.executeUpdate();
			if (result != 1) {
				System.out.println("중고폰 판매 실패!");
				return;
			}
			System.out.println("중고폰 판매 성공!");
			System.out.println(price + "원이 입금되었습니다.");
		}else if(str.equals("2")) {
			System.out.println("판매 취소하셨습니다.");
			return;
		}else {
			System.out.println("잘못된 입력입니다. 다시 진행해주세요.");
		}
		
		
	}// 메소드
		// 판매한 핸드폰 조회하기

	public void sellInfo() throws Exception {
		// 커넥션 가져오기
		Connection conn = JDBCTemplate.getConn();
		// SQL
		String sql = "SELECT L.NO , L.PHONE_NUMBER , L.MEMBER_ID , L.SCRATCH_NUM , L.BATTERY_NUM , L.TOTAL_SCORE , TO_CHAR(L.ENROLL_DATE, 'YYYY.MM.DD HH:MI') ENROLL_DATE , P.MODEL_NAME , P.GRADE , P.GRADE_PRICE FROM PURCHASE_LIST L JOIN PHONE P ON P.NO = L.PHONE_NUMBER WHERE MEMBER_ID = ?";
		PreparedStatement pstmt = conn.prepareCall(sql);
		pstmt.setString(1, Main.loginMember.getId());
		ResultSet rs = pstmt.executeQuery();
		// 갑 불러오기
		PurchaseListVo vo = null;
		ArrayList<PurchaseListVo> list = new ArrayList<PurchaseListVo>();
		while (rs.next()) {
			String no = rs.getString("NO");
			String phoneNumber = rs.getString("PHONE_NUMBER");
			String memberId = rs.getString("MEMBER_ID");
			String scratchNum = rs.getString("SCRATCH_NUM");
			String batteryNum = rs.getString("BATTERY_NUM");
			String totalScore = rs.getString("TOTAL_SCORE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modelName = rs.getString("MODEL_NAME");
			String grade = rs.getString("GRADE");
			String gradePrice = rs.getString("GRADE_PRICE");
			vo = new PurchaseListVo(no, phoneNumber, memberId, scratchNum, batteryNum, totalScore, enrollDate,
					modelName, grade, gradePrice);
			list.add(vo);
		}
		if (vo == null) {
			System.out.println("판매 내역 조회 실패..");
			return;
		}
		// 값 출력하기
		System.out.println("중고폰 판매 내역 조회 성공!");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------");
		System.out.print("NO | ");
		System.out.print("리스트 번호 | ");
		System.out.print("아이디   | ");
		System.out.print("   기종     | ");
		System.out.print("등급 | ");
		System.out.print(" 가격   | ");
		System.out.print("스크래치 개수| ");
		System.out.print("배터리 성능 | ");
		System.out.print("종합 점수 | ");
		System.out.println("    판매 날짜     |");

		for (PurchaseListVo pvo : list) {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");
			System.out.print(pvo.getNo());
			System.out.print("  |   ");
			System.out.print(pvo.getPhoneNumber());
			System.out.print("번    | ");
			System.out.print(pvo.getMemberId());
			System.out.print(" | ");
			System.out.print(pvo.getModelName());
			System.out.print(" |  ");
			System.out.print(pvo.getGrade());
			System.out.print("  | ");
			System.out.print(pvo.getGradePrice());
			System.out.print(" |    ");
			System.out.print(pvo.getScratchNum());
			System.out.print("개    |    ");
			System.out.print(pvo.getBatteryNum());
			System.out.print("%    |   ");
			System.out.print(pvo.getTotalScore());
			System.out.print("점  | ");
			System.out.print(pvo.getEnrollDate());
			System.out.println(" | ");

		} // 반복문

	}// 메소드

	public boolean masterMenu() throws Exception {
		System.out.println("1. 매입 내역 전체 조회");
		System.out.println("2. 매입 내역 상세 조회(ID)");
		System.out.println("3. 종료");
		String inputNum = Main.SC.nextLine();
		switch (inputNum) {
		case "1":
			masterInfo();
			break;
		case "2":
			masterDetailInfo();
			break;
		case "3":
			System.out.println("종료..");
			return Main.run = false;
		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}
		return true;
	}

	private void masterInfo() throws Exception {
		// 커넥션 가져오기
		Connection conn = JDBCTemplate.getConn();
		// SQL
		String sql = "SELECT L.NO , L.PHONE_NUMBER , L.MEMBER_ID , L.SCRATCH_NUM , L.BATTERY_NUM , L.TOTAL_SCORE , TO_CHAR(L.ENROLL_DATE, 'YYYY.MM.DD HH:MI') ENROLL_DATE , P.MODEL_NAME , P.GRADE , P.GRADE_PRICE FROM PURCHASE_LIST L JOIN PHONE P ON P.NO = L.PHONE_NUMBER";
		PreparedStatement pstmt = conn.prepareCall(sql);
		ResultSet rs = pstmt.executeQuery();
		// 갑 불러오기
		PurchaseListVo vo = null;
		ArrayList<PurchaseListVo> list = new ArrayList<PurchaseListVo>();
		while (rs.next()) {
			String no = rs.getString("NO");
			String phoneNumber = rs.getString("PHONE_NUMBER");
			String memberId = rs.getString("MEMBER_ID");
			String scratchNum = rs.getString("SCRATCH_NUM");
			String batteryNum = rs.getString("BATTERY_NUM");
			String totalScore = rs.getString("TOTAL_SCORE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modelName = rs.getString("MODEL_NAME");
			String grade = rs.getString("GRADE");
			String gradePrice = rs.getString("GRADE_PRICE");
			vo = new PurchaseListVo(no, phoneNumber, memberId, scratchNum, batteryNum, totalScore, enrollDate,
					modelName, grade, gradePrice);
			list.add(vo);
		}
		if (vo == null) {
			System.out.println("판매 내역 조회 실패..");
			return;
		}
		// 값 출력하기
		System.out.println("중고폰 판매 내역 조회 성공!");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------");
		System.out.print("NO | ");
		System.out.print("리스트 번호 | ");
		System.out.print("아이디   | ");
		System.out.print("   기종     | ");
		System.out.print("등급 | ");
		System.out.print(" 가격   | ");
		System.out.print("스크래치 개수| ");
		System.out.print("배터리 성능 | ");
		System.out.print("종합 점수 | ");
		System.out.println("    판매 날짜     |");

		for (PurchaseListVo pvo : list) {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");
			System.out.print(pvo.getNo());
			System.out.print("  |   ");
			System.out.print(pvo.getPhoneNumber());
			System.out.print("번    | ");
			System.out.print(pvo.getMemberId());
			System.out.print(" | ");
			System.out.print(pvo.getModelName());
			System.out.print(" |  ");
			System.out.print(pvo.getGrade());
			System.out.print("  | ");
			System.out.print(pvo.getGradePrice());
			System.out.print(" |    ");
			System.out.print(pvo.getScratchNum());
			System.out.print("개    |    ");
			System.out.print(pvo.getBatteryNum());
			System.out.print("%    |   ");
			System.out.print(pvo.getTotalScore());
			System.out.print("점  | ");
			System.out.print(pvo.getEnrollDate());
			System.out.println(" | ");

		} // 반복문
	}

	private void masterDetailInfo() throws Exception {
		System.out.println("조회하고 싶은 유저의 아이디를 입력하세요.");
		System.out.println("유저ID : ");
		String input1 = Main.SC.nextLine();

		ArrayList<PurchaseListVo> list = new ArrayList<PurchaseListVo>();
		Connection conn = JDBCTemplate.getConn();
		String sql = "SELECT L.NO , L.PHONE_NUMBER , L.MEMBER_ID , L.SCRATCH_NUM , L.BATTERY_NUM , L.TOTAL_SCORE , TO_CHAR(L.ENROLL_DATE, 'YYYY.MM.DD HH:MI') ENROLL_DATE , P.MODEL_NAME , P.GRADE , P.GRADE_PRICE FROM PURCHASE_LIST L JOIN PHONE P ON P.NO = L.PHONE_NUMBER WHERE L.MEMBER_ID = ?";
		PreparedStatement pstmt = conn.prepareCall(sql);
		pstmt.setString(1, input1);
		ResultSet rs = pstmt.executeQuery();
		// 갑 불러오기
		PurchaseListVo vo = null;
		while (rs.next()) {
			String no = rs.getString("NO");
			String phoneNumber = rs.getString("PHONE_NUMBER");
			String memberId = rs.getString("MEMBER_ID");
			String scratchNum = rs.getString("SCRATCH_NUM");
			String batteryNum = rs.getString("BATTERY_NUM");
			String totalScore = rs.getString("TOTAL_SCORE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modelName = rs.getString("MODEL_NAME");
			String grade = rs.getString("GRADE");
			String gradePrice = rs.getString("GRADE_PRICE");
			vo = new PurchaseListVo(no, phoneNumber, memberId, scratchNum, batteryNum, totalScore, enrollDate,
					modelName, grade, gradePrice);
			list.add(vo);
		}
		if (vo == null) {
			System.out.println("판매 내역 조회 실패..");
			return;
		}
		// 값 출력하기
		System.out.println("중고폰 판매 내역 조회 성공!");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------");
		System.out.print("NO | ");
		System.out.print("리스트 번호 | ");
		System.out.print("아이디   | ");
		System.out.print("   기종     | ");
		System.out.print("등급 | ");
		System.out.print(" 가격   | ");
		System.out.print("스크래치 개수| ");
		System.out.print("배터리 성능 | ");
		System.out.print("종합 점수 | ");
		System.out.println("    판매 날짜     |");

		for (PurchaseListVo pvo : list) {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");
			System.out.print(pvo.getNo());
			System.out.print("  |   ");
			System.out.print(pvo.getPhoneNumber());
			System.out.print("번    | ");
			System.out.print(pvo.getMemberId());
			System.out.print(" | ");
			System.out.print(pvo.getModelName());
			System.out.print(" |  ");
			System.out.print(pvo.getGrade());
			System.out.print("  | ");
			System.out.print(pvo.getGradePrice());
			System.out.print(" |    ");
			System.out.print(pvo.getScratchNum());
			System.out.print("개    |    ");
			System.out.print(pvo.getBatteryNum());
			System.out.print("%    |   ");
			System.out.print(pvo.getTotalScore());
			System.out.print("점  | ");
			System.out.print(pvo.getEnrollDate());
			System.out.println(" | ");

		} // 반복문
	}

}// 클래스