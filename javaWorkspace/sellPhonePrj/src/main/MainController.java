package main;

import java.util.Scanner;

import gradeList.GradeListController;
import member.MemberController;
import phone.PhoneController;
import purchaseList.PurchaseListController;
import util.Util;

public class MainController {
	// 스캐너 생성
	public static final Scanner SC = new Scanner(System.in);
	public static boolean run = true;
	//메뉴얼
	public void printMenu() throws Exception {
		MemberController mcl = new MemberController();
		mcl.printMenu();
		
		if(MemberController.mvo ==null) {
			System.out.println("로그인 후 이용해 주세요.");
			return;
		}
		// 객체 생성
		PhoneController phc = new PhoneController();
		PurchaseListController plc = new PurchaseListController();
		GradeListController glc = new GradeListController();
		
		
		while (run) {
			if(MemberController.mvo.getId().equals("addmin")) {
				plc.masterMenu();
			} else {
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------");
				System.out.println("1. 중고폰 판매");
				System.out.println("2. 판매한 중고폰 조회");
				System.out.println("3. 중고폰 시세 전체 조회");
				System.out.println("4. 중고폰 시세 상세 조회");
				System.out.println("5. 등급 산정표 조회");
				
				
				
				System.out.println("9. 종료");
				
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------");
				String inputNum = MainController.SC.nextLine();
				switch (inputNum) {
				case "1":
					plc.sellPhone();
					break;
				case "2":
					plc.sellInfo();
					break;
				case "3":
					phc.phoneInfo();
					break;
				case "4":
					phc.phoneDetailInfo();
					break;
				case "5":
					glc.gradeListController();
					break;
				
				case "9":
					System.out.println("시스템 종료.");
					run = false;
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
					break;
				}// 스위치
			}
			
		} // 반복문

	}// 메소드

	public static void main(String[] args) throws Exception {
		MainController m = new MainController();
		m.printMenu();

	}// 메인
}// 클래스