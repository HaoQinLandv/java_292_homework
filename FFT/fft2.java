
public class fft2 {
	public static void main(String[] args) {
		//Cooley�VTukey FFT algorithm
		
		int N = 3;
		int n = (int)(Math.pow(2, N));
		double[] x_real = new double[n];
		double[] x_imag = new double[n];
		for(int i =0; i<n; i++)
			x_real[i] = i; //���ʹ��հT��

		
		System.out.println("���հT��");
		Print_array(x_real, x_imag); //print �T��
		
		FFT(x_real, x_imag, 0, n - 1);
		
		System.out.println("");
		System.out.println("FFT��");
		Print_array(x_real, x_imag); //print �T��
	}
	
	public static void Print_array(double[] x_real, double[] x_imag) {
		System.out.printf("�곡: \n");
		for(double i: x_real) 
			System.out.printf("%.3f, ", i);
		System.out.printf("\n");
		System.out.printf("�곡: \n");	
		for(double i: x_imag) 
			System.out.printf("%.3f, ", i);
		System.out.printf("\n");
	}
	public static void FFT(double[] x_real, double[] x_imag, int left, int right) {
		//Cooley Tukey FFT algorithm
		//INPUT: ���׬�2��power���誺 complex number
		
		//�������ƻP�Ƽ� Bit-reverse
		bit_reverse(x_real, 0, x_real.length-1);
		bit_reverse(x_imag, 0, x_imag.length-1);
		
		//������k
		int current_butter_len = 2;
		int n = x_real.length;
		while (current_butter_len <= n) {
			for (int i = 0; i < n / current_butter_len; i++) {
				butter(x_real, x_imag, i * current_butter_len, i * current_butter_len + (current_butter_len - 1));
			}
			current_butter_len *= 2;
		}
	}
	private static void butter(double[] x_real, double[] x_imag, int left, int right) {
		//������e��k
		int temp_len = right - left + 1;
		double[] x_real_output = new double[temp_len];
		double[] x_imag_output = new double[temp_len];
		double Wk_r, Wk_i;
		for (int k = left; k < (left + temp_len / 2); k++) {
			Wk_r = Math.cos((2 * Math.PI * k) / temp_len);
			Wk_i = -Math.sin((2 * Math.PI * k) / temp_len);
			x_real_output[k-left] = x_real[k] + (x_real[k + (temp_len / 2)] * Wk_r - x_imag[k + (temp_len / 2)] * Wk_i);
			x_imag_output[k-left] = x_imag[k] + (x_imag[k + (temp_len / 2)] * Wk_r + x_real[k + (temp_len / 2)] * Wk_i);
		}
		for (int k = (left + temp_len / 2); k <= right; k++) {
			Wk_r = Math.cos((2 * Math.PI * k) / temp_len);
			Wk_i = -Math.sin((2 * Math.PI * k) / temp_len);
			x_real_output[k-left] = x_real[k - temp_len / 2] + (x_real[k] * Wk_r - x_imag[k] * Wk_i);
			x_imag_output[k-left] = x_imag[k - temp_len / 2] + (x_imag[k] * Wk_r + x_real[k] * Wk_i);
		}
		
		for(int k= left;k<=right;k++) {
			x_real[k] = x_real_output[k-left];
			x_imag[k] = x_imag_output[k-left];
		}
			
	}
	private static void bit_reverse(double[] x_real, int a, int b) {
		if((b-a)>0) {
			separate_even_odd(x_real, a, b);
			int Half = a + (b - a) / 2;
			bit_reverse(x_real, a, Half);
			bit_reverse(x_real, Half + 1, b);
		}
	}

	private static void separate_even_odd(double[] array, int a, int b) {
		if (b - a > 1) {
			// �N array[a], �� array[b] ��@�@�ӷsarray
			// ���Ʃ�b�e���A�_�Ʃ�b�᭱
			int half_array_len = (b - a + 1) / 2;
			double[] temp_array = new double[half_array_len];
			// �_�ƴ��X�A���Ʃ��e��
			int index = 0;
			for (int i = a; i <= b; i++) {
				if ((i - a) % 2 != 0) {
					temp_array[index] = array[i];
					index += 1;
				} else {
					array[a + index] = array[i];
				}
			}
			// �N�_�ƶ�^�harray����b�q
			for (int i = 0; i < temp_array.length; i++)
				array[a + half_array_len + i] = temp_array[i];
		}
	}

}
