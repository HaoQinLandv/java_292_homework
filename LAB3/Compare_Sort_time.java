import java.util.Arrays; //���F�ϥ� Array copy

public class Compare_Sort_time {
	public static void main(String[] args) {
		// ���ͤ@�� N*2000�� 2D array
		// ��C�@�� row ���@�խn�ƧǪ� array
		int N = 500;
		int[][] test_data = new int[N][2000];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2000; j++)
				test_data[i][j] = j;
		}

		// ��C�@��row ���~��
		for (int i = 0; i < N; i++) {
			shuffle(test_data[i]);
		}

		// Copy test_data �� InsertionSort �Ƨ�
		// �Ҧ����ƧǪk���O��ۦP���Ǫ�test_data�A���դ~����
		// �ƻs2D test_data
		int[][] data_InsertionSort = new int[N][2000];
		for (int i = 0; i < N; i++) {
			data_InsertionSort[i] = Arrays.copyOf(test_data[i], test_data[i].length);
		}
		int[][] data_BubbleSort = new int[N][2000];
		for (int i = 0; i < N; i++) {
			data_BubbleSort[i] = Arrays.copyOf(test_data[i], test_data[i].length);
		}
		int[][] data_SelectionSort = new int[N][2000];
		for (int i = 0; i < N; i++) {
			data_SelectionSort[i] = Arrays.copyOf(test_data[i], test_data[i].length);
		}
		int[][] data_QuickSort = new int[N][2000];
		for (int i = 0; i < N; i++) {
			data_QuickSort[i] = Arrays.copyOf(test_data[i], test_data[i].length);
		}
		// �ƻs2D test_data ����

		System.out.printf("%d �����׬� 2000 ��int[] �Ƨ�:\n", N);
		long t1, t2;
		t1 = System.currentTimeMillis();
		// ��C�@��row ��InsertionSort�A�íp��
		for (int i = 0; i < N; i++) {
			InsertionSort(data_InsertionSort[i]);
		}
		t2 = System.currentTimeMillis();
		System.out.printf("InsertionSort��F�G %d �@��\n", ((t2 - t1)));

		t1 = System.currentTimeMillis();
		// ��C�@��row ��BubbleSort�A�íp��
		for (int i = 0; i < N; i++) {
			BubbleSort(data_BubbleSort[i]);
		}
		t2 = System.currentTimeMillis();
		System.out.printf("BubbleSort��F�G %d �@��\n", ((t2 - t1)));

		t1 = System.currentTimeMillis();
		// ��C�@��row ��SelectionSort�A�íp��
		for (int i = 0; i < N; i++) {
			SelectionSort(data_SelectionSort[i]);
		}
		t2 = System.currentTimeMillis();
		System.out.printf("SelectionSort��F�G %d �@��\n", ((t2 - t1)));

		t1 = System.currentTimeMillis();
		// ��C�@��row ��Quick_Sort�A�íp��
		for (int i = 0; i < N; i++) {
			Quick_Sort(data_QuickSort[i], 0, 1999);
		}
		t2 = System.currentTimeMillis();
		System.out.printf("Quick_Sort��F�G %d �@��\n", ((t2 - t1)));

	}

	public static void Quick_Sort(int[] x, int left, int right) {
		if (right > left) {
			int P = Separate(x, left, right);
			Quick_Sort(x, left, P - 1);
			Quick_Sort(x, P + 1, right);
		}
	}

	public static int Separate(int[] x, int left, int right) {
		// �@�h��Separate
		// left �P right ��e�n�������l�}�C�����}�l���� �P��������
		// �^�ǭȬ�Separate �� povit ��m��������
		int Povit = x[right]; // ���̫�@��element ��@Povit
		int i = left - 1;// i��� �p��Povit���l�}�C���̫�@�Ӥ���m
		// j ���{�b���b�P�_���U�Ц�m
		for (int j = left; j < right; j++) {
			if (x[j] < Povit) {
				i += 1;
				// swap
				int temp;
				temp = x[j];
				x[j] = x[i];
				x[i] = temp;
			}
		}
		// �̫�N Povit ��^������m
		// swap
		int temp;
		temp = x[i + 1];
		x[i + 1] = x[right];
		x[right] = temp;
		return i + 1;
	}

	public static void BubbleSort(int[] x) {
		// Bubble Sort
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < (x.length - 1 - i); j++) {
				if (x[j] > x[j + 1]) {
					// swap
					int temp;
					temp = x[j + 1];
					x[j + 1] = x[j];
					x[j] = temp;
				}
			}
		}
	}

	public static void SelectionSort(int[] x) {
		// Selection Sort
		for (int i = 0; i < x.length; i++) {
			for (int j = (i + 1); j < x.length; j++) {
				if (x[i] > x[j]) {
					// swap
					int temp;
					temp = x[i];
					x[i] = x[j];
					x[j] = temp;
				}
			}
		}
	}

	public static void InsertionSort(int[] x) {
		// Insertion Sort
		for (int i = 1; i < x.length; i++) {
			int temp = x[i];
			int search_ind = i - 1;
			while (x[search_ind] > temp) {
				x[search_ind + 1] = x[search_ind];
				x[search_ind] = temp;
				search_ind -= 1;
				if (search_ind < 0)
					break;
			}
		}
	}

	public static void shuffle(int[] x) {
		// Fisher Yates shuffle
		int N = x.length;
		for (int i = N - 1; i >= 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			// swap
			int temp;
			temp = x[j];
			x[j] = x[i];
			x[i] = temp;
		}
	}

	public static void PrintArray(int[] x) {
		for (int i : x)
			System.out.printf(" %d,", i);
		System.out.printf("\n");
	}

}
