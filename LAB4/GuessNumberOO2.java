import java.util.Scanner;

public class GuessNumberOO2 {
	public static void main(String[] args) {

		// �إߤ@�Ӳq�Ʀr�C������
		GuessNumberGame G = new GuessNumberGame(); //�w�]�غc�l
//		GuessNumberGame G = new GuessNumberGame(100, 200); // �۩w�q�غc�l
		int Mode = G.SelectMode(); // �Ҧ����
		G.Play(Mode); // �C���D��
	}

}

class GuessNumberGame {
	// constructor
	public GuessNumberGame(int lower_bound, int upper_bound) {
		this.lower_bound = lower_bound;
		this.upper_bound = upper_bound;
		this.Target_number = (int) (Math.random() * (upper_bound - lower_bound) + lower_bound);
	}

	public GuessNumberGame() {
		lower_bound = 1;
		upper_bound = 100;
		Target_number = (int) (Math.random() * 100 + 1);
	}

	// Attribute & Data
	private int Target_number;
	private int lower_bound, upper_bound;

	// Method & Function
	// public method
	public int SelectMode() {
		Scanner input = new Scanner(System.in);
		boolean Selection_Done = false;
		int input_mode = 0;
		while (!Selection_Done) {
			System.out.println("�п�ܹC���Ҧ�");
			System.out.println("1. �ϥΪ̹C��  2.AI(�d���H��) 3.AI(Bisection)");
			input_mode = input.nextInt();
			if ((input_mode == 1) || (input_mode == 2) || (input_mode == 3)) {
				Selection_Done = true;
			} else {
				System.out.println("��J���~�A�Э��s��J!!");
			}
		}
		return input_mode;
	}

	public void Play(int Mode) {
		Scanner input = new Scanner(System.in);
		// �Τ@�� while loop ����C�����i��
		boolean Game_end = false;
		while (!Game_end) {
			PrintHint();
			boolean GuessCorrect;
			switch (Mode) {
			case 1:
				GuessCorrect = UserGuess(input.nextLine()); // �ϥΪ̦ۤv��
				break;
			case 2:
				GuessCorrect = AIRandomGuess(); // AI1 �H���òq
				break;
			default:
				GuessCorrect = AIBisectionGuess(); // AI2 Bisection
				break;
			}
			if (GuessCorrect) {
				// �p�G�q���C�������A��ܳӧQ
				Game_end = true;
			} else {
				// �p�G�q���A�ˬd�O�_GG
				// �Y�٦��Ʀr�i�q�A�~��i��while loop
				Game_end = IsGG();
			}
		}
	}

	// private method
	private void PrintHint() {
		System.out.printf("�вq�@��%d ~ %d�������Ʀr : \n", lower_bound, upper_bound);
	}

	private boolean UserGuess(String input_str) {
		// �ư��U�إեبϥΪ̶ÿ�J
		if (Is_Num(input_str)) {
			if (In_Range(input_str)) {
				// �T�w��J�r�ꬰ�ŦX���󪺼Ʀr�A�A�N�r���ରint
				int input_int = Integer.parseInt(input_str);
				return IsGuessCorrect(input_int);
			} else {
				// ��J���A�d�򤺡A�P�_���q��
				return false;
			}
		} else {
			// ��J�ëD�Ʀr�A�P�_���q��
			return false;
		}
	}

	private boolean AIRandomGuess() {
		int ai_guess = (int) (Math.random() * (upper_bound - lower_bound) + lower_bound);
		System.out.println("AI �q���Ʀr��" + ai_guess);
		return IsGuessCorrect(ai_guess);
	}

	private boolean AIBisectionGuess() {
		// �q���b�d�򤺡A�H���q�@�ӼƦr
		// �q����������:
		// �q�ثe lower_bound �P upper_bound �������I
		int ai_guess = (int) (upper_bound - lower_bound) / 2 + lower_bound;
		System.out.println("AI �q���Ʀr��" + ai_guess);
		return IsGuessCorrect(ai_guess);
	}

	private boolean IsGG() {
		if (lower_bound == upper_bound) {
			// ��S���Ʀr�i�H�q�ɡA�C��GG
			System.out.printf("GG~~ Gameover!! ���T���׬� %d", Target_number);
			return true;
		} else {
			return false;
		}
	}

	private boolean IsGuessCorrect(int guess_number) {
		// �P�_�O�_�q��
		if (guess_number == Target_number) {
			// �Y����F
			System.out.println("�q��F~~ �n�δ�!!");
			return true;
		} else if (Target_number > guess_number) {
			// �Y���פ�q���Ʀr�٤j��
			lower_bound = guess_number + 1;
			return false;
		} else if (Target_number < guess_number) {
			// �Y���פ�q���Ʀr�٤p��
			upper_bound = guess_number - 1;
			return false;
		} else {
			return false;
		}
	}

	private boolean Is_Num(String input_str) {
		// �T�{�ϥΪ̿�J�O�Ʀr
		boolean result = true;
		for (int i = 0; i < input_str.length(); i++) {
			// �v�r�ˬdString �O�_���� 0~9 �����Ʀr
			if ((input_str.charAt(i) < '0') || ('9' < input_str.charAt(i)))
				result = false;
		}
		if (result == false)
			System.out.printf("���~!!�п�J�Ʀr\n");
		return result;
	}

	private boolean In_Range(String input_num) {
		// �T�{�ϥΪ̿�J�O�_�b lower_bound ~upper_bound ��
		int int_user_guess = Integer.parseInt(input_num); // �Nuser_guess �૬�� int
		boolean result = false;
		if ((lower_bound <= int_user_guess) && (int_user_guess <= upper_bound))
			result = true;
		if (result == false)
			System.out.printf("���~!! �п�J�b %d ~ %d �������\n", lower_bound, upper_bound);
		return result;
	}
}
