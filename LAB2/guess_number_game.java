import java.util.Scanner;

public class guess_number_game {
	public static void main(String[] args) {
		int Target_number = (int) (Math.random() * 100 + 1);
		int lower_bound = 1, upper_bound = 100;
		boolean game_done = false;
		Scanner input = new Scanner(System.in);
		while (game_done == false) {
			System.out.printf("�вq�@��%d ~ %d�������Ʀr : ", lower_bound, upper_bound);
			String user_guess;
			user_guess = input.nextLine();
			// �T�{�ϥΪ̿�J�O�Ʀr
			boolean input_is_num = true;
			for (int i = 0; i < user_guess.length(); i++) {
				// �v�r�ˬdString �O�_���� 0~9 �����Ʀr
				if ((user_guess.charAt(i) < '0') || ('9' < user_guess.charAt(i)))
					input_is_num = false;
			}
			if (input_is_num == false) {
				System.out.println("���~!! �п�J�Ʀr!!");
				continue; // �p�G input_is_num �Ofalse �h��ܿ��~�í��s��J
			}
			int int_user_guess = Integer.parseInt(user_guess); // �Nuser_guess �૬�� int
			// �T�{�ϥΪ̿�J�O�_�b lower_bound ~upper_bound ��
			boolean input_in_region = false;
			if ((lower_bound <= int_user_guess) && (int_user_guess <= upper_bound))
				input_in_region = true;
			if (input_in_region == false) {
				System.out.printf("���~!! �п�J�b %d ~ %d �������\n", lower_bound, upper_bound);
				continue; // �p�G input_in_region �Ofalse �h��ܿ��~�í��s��J
			}	
			//�P�_�O�_�q��
			if (int_user_guess == Target_number) {
				//�Y����F
				game_done = true;
				System.out.println("�q��F~~ �n�δ�!!");
			}else if (Target_number > int_user_guess){
				//�Y���פ�q���Ʀr�٤j��
				lower_bound = int_user_guess + 1;
				if (lower_bound == upper_bound) {
					//��S���Ʀr�i�H�q�ɡA�C��GG
					game_done = true;
					System.out.printf("GG~~ Gameover!! ���T���׬� %d", Target_number);
				}	
			}else if (Target_number < int_user_guess){
				//�Y���פ�q���Ʀr�٤p��
				upper_bound = int_user_guess - 1;
				if (lower_bound == upper_bound) {
					//��S���Ʀr�i�H�q�ɡA�C��GG
					game_done = true;
					System.out.printf("GG~~ Gameover!! ���T���׬� %d", Target_number);
				}
			}
		}
		input.close();
	}
}
