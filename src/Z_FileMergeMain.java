package SeoulAptPrj;
//이 소스를 실행하면, 알아서 처리된다.

import java.io.IOException;

public class Z_FileMergeMain {

	public static void main(String[] args) throws IOException {
		S1_2011to2014normalization_Merge S1 = new S1_2011to2014normalization_Merge();
		S2_2015to2021normalization_Merge S2 = new S2_2015to2021normalization_Merge();
		S3_2011to2021_Merge S3 = new S3_2011to2021_Merge();		//2011to2014 ~ 2015to2021 파일합치기
		S4_SeperateGu S4 = new S4_SeperateGu();	//구 분리
		S5_typeDistinguish S5 = new S5_typeDistinguish();	//전세 데이터만 추출
		S6_calData S6 = new S6_calData();	//필요한 데이터 계산
				
		S1.S1_2011to2014normalization_Merge();
		S2.S2_2015to2021normalization_Merge();
		S3.S3_2011to2021_Merge();
		S4.S4_SeperateGu();
		S5.S5_typeDistinguish();
		S6.S6_calData();
	}
}


/*
<처리결과 예시>

Converting csv files to txt files and consolidating them into one file...
start time : 2022-05-07 18:22:07.525
Reading from 2011.csv
Reading from 2012.csv
Reading from 2013.csv
Reading from 2014.csv
Reading from all files in directory aptData2011to2014 Completed
File storage path =>  C:\Users\IM\Desktop\javaBasicPrj\aptDoroPrj\tmp\2011to2014.csv
Programme End => read [620087], write [620023]
end time : 2022-05-07 18:22:19.421
the time required : 11.896 seconds

Converting csv files to txt files and consolidating them into one file...
start time : 2022-05-07 18:22:19.424
Reading from 2016.csv
Reading from 2017.csv
Reading from 2018.csv
Reading from 2019.csv
Reading from 2020.csv
Reading from 2021.csv
Reading from all files in directory aptData2015to2021 Completed
File storage path =>  C:\Users\IM\Desktop\javaBasicPrj\aptDoroPrj\tmp\2015to2021.csv
Programme End => read [1056481], write [1056385]
end time : 2022-05-07 18:23:32.548
the time required : 73.124 seconds

Merging Files 2011 to 2021...
start time : 2022-05-07 18:23:32.548
Reading from 2011to2014.csv
Reading from 2015to2021.csv
Reading from all files in directory tmp Completed
File storage path => C:\Users\IM\Desktop\javaBasicPrj\aptDoroPrj\output\2011to2021.csv
Program End => read [1676408], write [1676408]
end time : 2022-05-07 18:23:34.719
the time required : 2.171 seconds

Separate districts...
start time : 2022-05-07 18:23:34.720
Reading from C:\Users\IM\Desktop\javaBasicPrj\aptDoroPrj\output\2011to2021.csv
Reading from all files in directory C:\Users\IM\Desktop\javaBasicPrj\aptDoroPrj\output\2011to2021.csv Completed
File storage path => C:\Users\IM\Desktop\javaBasicPrj\aptDoroPrj\output\seperateGu.csv
Program End => read [1676408]
end time : 2022-05-07 18:23:43.856
the time required : 9.136 seconds

Extracting Jeonse Only => write is Jeonse
start time : 2022-05-07 18:23:43.857
Programme End => read [1676408], write [1197681]
File storage path => C:\Users\IM\Desktop\javaBasicPrj\aptDoroPrj\output\two-yearLease2011to2021.csv
end time : 2022-05-07 18:23:54.716
the time required : 10.859 seconds

Generating data statistics...
start time : 2022-05-07 18:23:54.718

File storage path => C:\Users\IM\Desktop\javaBasicPrj\aptDoroPrj\output\HousePriceExtractionData2011to2021.csv
Programme End => read [1197681]  writeLines [286]
end time : 2022-05-07 18:24:04.070
the time required : 9.352 seconds
*/