package SeoulAptPrj;
//1. 2011~2014�� 4�� csv������ ���� ���� �� 1�� ���Ϸ� ����
/*
**�˰���**
1) �� ���͸��ȿ��� ��ü ���� ��� �ݺ��۾�
2) split =>  ����,�ٶ�,"35,000",���ٻ� => �̷��� �����͸� ���ҽ� 4���� �ƴ� 3�� ���� �����ϵ��� ���Խ� ���
3) ������ �Ǵ� "35,000" ó��
	(1) csv ���Ϸ� ������ ���̶� �޸� ���� : "35,000" => "35000"
	(2) ū����ǥ ���� : "35000" => 35000 
4) ������ ������ ���� 4���� 1�� ���Ϸ� �����ؼ� ����
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class S1_2011to2014normalization_Merge {
	public void S1_2011to2014normalization_Merge() throws IOException {
		System.out.println("Converting csv files to txt files and consolidating them into one file...");
		
		// ó���� �ð� ȹ��
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));
				
		// ���͸� ���� �ҷ�����
		File dir = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\aptData2011to2014");

		// ��� ���ϵ��� ����Ʈ�� String Array�� ����
		String[] fileNames = dir.list(); // ������ �ִ� ���ϸ���� fileNames ���ڿ� �迭�� ��´�.
		
		File saveFile = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\tmp\\2011to2014.csv");	// �ش� ���Ϸ� ������ ��.

		// BufferedWriter�� ���ο� ���� �����Ͽ� �� �ȿ� ���� �ۼ����ֱ�
		BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));

		String readtxt; // ���ڿ� readtxt ���� <= BufferedReader br�� �ش����� �� �پ� ���� ������ ���� ���ڿ�
		String [] fieldTmp;
		
		int line = 0;		//���ϸ��� ���� �� ī��Ʈ
		int cnt = 0;	//�� ������ ���� => ���� Ƚ��
		int wcnt = 0;	// ��ȿ�� ������ ���� => �� Ƚ��
		int fileNum = 0;	//br.close�� ���� ī��Ʈ
		

		for (String fileName : fileNames) {
			System.out.println("Reading from " + fileName); // �ܼ�â�� ���� �̸� ���

			File f = new File(dir, fileName); // public File(File parent, String child) => File f ���� ��� ��ȭ

			// BufferedReader ��ü ����
			BufferedReader br = new BufferedReader(new FileReader(f));
			// �����Ǵ� ���� ���� ������ ���� ���� �̸� ǥ�� (���� ���Ͽ� �� ǥ�� ����/ �ּ�ó��)

			

		// readtxt �� �� �о��µ� null�� �ƴϸ� ��� �ݺ� => ������ ����
		while ((readtxt = br.readLine()) != null) { //br�� ���� �� �о� readtxt�� ���� 
			StringBuffer s = new StringBuffer();		//StringBuffer ��ü���� => while �ȿ��� �ϱ�!
			String[] field = readtxt.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);	//�� �� �� �а� �ʵ� �� ���� => "35,000" �� ó�� ���� ���Խ� ��� => �� �� �ɰ��� �ʰ� 1���� ��µ�.
			//while ((readtxt = br.readLine()) != null)  ���� �� �� �����ϱ� 2��° �� ���� ������ �д´�.
			
			if (line > 15) {
				if (field[9].contains(",")) { // "35,000"
					field[9] = field[9].replaceAll(",","");   // ,���� => "35000"
					if (field[9].contains("\"")) {	//"35000"
						field[9] = field[9].replaceAll("\"", "");   // "���� => 35000
					}
				}
				fieldTmp = field;
				
				for(String fulltext:fieldTmp) {
					s.append(fulltext + ",");// �� ������ fulltext�� �߰�(append)
				}
				bw.write(s.toString());
				bw.newLine();	//�ٹٲ�
				wcnt++; // ��ȿ�� ������ ��� �б�
				}
			line++;	// ������ ��� �б�
			cnt++;
//			System.out.println(".");	//�ݺ��� ����Ȯ��
		} //while
		line = 0;
		fileNum++;		//���� ���� ���� �߰�
		
		if(fileNum > fileNames.length) {
			br.close();	// ���۸��� close
		}
		} //for
		
		bw.flush();
		bw.close();// ���۶����� close
		
		// ���α׷� ����ȳ����� ���
		System.out.println("Reading from all files" + " in directory " + dir.getName() + " Completed");
		System.out.println("File storage path =>  C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\tmp\\2011to2014.csv");
		System.out.printf("Programme End => read [%d], write [%d]\n", cnt, wcnt);
				
		// ó���� �ð� ȹ��
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));
		
		// �ð����� ���_�����Է±��� ������ �ɸ��ð�
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");	
	}
}