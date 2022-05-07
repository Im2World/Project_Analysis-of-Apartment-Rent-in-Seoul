package SeoulAptPrj;
//2. 2015~2021년 7개 csv파일을 형식 가공 후 1개 파일로 통합
/*
**알고리즘**
1) 한 디렉터리안에서 전체 파일 대상 반복작업
2) split =>  "201209","3","31,000","0" => 이러한 데이터를 분할시 5개가 아닌 4개 열로 분할하도록 정규식 사용
3) 문제가 되는 "31,000" 처리
	(1) csv 파일로 저장할 것이라서 콤마 제거 : "35,000" => "35000"
	(2) 큰따옴표 제거 : "35000" => 35000 
4) 가공한 데이터 파일 4개를 1개 파일로 통합해서 저장
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class S2_2015to2021normalization_Merge {

	public void S2_2015to2021normalization_Merge() throws IOException {
		System.out.println("Converting csv files to txt files and consolidating them into one file...");
		
		// 처리전 시간 획득
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));
				
		// 디렉터리 파일 불러오기
		File dir = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\aptData2015to2021");

		// 모든 파일들을 리스트로 String Array에 담음
		String[] fileNames = dir.list(); // 폴더에 있는 파일목록을 fileNames 문자열 배열에 담는다.
		
		File saveFile = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\tmp\\2015to2021.csv");	// 해당 파일로 저장할 것.

		// BufferedWriter로 새로운 파일 생성하여 그 안에 내용 작성해주기
		BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));

		String readtxt; // 문자열 readtxt 정의 <= BufferedReader br로 해당파일 한 줄씩 읽은 내용을 담을 문자열
		String [] fieldTmp;
		
		int line = 0;		//파일마다 라인 수 카운트
		int cnt = 0;	//총 데이터 갯수 => 읽은 횟수
		int wcnt = 0;	// 유효한 데이터 갯수 => 쓴 횟수
		int fileNum = 0;	//br.close를 위한 카운트
		

		for (String fileName : fileNames) {
			System.out.println("Reading from " + fileName); // 콘솔창에 파일 이름 출력

			File f = new File(dir, fileName); // public File(File parent, String child) => File f 값이 계속 변화

			// BufferedReader 객체 생성
			BufferedReader br = new BufferedReader(new FileReader(f));
			// 생성되는 파일 내에 구분을 위해 파일 이름 표시 (실제 파일엔 이 표시 없음/ 주석처리)

		while ((readtxt = br.readLine()) != null) { //br을 한줄 씩 읽어 readtxt에 저장 
			StringBuffer s = new StringBuffer();		//StringBuffer 객체생성 => while 안에서 하기!
			String[] field = readtxt.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);	//한 줄 씩 읽고 필드 열 생성 => "35,000" 열 처리 위해 정규식 사용 => 이 열 쪼개지 않고 1개로 출력됨.
			
			//while ((readtxt = br.readLine()) != null)  에서 한 줄 읽으니까 2번째 줄 부터 파일을 읽는다.
			
			if (line > 15) {
				for(int i = 0; i < field.length - 1; i++) {
					if (field[i].contains(",")) { //  , 가 있으면
						field[i] = field[i].replaceAll(",","");   // , 제거		   ex) "3","31,000" = "3", "31000"
					}
					if (field[i].contains("\"")) {		//	"가 있으면   ex) "3","31000" => 3, 31000 
						field[i] = field[i].replaceAll("\"", ""); 	// " 제거
					}
				} //for

				fieldTmp = field;
				
				for(String fulltext:fieldTmp) {
					s.append(fulltext + ",");// 그 내용을 fulltext에 추가(append)
				}
				bw.write(s.toString());
				bw.newLine();	//줄바꿈
				wcnt++; // 유효한 데이터 계속 읽기
				} //line > 15
			
			line++;	// 데이터 계속 읽기
			cnt++;
//			System.out.println(".");	//반복문 동작확인
		} //while
		line = 0;
		fileNum++;		//읽은 파일 개수 추가
		
		if(fileNum > fileNames.length) {
			br.close();	// 버퍼리더 close
		}
		} //for
		
		bw.flush();
		bw.close();// 버퍼라이터 close
		
		// 프로그램 종료안내문구 출력
		System.out.println("Reading from all files" + " in directory " + dir.getName() + " Completed");
		System.out.println("File storage path =>  C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\tmp\\2015to2021.csv");
		System.out.printf("Programme End => read [%d], write [%d]\n", cnt, wcnt);
				
		// 처리후 시간 획득
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));
		
		// 시간차이 출력_숫자입력까지 실제로 걸린시간
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");	
	}
}
