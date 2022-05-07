package SeoulAptPrj;

//���� �� ���� ������ (csv) ������ �� ���� ��¥ ���Ϸ� ��ġ�� �ҽ��ڵ�
/*
**�˰���**
1) �� ���͸��ȿ��� ��ü ���� ��� �ݺ��۾�
2) PrintWriter�� �� �پ� ����
3) �ܼ� ���� ��ġ��� ���� ������ �� ����.
4) ������ ���� 2���� 1�� ���Ϸ� �����ؼ� ����
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

		// ó���� �ð� ȹ��
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));

		// �ش� ��ο� 2011~2014, 2015~2021.csv ���Ϲ̸� �����ص�.
		File dir = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\tmp"); // 2011~2014 + 2015~2021

		// ��ģ ���� �� ���� ����
		PrintWriter pw = new PrintWriter("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\2011to2021.csv");

		// ��� ���ϵ��� ����Ʈ�� String Array�� ����
		String[] fileNames = dir.list(); // ������ �ִ� ���ϸ���� fileNames ���ڿ� �迭�� ��´�.

		// ����� Ȯ�ο� �ܼ�â ���
		int line = 0; // ���ϸ��� ���� �� ī��Ʈ
		int cnt = 0; // �� ������ ���� => ���� Ƚ��
		int wcnt = 0; // ��ȿ�� ������ ���� => �� Ƚ��
		int fileNum = 0; // br.close�� ���� ī��Ʈ

		for (String fileName : fileNames) {
			System.out.println("Reading from " + fileName); // �ܼ�â�� ���� �̸� ���

			File f = new File(dir, fileName); // public File(File parent, String child) => File f ���� ��� ��ȭ

			// BufferedReader ��ü ����
			BufferedReader br = new BufferedReader(new FileReader(f));
			// �����Ǵ� ���� ���� ������ ���� ���� �̸� ǥ�� (���� ���Ͽ� �� ǥ�� ����/ �ּ�ó��)
			// pw.println("Contents of file " + fileName);

			String readtxt;
			while ((readtxt = br.readLine()) != null) {
				pw.println(readtxt);
				wcnt++; // ��ȿ�� ������ ��� �б�
				line++; // ���� ������ cnt�� 0���� �ʱ�ȭ�Ǿ���.
				cnt++;
			}
			line = 0;
			fileNum++; // ���� ���� ���� �߰�

			if (fileNum > fileNames.length) {
				br.close(); // ���۸��� close
			}
		} // for
		pw.flush(); // flush�� �о��ְ�
		pw.close(); // ����

		// �۾� �Ϸ�� �ܼ�â�� ǥ��
		System.out.println("Reading from all files" + " in directory " + dir.getName() + " Completed");
		System.out.println("File storage path => C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\2011to2021.csv");
		System.out.printf("Program End => read [%d], write [%d]\n", cnt, wcnt); // cnt�� �ش� ���� �Ѷ��μ�

		// ó���� �ð� ȹ��
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));

		// �ð����� ���_�����Է±��� ������ �ɸ��ð�
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");
	}
} // class
