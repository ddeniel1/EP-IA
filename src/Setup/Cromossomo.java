package Setup;

import java.util.Random;

public class Cromossomo {
	private Gene[] genes;
	private Random gerador = new Random();
	public Cromossomo() {
		genes = new Gene[2];
		initCromossomo();
	}
	public Cromossomo(int a, int b){
		genes = new Gene[2];
		genes[0] = new Gene(a);
		genes[1] = new Gene(b);
	}
	public Cromossomo(Cromossomo clone){
		this(clone.genes[0].getNum(),clone.genes[1].getNum());
	}

	private void initCromossomo() {
		
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Gene(gerador.nextInt(11));
		}
	}

	public String toString() {
		return genes[0].getNum() + " " + genes[1].getNum();
	}

	public double isEqual(int target) {
		double contaCabulosa = 20+(Math.pow(genes[0].getNum(), 2))+(Math.pow(genes[1].getNum(), 2))-10*(Math.cos(2*Math.PI*genes[0].getNum()))+(Math.cos(2*Math.PI*genes[1].getNum()));
		return contaCabulosa;
	}
	
	public Cromossomo crossover(Cromossomo pai, Cromossomo mae){
		int aleatorio = gerador.nextInt(3);

		Cromossomo filho = new Cromossomo();
		switch (aleatorio) {
		case 0:
			filho = new Cromossomo(pai.genes[0].getNum(),mae.genes[1].getNum());
			break;
		case 1:
			filho = new Cromossomo(pai.genes[1].getNum(),mae.genes[0].getNum());
			break;
		case 2:
			filho = new Cromossomo(mae.genes[0].getNum(),pai.genes[1].getNum());
			break;
		case 3:
			filho = new Cromossomo(mae.genes[1].getNum(),pai.genes[0].getNum());
			break;
		}
		
		
		return filho;
	}

}
