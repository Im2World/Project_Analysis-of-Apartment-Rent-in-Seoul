package SeoulAptPrj;

//지역구 분리

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class S4_SeperateGu {
	public void S4_SeperateGu() throws IOException {
		System.out.println("Separate districts...");
		
		// 처리전 시간 획득
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));

		File file = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\2011to2021.csv");

		// 합친 내용 들어갈 파일 생성
		PrintWriter pw = new PrintWriter("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\seperateGu.csv");

		// 사용자 확인용 콘솔창 출력s
		System.out.println("Reading from " + file); // 콘솔창에 파일 이름 출력

		// BufferedReader 객체 생성
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
//	         String line = br.readLine();	//br을 한 줄씩 읽는다.
		int cnt = 0; // 라인을 센다.

		// 콤마제거
		while ((line = br.readLine()) != null) {
			// 행정구역명 분리를 위해 "서울특별시 "제거, 뒷글자"구 "제거 후 "구,"으로 1열을 추가생성
			if (line.contains("서울특별시 ")) {
				line = line.replaceAll("서울특별시 ", ""); // "서울특별시 "를 공백으로.
			}

			if (line.contains("구 ")) {
				line = line.replaceAll("구 ", "@,"); // "구 "를 콤마로 => "구" 열이 생긴다. =>구로구 잘려서 "로" 출력되는 것 방지위해 "구 "로 자름
			}

			if (line.contains("@,")) {
				line = line.replaceAll("@,", "구,"); // "중구"가 "중"으로 나오는 것 방지 => 구마다 다시 끝에 "구" 붙여줌
			}
			cnt++;
			pw.println(line);
		}
		br.close();
		pw.flush(); // flush로 털어주고
		pw.close(); // 닫음
		// 작업 완료시 콘솔창에 표시
		System.out.println("Reading from all files" + " in directory " + file + " Completed");
		System.out.println("File storage path => C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\seperateGu.csv");
		System.out.printf("Program End => read [%d]\n", cnt); // cnt는 해당 파일 총라인수

		// 처리후 시간 획득
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));

		// 시간차이 출력_숫자입력까지 실제로 걸린시간
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");
	}
} // class
