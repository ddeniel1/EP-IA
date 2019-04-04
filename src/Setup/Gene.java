package Setup;

public class Gene {
	private boolean[] num = new boolean[10];

	public Gene(int num) {
		
		this.setNum(num);

	}

	public int getNum() {
		for (int i = 0; i < num.length; i++) {
			if (num[i]) return i+1;
		}
		return -1;
	}

	public void setNum(int num) {
		this.num = new boolean[10];
		this.num[num-1] = true;
	}

}
