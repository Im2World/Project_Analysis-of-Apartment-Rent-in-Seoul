package SeoulAptPrj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
//csv파일 field 는 전부 String

public class S6_calData {

	public void S6_calData() throws IOException {
				
		//배열선언
		String [] areaNameArr = {"서울시","강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"};
		int [] yearsArr = {2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021};

		int [][] areaCntArr = new int[yearsArr.length][areaNameArr.length];	//해당연도 해당 구 거래 수
		double [][] tradingSizeArr = new double [yearsArr.length][areaNameArr.length];	//해당연도 해당 구 거래면적합	String field[7]		
		double [][] priceSumArr = new double [yearsArr.length][areaNameArr.length];	//해당연도 해당 구 거래액합		String field[10]


		// 출력확인
		System.out.println("Generating data statistics...");
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));
	
		// 파일 객체 생성 후 해당 경로의 파일 불러오기
		File f = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\two-yearLease2011to2021.csv");

		// br 파일 BufferedReader 객체 생성해 읽기
		BufferedReader br = new BufferedReader(new FileReader(f));

		File f2 = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\HousePriceExtractionData2011to2021.csv");	// 해당 파일로 저장할 것.

		// BufferedWriter로 새로운 파일 생성하여 그 안에 내용 작성해주기
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(f2));

		String line; // 문자열 readtxt 정의 <= BufferedReader br로 해당파일 한 줄씩 읽은 내용을 담을 문자열
		String year = new String();
		String areaName = new String();
		String areaCnt = new String();
		double priceSum = 0;
		double priceSumAgv = 0;
		double pricePerM = 0;		
		double pricePerP = 0;
		
		
		int cnt = 0;	// 총 데이터 개수
		int lineCnt  = 0;		//최종파일 라인 수
		
		StringBuffer s = new StringBuffer();
		
		//작업시작 _ 한 줄읽고 계산
		while ((line = br.readLine()) != null) { 
			String[] field = line.split(",");
			//연도 인덱스
			int yearIndex = Integer.parseInt(field[8].substring(0,4)) - 2011;			//필드가 2011년이면 int year_index는 0
			
				for (int j = 1; j < areaNameArr.length; j++) {
						//연도, 지역구 같으면
					//1줄 읽었는데, 강남구 2011 => 
					if ((Integer.parseInt(field[8].substring(0, 4)) == yearsArr[yearIndex]) && field[0].equals(areaNameArr[j])) { // 연도필드 값이 인덱스와 같고,
						//서울시 (전체총합)
						areaCntArr[yearIndex][0]++;
						priceSumArr[yearIndex][0] += Integer.parseInt(field[10]) ;
						tradingSizeArr[yearIndex][0] += Double.parseDouble(field[7]);		//면적합
						
						//지역구
						areaCntArr[yearIndex][j]++; // 해당연도 해당구 거래수 1 카운트 시킴
						priceSumArr[yearIndex][j] += Integer.parseInt(field[10]) ;
						tradingSizeArr[yearIndex][j] += Double.parseDouble(field[7]);		//면적합
					}	//if
				}	//for
				cnt++;
		}	//while

		s.append("연도" + "," + "지역구" + "," + "총 거래수(건)" + "," + "총 거래액(만원)" + "," + "평균가(만원)" + "," + "제곱미터 당 단가(㎡)(만원)" + "," +"평단가(만원)" + "\n");
		
		//서울시 값 계산 후 파일쓰기
		for (int i = 0; i < yearsArr.length; i++) {
			for (int j = 0; j < areaNameArr.length; j++) {
				//지역구별
				year =Integer.toString(yearsArr[i]);
				areaName = areaNameArr[j];
				areaCnt	= Integer.toString(areaCntArr[i][j]);
				priceSum = priceSumArr[i][j];
				priceSumAgv = priceSumArr[i][j] / (double)areaCntArr[i][j];	//해당연도 평균가
				pricePerM =  (priceSumArr[i][j] / tradingSizeArr[i][j]);	// 제곱미터당 단가
				pricePerP = (priceSumArr[i][j] / tradingSizeArr[i][j]) * 3.3;	//평단가
				
				s.append(year + "," + areaName + "," + areaCnt + "," + String.format("%.2f", priceSum) + "," + String.format("%.2f", priceSumAgv) + "," + String.format("%.2f", pricePerM) + "," + String.format("%.2f", pricePerP) + "\n");
				lineCnt++;
			}
		}

		bw1.write(s.toString());
		bw1.flush();
		bw1.close();
		br.close();
		
		System.out.println();
		
		//////////////////////////////////
		
		// 프로그램 종료안내문구 출력
		System.out.println("File storage path => C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\HousePriceExtractionData2011to2021.csv");
		System.out.printf("Programme End => read [%d]  writeLines [%d]\n", cnt, lineCnt);
				
		// 처리후 시간 획득
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));
		
		// 시간차이 출력_숫자입력까지 실제로 걸린시간
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");	
	}	//main
}	//class