package Setup;

public class Gene {
	private char[] numBin = new char[10];

	public Gene(int num) {
		char[] numBin1 = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };
		numBin = numBin1;

		this.setNum(num);

	}

	public Gene(String num) {
		this.setNum(num);
	}

	public String getNum() {
		String resp = "";
		for (int i = 0; i < numBin.length; i++) {
			resp = resp + numBin[i];
		}
		return resp;
	}

	public void setNum(int num) {
		String numString = Integer.toBinaryString(num);

		char[] numBin1 = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

		for (int i = 0, j = numBin.length - 1, k = numString.length() - 1; i < numString.length(); i++) {
			numBin1[j] = numString.charAt(k);
		}
		numBin = numBin1;
	}

	public void setNum(String num) {
		numBin = num.toCharArray();
	}

}
