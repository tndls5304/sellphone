package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Main;
import util.JDBCTemplate;

public class MemberController {
	public static boolean run = true;
	public void printMenu() throws Exception {

		while (run) {
			if (Main.loginMember == null) {
				System.out.println("----- member -----");
				System.out.println("1. 회원가입");
				System.out.println("2. 로그인");
				System.out.println("3. 종료");
				System.out.println("원하는 메뉴 번호 입력");
				String num = Main.SC.nextLine();
				switch (num) {
				case "1":
					join();
					break;
				case "2":
					login();
					break;
				case "3":
					System.out.println("종료합니다.");
					run = false;
					break;
				default:
					System.out.println("잘못된 입력입니다.");
				}

			} else if (Main.loginMember != null) {
				System.out.println("------------------");
				System.out.println("1. 로그아웃");
				System.out.println("2. 회원탈퇴");
				System.out.println("3. 회원정보 조회");
				System.out.println("4. 회원정보 변경");
				System.out.println("------------------");
				String inputNum = Main.SC.nextLine();
				switch (inputNum) {
				case "1":
					logout();
					break;
				case "2":
					deleteM();
					break;
				case "3":
					mInfo();
					break;
				case "4":
					mUpdateInfo();
					break;
				default:
					System.out.println("잘못된 입력입니다.");
					break;
				}
			}
		}
	}

	private void join() throws Exception {
		Connection conn = JDBCTemplate.getConn();

		String sql = "INSERT INTO MEMBER(NO, MEMBER_ID, PWD, BANK_ACCOUNT_NUMBER) VALUES (SEQ_MEMBER_NO.NEXTVAL , ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		System.out.println("ID : ");
		String inputId = Main.SC.nextLine();
		System.out.println("Pwd : ");
		String inputPwd = Main.SC.nextLine();
		System.out.println("BANK_ACCOUNT_NUMBER : ");
		String inputBAN = Main.SC.nextLine();

		pstmt.setString(1, inputId);
		pstmt.setString(2, inputPwd);
		pstmt.setString(3, inputBAN);

		int result = pstmt.executeUpdate();
		if (result != 1) {
			System.out.println("실패");
			return;
		}
		System.out.println("성공");
	}

	private void login() throws Exception {
		Connection conn = JDBCTemplate.getConn();
		String sql = "SELECT * FROM MEMBER WHERE QUIT_YN = 'N' AND MEMBER_ID = ? AND PWD = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		System.out.println("ID : ");
		String inputId = Main.SC.nextLine();
		System.out.println("Pwd : ");
		String inputPwd = Main.SC.nextLine();

		pstmt.setString(1, inputId);
		pstmt.setString(2, inputPwd);

		ResultSet rs = pstmt.executeQuery();
		MemberVo vo = null;
		while (rs.next()) {
			String dbNo = rs.getString("NO");
			String dbId = rs.getString("MEMBER_ID");
			String dbPwd = rs.getString("PWD");
			String dbBAN = rs.getString("BANK_ACCOUNT_NUMBER");
			String quitYn = rs.getString("QUIT_YN");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			vo = new MemberVo(dbNo, dbId, dbPwd, dbBAN, enrollDate, modifyDate, quitYn);
		}
		if (vo == null) {
			System.out.println("실패!");
			return;
		}
		System.out.println("로그인 성공!");
		System.out.println(vo.getId() + "님 환영합니다. ");
		Main.loginMember = vo;
	}

	public void logout() {
		System.out.println("로그아웃을 진행합니다..");
		Main.loginMember = null;
	}

	public void deleteM() throws Exception {
		System.out.println("정말로 삭제하시겠습니까?");
		System.out.println("1. 예");
		System.out.println("2. 아니오");
		String inputstr = Main.SC.nextLine();
		if (inputstr.equals("1")) {
			System.out.println("삭제를 진행합니다.");
			System.out.println("비밀번호 입력 : ");
			String inputstr2 = Main.SC.nextLine();

			if (inputstr2.equals(Main.loginMember.getPwd())) {
				Connection conn = JDBCTemplate.getConn();
				String sql = "UPDATE MEMBER SET QUIT_YN = 'Y' WHERE MEMBER_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Main.loginMember.getId());

				int result = pstmt.executeUpdate();
				if (result != 1) {
					System.out.println("회원 탈퇴 실패");
					return;
				}
				System.out.println("회원 탈퇴 성공!");
				Main.loginMember = null;
			}
		} else if (inputstr.equals("2")) {
			System.out.println("취소되었습니다.");
			return;
		} else {
			System.out.println("잘못된 입력입니다.");
			return;
		}
	}

	public void mInfo() {
		System.out.println("회원 정보를 조회합니다.");
		System.out.print("회원번호 : ");
		System.out.println(Main.loginMember.getNo());
		System.out.print("아이디   : ");
		System.out.println(Main.loginMember.getId());
		System.out.print("계좌번호 : ");
		System.out.println(Main.loginMember.getBankAccountNumber());
		System.out.print("가입일 : ");
		System.out.println(Main.loginMember.getEnrollDate());
	}

	public void mUpdateInfo() throws Exception {
		System.out.println("비밀번호 입력 : ");
		String inputstr1 = Main.SC.nextLine();
		if (inputstr1.equals(Main.loginMember.getPwd())) {
			System.out.println("변경하실 값을 선택하세요");
			System.out.println("1. 비밀번호");
			System.out.println("2. 계좌번호");
			String inputstr2 = Main.SC.nextLine();
			if (inputstr2.equals("1")) {
				System.out.println("비밀번호 변경을 진행합니다.");
				System.out.println("변경하실 비밀번호를 입력해주세요.");
				System.out.print("변경할 비밀번호 : ");
				String inputPwd1 = Main.SC.nextLine();
				if(!inputPwd1.equals(Main.loginMember.getPwd())) {
					System.out.println("한번 더 입력해주세요.");
					String inputPwd2 = Main.SC.nextLine();
					if (inputPwd1.equals(inputPwd2)) {
						Connection conn = JDBCTemplate.getConn();
						String sql = "UPDATE MEMBER SET PWD = ? WHERE MEMBER_ID = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, inputPwd2);
						pstmt.setString(2, Main.loginMember.getId());
						int result = pstmt.executeUpdate();
						if (result != 1) {
							System.out.println("비밀번호 변경 실패..");
							return;
						}
						System.out.println("비밀번호 변경 성공!");
						System.out.println("다시 로그인 해주세요.");
						Main.loginMember = null;
					} else {
						System.out.println("비밀번호가 일치하지 않습니다.");
						return;
					}
				} else if(inputPwd1.equals(Main.loginMember.getPwd())) {
					System.out.println("기존의 비밀번호와 동일합니다.");
					System.out.println("변경하실 비밀번호를 입력해주세요.");
				} else {
					System.out.println("잘못된 입력입니다. 다시 진행해주세요.");
					return;
				}

			} else if (inputstr2.equals("2")) {
				System.out.println("계좌번호 변경을 진행합니다.");
				System.out.println("변경하실 계좌번호를 입력해주세요.");
				System.out.print("변경할 계좌번호 입력:");

				String inputBAN = Main.SC.nextLine();
				System.out.println("정말로 변경하시겠습니까?");
				System.out.println("1. 예");
				System.out.println("2. 아니요");
				String num = Main.SC.nextLine();
				if (num.equals("1")) {
					Connection conn = JDBCTemplate.getConn();

					String sql = "UPDATE MEMBER SET BANK_ACCOUNT_NUMBER = ? WHERE MEMBER_ID = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, inputBAN);
					pstmt.setString(2, Main.loginMember.getId());

					int result = pstmt.executeUpdate();
					if (result != 1) {
						System.out.println("계좌번호 변경 실패..");
						return;
					}
					System.out.println("계좌번호 변경 성공!");
					System.out.println("다시 로그인해주세요.");
					Main.loginMember = null;
				} else if (num.equals("2")) {
					System.out.println("변경을 취소하였습니다.");
					return;
				} else {
					System.out.println("잘못된 입력입니다. 다시 진행해주세요.");
					return;
				}

			}
		}

	}

}
