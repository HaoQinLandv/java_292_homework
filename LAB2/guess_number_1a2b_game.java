import java.util.Scanner;
public class guess_number_1a2b_game {
	public static void main(String[] args) {
		
		// �ͦ��|�Ӥ��ۦP���Ʀr�r��A��@�ؼмƦr
		String ans_str = "" + ((int) (Math.random()*10));
		boolean generate_done = false;
		while(generate_done == false) {
			char temp_char = (char)(((int) (Math.random()*10) ) + '0');
			int ans_str_len = ans_str.length();
			for(int i = 0; i < ans_str_len; i++) {
				if(temp_char == ans_str.charAt(i)) {
					break;
				} else if( i == (ans_str_len - 1)){
					ans_str = ans_str + temp_char;
				}
				if(ans_str.length() == 4)
					generate_done = true;
			}		
		}
		// �ͦ��|�Ӥ��ۦP���Ʀr����
		Scanner input = new Scanner(System.in);
		System.out.println("�O�_����ܵ���(����BUG��) (Y/N)");
		char is_dip = (char) input.next().charAt(0);
		if ((is_dip == 'Y')||(is_dip == 'y')) {
			System.out.println("���׬�" + ans_str);
		}
		
		
		//����եبϥΪ̶ÿ�J    �}�l
		boolean guess_done = false;
		while (guess_done == false) {
			String input_Str = "";
			boolean input_correct = false;;
			while (input_correct == false) {
				System.out.println("�п�J�|��Ʀr(�Ʀr������)");
				input_Str = input.next();
				//�T�{�O�Ʀr��ƥ��T
				if(input_Str.length() != 4) {
					System.out.println("���׿��~!!");
					input_correct = false;
				} else {
					input_correct = true;
				}
				if (input_correct) {
					//�T�{�O�_�O�Ʀr
					for (int i = 0; i<4;i++) {
						if((input_Str.charAt(i) < '0')||('9'<input_Str.charAt(i)))
							input_correct = false;
					}
					if(input_correct == false) {
						System.out.println("�п�J�Ʀr!!");
					}
				}
				if (input_correct) {
					//�T�{�O�_�����ƼƦr�A�Y�����ƽШϥΪ̭��s��J
					for (int i = 0; i < 4; i++) {
						for (int j = i+1; j < 4; j++) {
							if (input_Str.charAt(i) == input_Str.charAt(j)) {
								input_correct = false;
							}
						}
					}
					if(input_correct == false) {
						System.out.println("��J�Ʀr������!!");
					}
				}
			}
			//����եبϥΪ̶ÿ�J    ����
			
			// �P�_ ?A ?B A����m��Ʀr�]���T�AB����m���Ʀr���T
			int num_A = 0, num_B = 0; // �N A,B��l�Ƭ�0
			
			//���O��ϥΪ̿�J�r�ꪺ�|�ӼƦr�A�i��A�BB�P�O]
			for (int i = 0; i < 4; i++) {
				// ���j�M�ϥΪ̿�J��i�ӼƦr�O�_��A(��m���T�Ʀr���T)
				if (input_Str.charAt(i) == ans_str.charAt(i)) {
					num_A = num_A + 1;
				} else {
					// ���۷j�M�ϥΪ̿�J��i�ӼƦr�O�_��B
					// �C�|���ץ|�ӼƦr�A�T�{�O�_���ۦP
					for (int j = 0; j < 4; j++) {
						if (input_Str.charAt(i) == ans_str.charAt(j)) {
							num_B = num_B + 1;
						}
					}
				}
				
			}
			
			if (num_A == 4) {
				System.out.println("�n�δ�!! �A����F!!");
				guess_done = true;
			} else {
				System.out.printf("%d A %d B ���~���J \n", num_A, num_B);
			}
			
		}
		
		input.close(); //���� Scanner ���� 
	}

}
