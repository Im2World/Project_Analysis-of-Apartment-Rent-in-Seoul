package SeoulAptPrj;

//폴더 내 같은 형태의 (csv) 파일을 한 개의 통짜 파일로 합치는 소스코드
/*
**알고리즘**
1) 한 디렉터리안에서 전체 파일 대상 반복작업
2) PrintWriter로 한 줄씩 읽음
3) 단순 파일 합치기라서 따로 가공할 것 없음.
4) 데이터 파일 2개를 1개 파일로 통합해서 저장
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class S3_2011to2021_Merge {
	public void S3_2011to2021_Merge() throws IOException {
		System.out.println("Merging Files 2011 to 2021...");

		// 처리전 시간 획득
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));

		// 해당 경로에 2011~2014, 2015~2021.csv 파일미리 저장해둠.
		File dir = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\tmp"); // 2011~2014 + 2015~2021

		// 합친 내용 들어갈 파일 생성
		PrintWriter pw = new PrintWriter("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\2011to2021.csv");

		// 모든 파일들을 리스트로 String Array에 담음
		String[] fileNames = dir.list(); // 폴더에 있는 파일목록을 fileNames 문자열 배열에 담는다.

		// 사용자 확인용 콘솔창 출력
		int line = 0; // 파일마다 라인 수 카운트
		int cnt = 0; // 총 데이터 갯수 => 읽은 횟수
		int wcnt = 0; // 유효한 데이터 갯수 => 쓴 횟수
		int fileNum = 0; // br.close를 위한 카운트

		for (String fileName : fileNames) {
			System.out.println("Reading from " + fileName); // 콘솔창에 파일 이름 출력

			File f = new File(dir, fileName); // public File(File parent, String child) => File f 값이 계속 변화

			// BufferedReader 객체 생성
			BufferedReader br = new BufferedReader(new FileReader(f));
			// 생성되는 파일 내에 구분을 위해 파일 이름 표시 (실제 파일엔 이 표시 없음/ 주석처리)
			// pw.println("Contents of file " + fileName);

			String readtxt;
			while ((readtxt = br.readLine()) != null) {
				pw.println(readtxt);
				wcnt++; // 유효한 데이터 계속 읽기
				line++; // 파일 끝나면 cnt가 0으로 초기화되야함.
				cnt++;
			}
			line = 0;
			fileNum++; // 읽은 파일 개수 추가

			if (fileNum > fileNames.length) {
				br.close(); // 버퍼리더 close
			}
		} // for
		pw.flush(); // flush로 털어주고
		pw.close(); // 닫음

		// 작업 완료시 콘솔창에 표시
		System.out.println("Reading from all files" + " in directory " + dir.getName() + " Completed");
		System.out.println("File storage path => C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\2011to2021.csv");
		System.out.printf("Program End => read [%d], write [%d]\n", cnt, wcnt); // cnt는 해당 파일 총라인수

		// 처리후 시간 획득
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));

		// 시간차이 출력_숫자입력까지 실제로 걸린시간
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");
	}
} // class
