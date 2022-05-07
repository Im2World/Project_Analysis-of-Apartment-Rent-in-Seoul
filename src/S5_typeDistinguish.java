package SeoulAptPrj;
//전세만 추출

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class S5_typeDistinguish {

	public void S5_typeDistinguish() throws IOException {
		System.out.println("Extracting Jeonse Only => write is Jeonse");
		
		// 처리전 시간 획득
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));
				
		// 파일 객체 생성 후 해당 경로의 파일 불러오기
		File f = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\seperateGu.csv");

		// br 파일 BufferedReader 객체 생성해 읽기
		BufferedReader br = new BufferedReader(new FileReader(f));

		File f2 = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\two-yearLease2011to2021.csv");	// 해당 파일로 저장할 것.

		// BufferedWriter로 새로운 파일 생성하여 그 안에 내용 작성해주기
		BufferedWriter bw = new BufferedWriter(new FileWriter(f2));

		String readtxt; // 문자열 readtxt 정의 <= BufferedReader br로 해당파일 한 줄씩 읽은 내용을 담을 문자열

		int cnt = 0;	// 총 데이터 갯수 => 읽은 횟수
		int wcnt = 0;	// 유효한 데이터 갯수 => 쓴 횟수

		
		// readtxt 한 줄 읽었는데 null이 아니면 계속 반복 => 끝까지 읽음
		while ((readtxt = br.readLine()) != null) { //br을 한줄 씩 읽어 readtxt에 저장 
			StringBuffer s = new StringBuffer();		//StringBuffer 객체생성 => while 안에서 하기!
			String[] field = readtxt.split(","); //배열에 있는 내용을 읽고, 콤마를 구분자로 내용 쪼개 field배열에 저장
			if (field[6].contains("전세")) {// 전세면	///////////////TEST에서는 5
				s.append(readtxt);// 그 내용을 readtxt에 추가(append)

				//toString() 메소드 호출해서 String객체로 전환 => k38_s 이제 문자열! => k38_bw1.write 파일쓰기!
				bw.write(s.toString());
				bw.newLine();	//줄바꿈
				wcnt++; // 유효한 데이터 계속 읽기
			}
			cnt++;// 데이터 계속 읽기
//			System.out.println(".");	//반복문 동작확인
		}

		//자원해제
		br.close();	// 버퍼리더 close
		bw.flush();
		bw.close();// 버퍼라이터 close
		
		// 프로그램 종료안내문구 출력
		System.out.printf("Programme End => read [%d], write [%d]\n", cnt, wcnt);
		System.out.println("File storage path => C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\two-yearLease2011to2021.csv");
				
		// 처리후 시간 획득
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));
		
		// 시간차이 출력_숫자입력까지 실제로 걸린시간
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");	
	}
}
