import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFrame;

class blocks { // �@���������X�A��l��m���b�e���������W�����
	int[][] Positionx; // x�y��
	int[][] Positiony; // y�y��
	char[] blocks_char; // �C�@�Ӥ��P����������r��
	int current_block; // ��e�����������index
	int Max_blocks; //�n����������̤j��index
	int width;
	int height;
	
	boolean block_drop() {
		// ����������
		// �^�ǬO�_ GameOver
		int[] tempy = new int[4];
		for (int i = 0; i < 4; i++)
			tempy[i] = Positiony[current_block][i] + 1; // �C�@�Ӥ�����󩹤U���ʤ@��

		if (Is_overlap(current_block, Positionx[current_block], tempy)) {
			current_block += 1; // �ثe���ʪ� current_block index + 1
			return Is_Game_Over(tempy);
		} else if ((tempy[0] >= height) || (tempy[1] >= height) || (tempy[2] >= height) || (tempy[3] >= height)) {
			current_block += 1; // �ثe���ʪ� current_block index + 1
			return false;
		} else {
			// �Y�S�����쩳�A�]�S���P��L������|�A�h���U���ʤ@��
			for (int i = 0; i < 4; i++)
				Positiony[current_block][i] = tempy[i];
			return false; // �^�ǩ|��GameOver
		}

	}
	boolean Is_no_blocks() {
		if(current_block ==  Max_blocks) {
			return true;
		}else {
			return false;
		}
	}
	boolean Is_Game_Over(int[] tempy) {
		//�p�G�ثe������@�ӳ���Y�y�а���C���̰��I
		//�h�^�� true
		if ((tempy[0] < 0) || (tempy[1] < 0) || (tempy[2] < 0) || (tempy[3] < 0)) {
			return true;
		} else {
			return false;
		}
		
	}
	boolean Is_overlap(int current_block, int[] tempx, int[] tempy) {
		// �T�{��e���ʤ�����A�O�_����H�������@�Ӥ�������|
		// ��e���ʤ��x�y�� tempx�A ��e���ʤ�� y�y�� tempy
		boolean overlap_with_other_block = false;
		for (int i = 0; i < current_block; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if ((Positionx[i][j] == tempx[k]) && (Positiony[i][j] == tempy[k])) {
						overlap_with_other_block = true;
						break;
					}
				}
			}
		}
		return overlap_with_other_block;
	}
	boolean Is_inside(int[] currentx, int[] currenty) {
		//�T�{ currentx currenty �O�_�b�C����ɤ���
		boolean Inside = true;
		for(int i = 0; i<currentx.length; i++) {
			if((currentx[i] < 1)||( width < currentx[i]) ||(currenty[i] < 0)||( height <= currenty[i])) {
				Inside = false;
			}
		}
		return Inside;
	}
	void rotate_cw(int[] x, int[] y) {
		// ���ɰw90�ױ���
		int[] tempx = new int[4];
		int[] tempy = new int[4];
		// 1. ���N���߲��ʨ�(0,0)
		for (int i = 0; i < 4; i++) {
			tempx[i] = x[i] - x[0];
			tempy[i] = y[i] - y[0];
		}
		// 2. x�y���ܸ�
		for (int i = 0; i < 4; i++) {
			if (tempx[i] != 0)
				tempx[i] = (-1) * tempx[i];
		}
		// 3. x�y�� y�y�� �洫
		int temp;
		for (int i = 0; i < 4; i++) {
			temp = tempx[i];
			tempx[i] = tempy[i];
			tempy[i] = temp;
		}

		// 4. �N������߲��^ x[0] , y[0]
		for (int i = 0; i < 4; i++) {
			tempx[i] = tempx[i] + x[0];
			tempy[i] = tempy[i] + y[0];
		}

		// ��X�y��
		for (int i = 0; i < 4; i++) {
			x[i] = tempx[i];
			y[i] = tempy[i];
		}
	}
	void rotate_ccw(int[] x, int[] y) {
		//�f�ɰw90�ױ���
		int[] tempx = new int[4];
		int[] tempy = new int[4];
		// 1. ���N���߲��ʨ�(0,0)
		for (int i = 0; i < 4; i++) {
			tempx[i] = x[i] - x[0];
			tempy[i] = y[i] - y[0];
		}

		// 2. x�y�� y�y�� �洫
		int temp;
		for (int i = 0; i < 4; i++) {
			temp = tempx[i];
			tempx[i] = tempy[i];
			tempy[i] = temp;
		}
		// 3. x�y���ܸ�
		for (int i = 0; i < 4; i++) {
			if (tempx[i] != 0)
				tempx[i] = (-1) * tempx[i];
		}

		// 4. �N������߲��^ x[0] , y[0]
		for (int i = 0; i < 4; i++) {
			tempx[i] = tempx[i] + x[0];
			tempy[i] = tempy[i] + y[0];
		}
		
		//��X�y��
		for (int i = 0; i < 4; i++) {
			x[i] = tempx[i];
			y[i] = tempy[i];
		}
	}
	char[][] blocks_to_draw_format(){
		char[][] output = new char[height][width+2];		
		for (int i = 0; i<=current_block; i++) {
			for (int j = 0; j<4; j++) {
				if ((0<=Positiony[i][j])&&(Positiony[i][j]<height)&&(1<=Positionx[i][j])&&(Positionx[i][j]<width+1)) {
					output[Positiony[i][j]][Positionx[i][j]] = blocks_char[i];
				}
			}
		}
		return output;
	}
	boolean is_vanish_event(boolean[] vanish_row) {
		//�^�ǬO�_�����h����o���ƥ�A�åB��svanish_row
		boolean output = false;
		//�N vanish_row �k�s
		for (int i = 0; i<height; i++) {
			vanish_row[i] = false;
		}
		int[] accumulated = new int[height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < current_block; j++) {
				for (int k = 0; k < 4; k++) {
					if (Positiony[j][k] == i) {
						accumulated[i] += 1;
					}
				}
			}
			if(accumulated[i] == width) {
				output = true;
				vanish_row[i] = true;
			}
		}
		
		return output;
	}	
	blocks(int width, int height) { // constructor
		this.width = width;
		this.height = height;
		Max_blocks = 150;
		Positionx = new int[Max_blocks][4];
		Positiony = new int[Max_blocks][4];
		blocks_char = new char[Max_blocks];
		current_block = 0;
		
		char[] CharTable = { 'A', 'B', 'C', 'D', 'E' };
		for (int i = 0; i < Max_blocks; i++) {
			blocks_char[i] = CharTable[i%5];
		}
		
		
		int random_shape_type; // �C�@�Ӥ�����Ϊ��A�Q���H���ͦ�
		int random_rotate; //�C�@�Ӥ�����H������, 0,1,2,3, ���ɰw���� 0 90 180 270 ��
		int initialx = (this.width)/2; // ������ ������ߪ��_�lx�y��
		int initialy = -2; // ������ ������ߪ��_�ly�y��
		
		for (int i = 0; i < Max_blocks; i++) {
			random_shape_type = (int) (Math.random() * 6) + 1; //�H�����ͤ�����A
			random_rotate = (int) (Math.random() * 4); //�H�����ͤ������
			// (i�����߮y��x, i�����߮y��y) (Positionx[i][0], Positiony[i][0])
			switch (random_shape_type) {
			case 1:
				// ������A1: (���������त��)
				//   X
				//   X
				//   X X
				Positionx[i][0] = initialx;
				Positionx[i][1] = initialx;
				Positionx[i][2] = initialx;
				Positionx[i][3] = initialx + 1;
				Positiony[i][0] = initialy;
				Positiony[i][1] = initialy - 1;
				Positiony[i][2] = initialy + 1;
				Positiony[i][3] = initialy + 1;
				break;
			case 2:
				// ������A2: (���W�����त��)
				// X X
				// X X
				Positionx[i][0] = initialx;
				Positionx[i][1] = initialx;
				Positionx[i][2] = initialx + 1;
				Positionx[i][3] = initialx + 1;
				Positiony[i][0] = initialy;
				Positiony[i][1] = initialy + 1;
				Positiony[i][2] = initialy + 1;
				Positiony[i][3] = initialy;
				break;
			case 3:
				// ������A3: (���U�����त��)
				//   X
				// X X X
				Positionx[i][0] = initialx;
				Positionx[i][1] = initialx;
				Positionx[i][2] = initialx - 1;
				Positionx[i][3] = initialx + 1;
				Positiony[i][0] = initialy;
				Positiony[i][1] = initialy - 1;
				Positiony[i][2] = initialy;
				Positiony[i][3] = initialy;
				break;
			case 4:
				// ������A4: (���k�����त��)
				//   X
				//   X X
				//     X
				Positionx[i][0] = initialx;
				Positionx[i][1] = initialx - 1;
				Positionx[i][2] = initialx - 1;
				Positionx[i][3] = initialx;
				Positiony[i][0] = initialy;
				Positiony[i][1] = initialy - 1;
				Positiony[i][2] = initialy;
				Positiony[i][3] = initialy + 1;
				break;
			case 5:
				// ������A5: (���k�����त��)
				//     X
				//   X X
				//   X
				Positionx[i][0] = initialx;
				Positionx[i][1] = initialx;
				Positionx[i][2] = initialx - 1;
				Positionx[i][3] = initialx - 1;
				Positiony[i][0] = initialy;
				Positiony[i][1] = initialy - 1;
				Positiony[i][2] = initialy;
				Positiony[i][3] = initialy + 1;
				break;
			case 6:
				// ������A6: (�W���ƤU�ӲĤG�Ӭ����त��)
				//     X
				//     X
				//     X
				//     x
				Positionx[i][0] = initialx;
				Positionx[i][1] = initialx;
				Positionx[i][2] = initialx;
				Positionx[i][3] = initialx;
				Positiony[i][0] = initialy;
				Positiony[i][1] = initialy - 1;
				Positiony[i][2] = initialy + 1;
				Positiony[i][3] = initialy + 2;
				break;
			default:

			}
			//�H�����ɰw���� random_rotate ��
			for (int j = 0; j < random_rotate; j++)
				rotate_cw(Positionx[i], Positiony[i]);

		}

	}
}

public class Tetris extends JFrame {
	static int width = 17; // �C����ت��e
	static int height = 20; // �C����ت���
	static int score = 0;
	static int sleep_time = 100; //����ɶ�
	static char keyboard_input = '0'; //0 ��ܥ�����������L��J
	static boolean GameOver = false;
	static boolean[] vanish_row = new boolean[height];
	static blocks BLOCKS = new blocks(width, height); // �s��������������data

	public static void main(String... arg) throws IOException, InterruptedException {
		new Tetris(); // new�@�ӵ����Ӻ�ť��L
		while (!GameOver && !BLOCKS.Is_no_blocks()) {
			
			//�Ĥ@����s�e���A���汼�������ơA�]���汵����L������
			GameOver = BLOCKS.block_drop();		
			keyboard_event_update(BLOCKS);
			if (GameOver||BLOCKS.Is_no_blocks()) {
				break;
			}else {
				Draw();
			}
			Thread.sleep(sleep_time);
			CleanScreen();
			
			//�ĤG����s�e���A�u���汵����L������
			keyboard_event_update(BLOCKS);
			if (GameOver||BLOCKS.Is_no_blocks()) {
				break;
			}else {
				Draw();
			}
			Thread.sleep(sleep_time);
			CleanScreen();

		}
		if (GameOver) {
			Draw_GG_screen();
		} else if (BLOCKS.Is_no_blocks()) {
			Draw_No_block_screen();
		}
	}
	public static void CleanScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();// �M���ù�
	}

	public static void keyboard_event_update(blocks B) {
		int[] tempx = new int[4];
		int[] tempy = new int[4];
		boolean is_overlap;
		boolean is_inside;
		switch (keyboard_input) {
		case 's':
			// ������ɰw���� 90 ��
			// ���� XY �y�нƻs�X��
			for (int i = 0; i < B.Positionx[B.current_block].length; i++) {
				tempx[i] = B.Positionx[B.current_block][i];
				tempy[i] = B.Positiony[B.current_block][i];
			}

			B.rotate_cw(tempx, tempy);
			is_overlap = B.Is_overlap(B.current_block, tempx, tempy);
			is_inside = B.Is_inside(tempx, tempy);

			// �p�G�|���| �B�b�d�� �h����
			if ((!is_overlap) && is_inside) {
				for (int i = 0; i < 4; i++) {
					B.Positionx[B.current_block][i] = tempx[i];
					B.Positiony[B.current_block][i] = tempy[i];
				}
			}
			break;
		case 'w':
			// ����f�ɰw���� 90 ��
			for (int i = 0; i < B.Positionx[B.current_block].length; i++) {
				tempx[i] = B.Positionx[B.current_block][i];
				tempy[i] = B.Positiony[B.current_block][i];
			}

			B.rotate_ccw(tempx, tempy);
			is_overlap = B.Is_overlap(B.current_block, tempx, tempy);
			is_inside = B.Is_inside(tempx, tempy);

			// �p�G�|���| �B�b�d�� �h����
			if ((!is_overlap) && is_inside) {
				for (int i = 0; i < 4; i++) {
					B.Positionx[B.current_block][i] = tempx[i];
					B.Positiony[B.current_block][i] = tempy[i];
				}
			}
			break;
		case 'a':
			// ���ʤ������
			for (int i = 0; i < 4; i++)
				tempx[i] = B.Positionx[B.current_block][i] - 1;

			is_overlap = B.Is_overlap(B.current_block, tempx, B.Positiony[B.current_block]);
			is_inside = B.Is_inside(tempx, B.Positiony[B.current_block]);

			if ((!is_overlap) && is_inside) {
				for (int i = 0; i < 4; i++)
					B.Positionx[B.current_block][i] = tempx[i];
			}
			break;
		case 'd':
			// ���ʤ�����k
			for (int i = 0; i < 4; i++)
				tempx[i] = B.Positionx[B.current_block][i] + 1;

			is_overlap = B.Is_overlap(B.current_block, tempx, B.Positiony[B.current_block]);
			is_inside = B.Is_inside(tempx, B.Positiony[B.current_block]);

			if ((!is_overlap) && is_inside) {
				for (int i = 0; i < 4; i++)
					B.Positionx[B.current_block][i] = tempx[i];
			}
			break;
		}
		keyboard_input = '0'; //�^�_��l���A�AKeyboard ����������r��
	}
	

	public static void Draw() throws IOException, InterruptedException {
		char[][] draw_format = BLOCKS.blocks_to_draw_format();
		boolean IsVanish = BLOCKS.is_vanish_event(vanish_row);
		if (!IsVanish) {
			//�p�G�S�������ƥ�
			// �ϤW��
			System.out.println("   Tetris Game");
			for (int i = 0; i < width + 2; i++)
				System.out.printf("#");
			System.out.printf("\n");
			// �Ϥ���
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width + 2; j++) {
					if (j == 0) {
						System.out.printf("#");
					} else if (j == width + 1) {
						System.out.printf("#");
					} else {
						System.out.printf("%c", draw_format[i][j]);
					}
				}
				System.out.printf("\n");
			}
			// �ϤU��
			for (int i = 0; i < width + 2; i++)
				System.out.printf("#");
			System.out.printf("\n");
			System.out.println(" 'a' : left, 'd': right");
			System.out.println(" 's' : rotate clockwise");
			System.out.println(" 'w' : rotate counter clockwise");
			System.out.println("Score : " + score);
			System.out.println("Speed : " + sleep_time + "%");

		} else {

			// �p�G�������ƥ�
			// 1. �N�n���h��row �� @ �C�L
			// �ϤW��
			System.out.println("   Tetris Game");
			for (int i = 0; i < width + 2; i++)
				System.out.printf("#");
			System.out.printf("\n");
			// �Ϥ���
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width + 2; j++) {
					if (j == 0) {
						System.out.printf("#");
					} else if (j == width + 1) {
						System.out.printf("#");
					} else if (vanish_row[i]){
						System.out.printf("@");
					}else {
						System.out.printf("%c", draw_format[i][j]);
					}
				}
				System.out.printf("\n");
			}
			// �ϤU��
			for (int i = 0; i < width + 2; i++)
				System.out.printf("#");
			System.out.printf("\n");
			System.out.println(" 'a' : left, 'd': right");
			System.out.println(" 's' : rotate clockwise");
			System.out.println(" 'w' : rotate counter clockwise");
			System.out.println("Score : " + score);
			System.out.println("Speed : " + sleep_time + "%");
			Thread.sleep(sleep_time);
			CleanScreen();
			
			// 2. �N�n���h������]�w��999
			for ( int i = 0; i<BLOCKS.current_block; i++) {
				for (int  j = 0; j<4; j++) {
					if((0<=BLOCKS.Positiony[i][j])&&(BLOCKS.Positiony[i][j]<height)) {
						if(vanish_row[BLOCKS.Positiony[i][j]]){
							BLOCKS.Positionx[i][j] = 999;
							BLOCKS.Positiony[i][j] = 999;
						}
					}	
				}
			}
			
			// 3. �N�n���h��row�W������������ʩ��U�A���켲���L����A�μ��쩳�O
			int vanish_num = 0;// �p�⦹�����h�X��
			for (int i = 0; i < height; i++) {
				if (vanish_row[i]) {
					vanish_num += 1;
				}
			}
			
			score += vanish_num*100; //�[��
			
			for (int v = 0; v < BLOCKS.height; v++) {
				if (vanish_row[v]) {
					for (int i = 0; i < BLOCKS.current_block; i++) {
						for (int j = 0; j < 4; j++) {
							if ((0 <= BLOCKS.Positiony[i][j]) && (BLOCKS.Positiony[i][j] < height)) {
								if (BLOCKS.Positiony[i][j] < v) {
									BLOCKS.Positiony[i][j] += 1;
								}
							}
						}
					}
				}
			}
			BLOCKS.is_vanish_event(vanish_row); //��s vanish_row
			
			
			
			draw_format = BLOCKS.blocks_to_draw_format();
			// �ϤW��
			System.out.println("   Tetris Game");
			for (int i = 0; i < width + 2; i++)
				System.out.printf("#");
			System.out.printf("\n");
			// �Ϥ���
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width + 2; j++) {
					if (j == 0) {
						System.out.printf("#");
					} else if (j == width + 1) {
						System.out.printf("#");
					} else {
						System.out.printf("%c", draw_format[i][j]);
					}
				}
				System.out.printf("\n");
			}
			// �ϤU��
			for (int i = 0; i < width + 2; i++)
				System.out.printf("#");
			System.out.printf("\n");
			System.out.println(" 'a' : left, 'd': right");
			System.out.println(" 's' : rotate clockwise");
			System.out.println(" 'w' : rotate counter clockwise");
			System.out.println("Score : " + score);
			System.out.println("Speed : " + sleep_time + "%");
		}

	}

	public static void Draw_GG_screen() {
		// �C�LGAME OVER �e��
		System.out.println("   Tetris Game");
		for (int i = 0; i < width + 2; i++)
			System.out.printf("#");
		System.out.printf("\n");
		for (int i = 0; i < height; i++) {
			if (i != height / 2) {
				for (int j = 0; j < width + 2; j++) {
					if (j == 0) {
						System.out.printf("#");
					} else if (j == width + 1) {
						System.out.printf("#");
					} else {
						System.out.printf(" ");
					}
				}
				System.out.printf("\n");
			} else {

				System.out.printf("#");
				for (int j = 0; j < (width - 10) / 2; j++) {
					System.out.printf(" ");
				}
				System.out.printf("GAME OVER!");
				for (int j = 0; j < (width - 10) / 2; j++) {
					System.out.printf(" ");
				}
				if (width % 2 != 0)
					System.out.printf(" ");
				System.out.printf("#\n");
			}
		}
		for (int i = 0; i < width + 2; i++)
			System.out.printf("#");
		System.out.printf("\n");
		System.out.println(" 'a' : left, 'd': right");
		System.out.println(" 's' : rotate clockwise");
		System.out.println(" 'w' : rotate counter clockwise");
		System.out.println("Score : " + score);
		System.out.println("Speed : " + sleep_time + "%");
	}
	
	public static void Draw_No_block_screen() {
		// �C�L�w�g�S���i������blocks�e��
		System.out.println("   Tetris Game");
		for (int i = 0; i < width + 2; i++)
			System.out.printf("#");
		System.out.printf("\n");
		for (int i = 0; i < height; i++) {
			if ( (i != height / 2) && (i != (height / 2 + 1))) {
				for (int j = 0; j < width + 2; j++) {
					if (j == 0) {
						System.out.printf("#");
					} else if (j == width + 1) {
						System.out.printf("#");
					} else {
						System.out.printf(" ");
					}
				}
				System.out.printf("\n");
			} else if(i == height / 2){
				System.out.printf("#");
				for (int j = 0; j < (width - 10) / 2; j++) {
					System.out.printf(" ");
				}
				System.out.printf("GAME OVER!");
				for (int j = 0; j < (width - 10) / 2; j++) {
					System.out.printf(" ");
				}
				if (width % 2 != 0)
					System.out.printf(" ");
				System.out.printf("#\n");
			} else {
				System.out.printf("#");
				for (int j = 0; j < (width - 10) / 2; j++) {
					System.out.printf(" ");
				}
				System.out.printf("NO Blocks!");
				for (int j = 0; j < (width - 10) / 2; j++) {
					System.out.printf(" ");
				}
				if (width % 2 != 0)
					System.out.printf(" ");
				System.out.printf("#\n");
			}
		}
		for (int i = 0; i < width + 2; i++)
			System.out.printf("#");
		System.out.printf("\n");
		System.out.println(" 'a' : left, 'd': right");
		System.out.println(" 's' : rotate clockwise");
		System.out.println(" 'w' : rotate counter clockwise");
		System.out.println("Score : " + score);
		System.out.println("Speed : " + sleep_time + "%");
	}
	

	public Tetris() { // constructor
		this.setSize(250, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("��ť��L��J");
		this.setVisible(true);
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				keyboard_input = Character.toLowerCase(c); //�Τ@��^�p�g
			}
		});
	}
}
