package SeoulAptPrj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
//csv���� field �� ���� String

public class S6_calData {

	public void S6_calData() throws IOException {
				
		//�迭����
		String [] areaNameArr = {"�����","������","������","���ϱ�","������","���Ǳ�","������","���α�","��õ��","�����","������","���빮��","���۱�","������","���빮��","���ʱ�","������","���ϱ�","���ı�","��õ��","��������","��걸","����","���α�","�߱�","�߶���"};
		int [] yearsArr = {2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021};

		int [][] areaCntArr = new int[yearsArr.length][areaNameArr.length];	//�ش翬�� �ش� �� �ŷ� ��
		double [][] tradingSizeArr = new double [yearsArr.length][areaNameArr.length];	//�ش翬�� �ش� �� �ŷ�������	String field[7]		
		double [][] priceSumArr = new double [yearsArr.length][areaNameArr.length];	//�ش翬�� �ش� �� �ŷ�����		String field[10]


		// ���Ȯ��
		System.out.println("Generating data statistics...");
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));
	
		// ���� ��ü ���� �� �ش� ����� ���� �ҷ�����
		File f = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\two-yearLease2011to2021.csv");

		// br ���� BufferedReader ��ü ������ �б�
		BufferedReader br = new BufferedReader(new FileReader(f));

		File f2 = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\HousePriceExtractionData2011to2021.csv");	// �ش� ���Ϸ� ������ ��.

		// BufferedWriter�� ���ο� ���� �����Ͽ� �� �ȿ� ���� �ۼ����ֱ�
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(f2));

		String line; // ���ڿ� readtxt ���� <= BufferedReader br�� �ش����� �� �پ� ���� ������ ���� ���ڿ�
		String year = new String();
		String areaName = new String();
		String areaCnt = new String();
		double priceSum = 0;
		double priceSumAgv = 0;
		double pricePerM = 0;		
		double pricePerP = 0;
		
		
		int cnt = 0;	// �� ������ ����
		int lineCnt  = 0;		//�������� ���� ��
		
		StringBuffer s = new StringBuffer();
		
		//�۾����� _ �� ���а� ���
		while ((line = br.readLine()) != null) { 
			String[] field = line.split(",");
			//���� �ε���
			int yearIndex = Integer.parseInt(field[8].substring(0,4)) - 2011;			//�ʵ尡 2011���̸� int year_index�� 0
			
				for (int j = 1; j < areaNameArr.length; j++) {
						//����, ������ ������
					//1�� �о��µ�, ������ 2011 => 
					if ((Integer.parseInt(field[8].substring(0, 4)) == yearsArr[yearIndex]) && field[0].equals(areaNameArr[j])) { // �����ʵ� ���� �ε����� ����,
						//����� (��ü����)
						areaCntArr[yearIndex][0]++;
						priceSumArr[yearIndex][0] += Integer.parseInt(field[10]) ;
						tradingSizeArr[yearIndex][0] += Double.parseDouble(field[7]);		//������
						
						//������
						areaCntArr[yearIndex][j]++; // �ش翬�� �ش籸 �ŷ��� 1 ī��Ʈ ��Ŵ
						priceSumArr[yearIndex][j] += Integer.parseInt(field[10]) ;
						tradingSizeArr[yearIndex][j] += Double.parseDouble(field[7]);		//������
					}	//if
				}	//for
				cnt++;
		}	//while

		s.append("����" + "," + "������" + "," + "�� �ŷ���(��)" + "," + "�� �ŷ���(����)" + "," + "��հ�(����)" + "," + "�������� �� �ܰ�(��)(����)" + "," +"��ܰ�(����)" + "\n");
		
		//����� �� ��� �� ���Ͼ���
		for (int i = 0; i < yearsArr.length; i++) {
			for (int j = 0; j < areaNameArr.length; j++) {
				//��������
				year =Integer.toString(yearsArr[i]);
				areaName = areaNameArr[j];
				areaCnt	= Integer.toString(areaCntArr[i][j]);
				priceSum = priceSumArr[i][j];
				priceSumAgv = priceSumArr[i][j] / (double)areaCntArr[i][j];	//�ش翬�� ��հ�
				pricePerM =  (priceSumArr[i][j] / tradingSizeArr[i][j]);	// �������ʹ� �ܰ�
				pricePerP = (priceSumArr[i][j] / tradingSizeArr[i][j]) * 3.3;	//��ܰ�
				
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
		
		// ���α׷� ����ȳ����� ���
		System.out.println("File storage path => C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\HousePriceExtractionData2011to2021.csv");
		System.out.printf("Programme End => read [%d]  writeLines [%d]\n", cnt, lineCnt);
				
		// ó���� �ð� ȹ��
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));
		
		// �ð����� ���_�����Է±��� ������ �ɸ��ð�
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");	
	}	//main
}	//class