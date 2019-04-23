package Setup;

import java.util.Random;

public class Cromossomo {
	private Gene[] genes;
	private Random gerador = new Random();

	public Cromossomo() {
		genes = new Gene[2];
		initCromossomo();
	}

	public Gene getGenes(int i) {
		return genes[i];
	}

	public Cromossomo(int a, int b) {
		genes = new Gene[2];
		genes[0] = new Gene(a);
		genes[1] = new Gene(b);
	}

	public Cromossomo(Cromossomo clone) {
		this(Integer.parseInt(clone.genes[0].getNum(), 2), Integer.parseInt(clone.genes[1].getNum(), 2));
	}

	public Cromossomo(String a, String b) {
		genes = new Gene[2];
		genes[0] = new Gene(a);
		genes[1] = new Gene(b);
	}

	private void initCromossomo() {

		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Gene(gerador.nextInt(1024));
		}
	}

	public String toString() {
		return genes[0].getNum() + " " + genes[1].getNum();
	}

	public double isEqual() {
		double x = -5.0+Integer.parseInt(genes[0].getNum(), 2)*0.00978;
		double y = -5.0+Integer.parseInt(genes[1].getNum(), 2)*0.00978;
		double contaCabulosa = 20.0 + (x*x)+ (y*y)
				- 10.0 * (Math.cos(2.0 * Math.PI * x)
				+ Math.cos(2.0 * Math.PI * y));
		return contaCabulosa;
	}

}
