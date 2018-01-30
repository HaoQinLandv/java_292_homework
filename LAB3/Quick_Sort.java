public class Quick_Sort {
	public static void main(String[] args) {
		// ���ͤ@��array
		int N = 100;
		int[] x = new int[N];
		for (int i = 0; i < N; i++)
			x[i] = i;

		// Fisher Yates shuffle
		for (int i = N - 1; i >= 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			// swap
			int temp;
			temp = x[j];
			x[j] = x[i];
			x[i] = temp;
		}
		System.out.println("���ƧǼƦC:");
		PrintArray(x);
		QuickSort(x, 0, N - 1);
		System.out.println("QuickSort�Ƨǫ�:");
		PrintArray(x);
	}

	public static void QuickSort(int[] x, int left, int right) {
		if (right > left) {
			int P = Separate(x, left, right);
			QuickSort(x, left, P - 1);
			QuickSort(x, P + 1, right);
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

	public static void PrintArray(int[] x) {
		for (int i : x)
			System.out.printf(" %d,", i);
		System.out.printf("\n");
	}

}
