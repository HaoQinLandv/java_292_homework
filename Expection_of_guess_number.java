import java.util.Scanner;
public class Expection_of_guess_number {
	public static void main(String[] args) {
		// �q���H���q�Ʀr���ơA�`�@����simulated_num���A�åB�p��ӧQ���v�����
		Scanner input = new Scanner(System.in);
		int simulated_num;
		System.out.printf("�q���H���q�Ʀr���ơA�`�@���� N ���C\n N = ");
		simulated_num = input.nextInt();
		boolean input_done = false;
		boolean is_printf = true;
		while(input_done == false) {
			System.out.println("�O�_�����L�{ printf �X? �O(Y)/�_(N)");
			char answer_chr = input.next().charAt(0);
			if ((answer_chr == 'Y')||(answer_chr == 'y')||(answer_chr == '�O')) {
				is_printf = true;
				input_done = true;
			}else if ((answer_chr == 'N')||(answer_chr == 'n')||(answer_chr == '�_')){
				is_printf = false;
				input_done = true;
			}else {
				System.out.println("��J���~");
				input_done = false;
			}
		}
		input.close();

			

		int num_of_win = 0; //�q�����\�ӧQ���`����
		for (int iter_com = 0; iter_com < simulated_num; iter_com++) {
			
			
			int Target_number = (int) (Math.random() * 100 + 1);
			int lower_bound = 1, upper_bound = 100;
			boolean game_done = false;
			while (game_done == false) {
				if (is_printf)
					System.out.printf("�вq�@��%d ~ %d�������Ʀr : ", lower_bound, upper_bound);
				int int_com_guess;
				// �q���b�d�򤺡A�H���q�@�ӼƦr
				int_com_guess = (int) (Math.random()*(upper_bound-lower_bound +1) + lower_bound);
				if (is_printf)
					System.out.println("AI�q���Ʀr�O : " + int_com_guess);
				// �T�{�q����J�O�_�b lower_bound ~upper_bound ��
				boolean input_in_region = false;
				if ((lower_bound <= int_com_guess) && (int_com_guess <= upper_bound))
					input_in_region = true;
				if (input_in_region == false) {
					if (is_printf)
						System.out.printf("���~!! �п�J�b %d ~ %d �������\n", lower_bound, upper_bound);
					continue; // �p�G input_in_region �Ofalse �h��ܿ��~�í��s��J
				}
				
				//�P�_�O�_�q��
				if (int_com_guess == Target_number) {
					//�Y����F
					game_done = true;
					if (is_printf)
						System.out.println("�q��F~~ �n�δ�!!");
					num_of_win += 1;
				}else if (Target_number > int_com_guess){
					//�Y���פ�q���Ʀr�٤j��
					lower_bound = int_com_guess + 1;
					if (lower_bound == upper_bound) {
						game_done = true;
						if (is_printf)
							System.out.printf("GG~~ Gameover!! ���T���׬� %d \n", Target_number);
					}
					
				}else if (Target_number < int_com_guess){
					//�Y���פ�q���Ʀr�٤p��
					upper_bound = int_com_guess - 1;
					if (lower_bound == upper_bound) {
						game_done = true;
						if (is_printf)
							System.out.printf("GG~~ Gameover!! ���T���׬� %d \n", Target_number);
					}
				}

			}
		}
		System.out.printf("�`�@���� %d �� �A�q���ӧQ%d �� \n",simulated_num, num_of_win);
		System.out.printf("�b�d�򤺧����H���òq�������U�A�q����Ӿ��v�� %.4f", (1.0*num_of_win/simulated_num));
	}

}
