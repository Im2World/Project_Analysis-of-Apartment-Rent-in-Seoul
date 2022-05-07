package SeoulAptPrj;
//������ ����

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
		
		// ó���� �ð� ȹ��
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));
				
		// ���� ��ü ���� �� �ش� ����� ���� �ҷ�����
		File f = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\seperateGu.csv");

		// br ���� BufferedReader ��ü ������ �б�
		BufferedReader br = new BufferedReader(new FileReader(f));

		File f2 = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\two-yearLease2011to2021.csv");	// �ش� ���Ϸ� ������ ��.

		// BufferedWriter�� ���ο� ���� �����Ͽ� �� �ȿ� ���� �ۼ����ֱ�
		BufferedWriter bw = new BufferedWriter(new FileWriter(f2));

		String readtxt; // ���ڿ� readtxt ���� <= BufferedReader br�� �ش����� �� �پ� ���� ������ ���� ���ڿ�

		int cnt = 0;	// �� ������ ���� => ���� Ƚ��
		int wcnt = 0;	// ��ȿ�� ������ ���� => �� Ƚ��

		
		// readtxt �� �� �о��µ� null�� �ƴϸ� ��� �ݺ� => ������ ����
		while ((readtxt = br.readLine()) != null) { //br�� ���� �� �о� readtxt�� ���� 
			StringBuffer s = new StringBuffer();		//StringBuffer ��ü���� => while �ȿ��� �ϱ�!
			String[] field = readtxt.split(","); //�迭�� �ִ� ������ �а�, �޸��� �����ڷ� ���� �ɰ� field�迭�� ����
			if (field[6].contains("����")) {// ������	///////////////TEST������ 5
				s.append(readtxt);// �� ������ readtxt�� �߰�(append)

				//toString() �޼ҵ� ȣ���ؼ� String��ü�� ��ȯ => k38_s ���� ���ڿ�! => k38_bw1.write ���Ͼ���!
				bw.write(s.toString());
				bw.newLine();	//�ٹٲ�
				wcnt++; // ��ȿ�� ������ ��� �б�
			}
			cnt++;// ������ ��� �б�
//			System.out.println(".");	//�ݺ��� ����Ȯ��
		}

		//�ڿ�����
		br.close();	// ���۸��� close
		bw.flush();
		bw.close();// ���۶����� close
		
		// ���α׷� ����ȳ����� ���
		System.out.printf("Programme End => read [%d], write [%d]\n", cnt, wcnt);
		System.out.println("File storage path => C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\two-yearLease2011to2021.csv");
				
		// ó���� �ð� ȹ��
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));
		
		// �ð����� ���_�����Է±��� ������ �ɸ��ð�
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");	
	}
}
