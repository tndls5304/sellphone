package gradeList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import battery.BatteryVo;
import main.Main;
import member.MemberController;
import modelClass.ModelClassVo;
import scratch.ScratchVo;
import util.JDBCTemplate;

public class GradeListController {
	//메뉴얼
	public void gradeListController() throws Exception {
		
		System.out.println("1. 가격 산정표 조회");
		System.out.println("2. 등급 산정표 조회");
		String inputNum = Main.SC.nextLine();
		switch (inputNum) {
		case "1":
			priceSet();
			break;
		case "2":
			gradeSet();
			break;
		default:
			System.out.println("잘못된 입력입니다. 다시 시도해주세요");
			break;
		}//스위치
	}//메소드
	
	//등급 산정표 조회 메뉴얼
	private void gradeSet() throws Exception {
		System.out.println("1. 등급 기준");
		System.out.println("2. 차감 기준");
		String inputNum1 = Main.SC.nextLine();
		if (inputNum1.equals("1")) {
			modelClass();
		} else if (inputNum1.equals("2")) {
			System.out.println("1. 배터리 성능");
			System.out.println("2. 스크래치 개수");
			String inputNum2 = Main.SC.nextLine();
			if (inputNum2.equals("1")) {
				battery();
			} else if (inputNum2.equals("2")) {
				scratch();
			}
		}//이프
	}//메소드
	
	// 차감기준 - 스크래치
	private void scratch() throws Exception {
		//커넥션 가져오기
		Connection conn = JDBCTemplate.getConn();
		
		//SQL
		String sql = "SELECT NO, NUM, SCORE, TO_CHAR(ENROLL_DATE, 'YYYY.MM.DD') ENROLL_DATE FROM SCRATCH";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		//값 불러오기
		ScratchVo vo = null;
		ArrayList<ScratchVo> list = new ArrayList<ScratchVo>();
		while (rs.next()) {
			String no = rs.getString("NO");
			String num = rs.getString("NUM");
			String score = rs.getString("SCORE");
			String enrollDate = rs.getString("ENROLL_DATE");
			vo = new ScratchVo(no, num, score, enrollDate);
			list.add(vo);
		}
		if (vo == null) {
			System.out.println("조회 실패..");
		}
		// 값 출력하기
		System.out.println("조회 성공!");
		for (ScratchVo bvo : list) {
			System.out.print("No | ");
			System.out.print(bvo.getNo());
			System.out.print(" 스크래치 개수 |");
			System.out.print(bvo.getNum());
			System.out.print("| 차감 점수 |");
			System.out.print(bvo.getScore());
			System.out.print("| 등록일 |");
			System.out.println(bvo.getEnrolldate());
		}// 반복문
	}// 메소드
	
	//차감 기준 - 배터리
	private void battery() throws Exception {
		// 커넥션 가져오기
		Connection conn = JDBCTemplate.getConn();
		//SQL
		String sql = "SELECT PERCENTAGE, SCORE, TO_CHAR(ENROLL_DATE, 'YYYY.MM.DD') ENROLL_DATE FROM BATTERY";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		BatteryVo vo = null;
		ArrayList<BatteryVo> list = new ArrayList<BatteryVo>();
		// 값 불러오기
		while (rs.next()) {
			String percentage = rs.getString("PERCENTAGE");
			String score = rs.getString("SCORE");
			String enrollDate = rs.getString("ENROLL_DATE");
			vo = new BatteryVo(percentage, score, enrollDate);
			list.add(vo);
		}
		if (vo == null) {
			System.out.println("조회 실패..");
		}
		// 값 출력하기
		System.out.println("조회 성공!");
		for (BatteryVo bvo : list) {
			System.out.print(" 퍼센티지 |");
			System.out.print(bvo.getPercentage());
			System.out.print("| 차감 점수 |");
			System.out.print(bvo.getScore());
			System.out.print("| 등록일 |");
			System.out.println(bvo.getEnrolldate());
			
		}// 반복문
	}// 메소드
	
	//등급 산정표 조회 - 등급기준
	private void modelClass() throws Exception {
		Connection conn = JDBCTemplate.getConn();
		String sql = "SELECT NO, GRADE, TOTAL_SCORE, TO_CHAR(ENROLL_DATE, 'YYYY.MM.DD') ENROLL_DATE FROM MODEL_CLASS";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ModelClassVo vo = null;
		ArrayList<ModelClassVo> list = new ArrayList<ModelClassVo>();
		while (rs.next()) {
			String no = rs.getString("NO");
			String grade = rs.getString("GRADE");
			String totalScore = rs.getString("TOTAL_SCORE");
			String enrollDate = rs.getString("ENROLL_DATE");
			vo = new ModelClassVo(no, grade, totalScore, enrollDate);
			list.add(vo);
		}
		if (vo == null) {
			System.out.println("조회 실패..");
		}
		System.out.println("조회 성공!");
		for (ModelClassVo mvo : list) {
			System.out.print("NO : ");
			System.out.print(mvo.getNo());
			System.out.print("| 등급 : ");
			System.out.print(mvo.getGrade());
			System.out.print("| 범위 : ");
			System.out.print(mvo.getTotalScore());
			System.out.print("| 등록일 : ");
			System.out.println(mvo.getEnrollDate());
		}
	}
	//가격 산정표 조회
	private void priceSet() throws Exception {
		// 값 입력받기
		System.out.println("기종을 선택해주세요.");
		System.out.println("1. 아이폰");
		System.out.println("2. 갤럭시");
		String inputNum1 = Main.SC.nextLine();
		String inputPhoneName = "";
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
		// 커넥션 가져오기
		Connection conn = JDBCTemplate.getConn();
		// SQL
		String sql = "SELECT NO , MODEL_NAME , GRADE , FIRST_PRICE , GRADE_PRICE , PERCENTAGE , TO_CHAR(ENROLL_DATE, 'YYYY.MM.DD HH24:MI') ENROLL_DATE FROM PRICE_SET WHERE MODEL_NAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputPhoneName);
		ResultSet rs = pstmt.executeQuery();
		// 값 가져오기
		PriceSetVo vo = null;
		ArrayList<PriceSetVo> list = new ArrayList<PriceSetVo>();
		while (rs.next()) {
			String no = rs.getString("NO");
			String modelName = rs.getString("MODEL_NAME");
			String grade = rs.getString("GRADE");
			String firstPrice = rs.getString("FIRST_PRICE");
			String gradePrice = rs.getString("GRADE_PRICE");
			String percentage = rs.getString("PERCENTAGE");
			String enrollDate = rs.getString("ENROLL_DATE");
			vo = new PriceSetVo(no, modelName, grade, firstPrice, gradePrice, percentage, enrollDate);
			list.add(vo);
		}
		if (vo == null) {
			System.out.println("조회 실패..");
			return;
		}
		// 값 출력하기
		
		System.out.print("NO");
		System.out.print(" | ");
		System.out.print("    기종    ");
		System.out.print(" | ");
		System.out.print("   원가  ");
		System.out.print(" | ");
		System.out.print("등급");
		System.out.print(" | ");
		System.out.print(" 중고가 ");
		System.out.print("  | ");
		System.out.print("퍼센티지");
		System.out.print(" |    ");
		System.out.print(" 산정 날짜");
		System.out.println("      |");
		
		for (PriceSetVo pvo : list) {
			System.out.print(pvo.getNo());
			System.out.print(" | ");
			System.out.print(pvo.getModelName());
			System.out.print("  |  ");
			System.out.print(pvo.getFirstPrice());
			System.out.print(" |  ");
			System.out.print(pvo.getGrade());
			System.out.print("  |  ");
			System.out.print(pvo.getGradePrice());
			System.out.print(" |  ");
			System.out.print(pvo.getPercentage());
			System.out.print("  |  ");
			System.out.print(pvo.getEnrollDate());
			System.out.println(" | ");
		}
	}
}
