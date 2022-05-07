package SeoulAptPrj;

//������ �и�

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class S4_SeperateGu {
	public void S4_SeperateGu() throws IOException {
		System.out.println("Separate districts...");
		
		// ó���� �ð� ȹ��
		long start = System.currentTimeMillis();
		System.out.print("start time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(start));

		File file = new File("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\2011to2021.csv");

		// ��ģ ���� �� ���� ����
		PrintWriter pw = new PrintWriter("C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\seperateGu.csv");

		// ����� Ȯ�ο� �ܼ�â ���s
		System.out.println("Reading from " + file); // �ܼ�â�� ���� �̸� ���

		// BufferedReader ��ü ����
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
//	         String line = br.readLine();	//br�� �� �پ� �д´�.
		int cnt = 0; // ������ ����.

		// �޸�����
		while ((line = br.readLine()) != null) {
			// ���������� �и��� ���� "����Ư���� "����, �ޱ���"�� "���� �� "��,"���� 1���� �߰�����
			if (line.contains("����Ư���� ")) {
				line = line.replaceAll("����Ư���� ", ""); // "����Ư���� "�� ��������.
			}

			if (line.contains("�� ")) {
				line = line.replaceAll("�� ", "@,"); // "�� "�� �޸��� => "��" ���� �����. =>���α� �߷��� "��" ��µǴ� �� �������� "�� "�� �ڸ�
			}

			if (line.contains("@,")) {
				line = line.replaceAll("@,", "��,"); // "�߱�"�� "��"���� ������ �� ���� => ������ �ٽ� ���� "��" �ٿ���
			}
			cnt++;
			pw.println(line);
		}
		br.close();
		pw.flush(); // flush�� �о��ְ�
		pw.close(); // ����
		// �۾� �Ϸ�� �ܼ�â�� ǥ��
		System.out.println("Reading from all files" + " in directory " + file + " Completed");
		System.out.println("File storage path => C:\\Users\\IM\\Desktop\\javaBasicPrj\\aptDoroPrj\\output\\seperateGu.csv");
		System.out.printf("Program End => read [%d]\n", cnt); // cnt�� �ش� ���� �Ѷ��μ�

		// ó���� �ð� ȹ��
		long end = System.currentTimeMillis();
		System.out.print("end time : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(end));

		// �ð����� ���_�����Է±��� ������ �ɸ��ð�
		System.out.println("the time required : " + (end - start) / 1000.0 + " seconds\n");
	}
} // class
