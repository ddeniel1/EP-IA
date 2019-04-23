package Setup;

import java.io.IOException;
import java.util.Random;

import Draw.Escritor;

public class Population {
	private boolean debug = false;
	private double indiceMutacao = 0;
	private int tamanho;
	private Cromossomo[] dna;
	private int target = 0;
	public int geracao = 0;
	private Random gerador = new Random();

	public Population(int tam) {
		this.tamanho = tam;
		dna = new Cromossomo[this.tamanho];
		initDNA();
	}

	public Population(Cromossomo[] newGen) {
		this.tamanho = newGen.length;
		dna = newGen;
	}

	private void initDNA() {
		for (int i = 0; i < dna.length; i++) {
			dna[i] = new Cromossomo();
		}
	}

	public double[] evaluate() {
		double[] parcial = new double[dna.length];
		for (int i = 0; i < parcial.length; i++) {
			parcial[i] = dna[i].isEqual();
			if (parcial[i] == target)
				System.out.println("Encontrou");
		}

		/*
		 * for (double d : parcial) { System.out.print(d + " ");
		 * System.out.println(); }
		 */

		return parcial;
	}

	public double[] bubbleSort(double[] fitness) {
		int n = fitness.length;
		double temp = 0;
		Cromossomo temp2 = new Cromossomo();
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (fitness[j - 1] > fitness[j]) {
					temp = fitness[j - 1];
					fitness[j - 1] = fitness[j];
					fitness[j] = temp;
					temp2 = dna[j - 1];
					dna[j - 1] = dna[j];
					dna[j] = temp2;
				}

			}
		}
		return fitness;
	}

	public void selection(Escritor escravao) {

		double[] fitness = evaluate();

		fitness = bubbleSort(fitness);

		double tamNewGen = 0;
		int contador = 0;
		for (int i = 0; i < fitness.length; i++) {
			tamNewGen += fitness[i];
			contador += i + 1;
		}
		try {
			escravao.escritor.append(geracao + "," + (tamNewGen / fitness.length) + "," + fitness[0] + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (debug)
			System.out.println(geracao + " " + (tamNewGen / fitness.length) + " " + fitness[0]);

		Cromossomo[] newGen = new Cromossomo[contador];

		int ateagora = 0;

		for (int i = 0; i < dna.length; i++) {
			int k = 0;
			for (int j = (ateagora == -1 ? 0 : ateagora); j < (ateagora + dna.length - i); j++) {
				newGen[j] = new Cromossomo(dna[i]);
				k++;
			}
			ateagora += k;

		}

		// CrossOver (para todos os individuos?)
		for (int i = 0; i < dna.length; i++) {
			Cromossomo pai = newGen[gerador.nextInt(contador)];
			Cromossomo mae = newGen[gerador.nextInt(contador)];
			//System.out.println(mae.toString() + "  " + pai.toString());

			if (gerador.nextDouble() <= 0.8) {
				int ponto = gerador.nextInt(10);
				String filhoStringX = (ponto == 0 ? "" : pai.getGenes(0).getNum().substring(0, ponto));
				filhoStringX = filhoStringX
						+ (ponto == 9 ? mae.getGenes(0).getNum().charAt(9) : mae.getGenes(0).getNum().substring(ponto));

				String filhoStringY = (ponto == 0 ? "" : pai.getGenes(1).getNum().substring(0, ponto));
				filhoStringY = filhoStringY
						+ (ponto == 9 ? mae.getGenes(1).getNum().charAt(9) : mae.getGenes(1).getNum().substring(ponto));

				dna[i] = mutacao(new Cromossomo(filhoStringX, filhoStringY));
			} else
				dna[i] = newGen[gerador.nextInt(contador)];
		}

		geracao++;

	}

	private Cromossomo mutacao(Cromossomo crossover) {

		if (gerador.nextDouble() <= (getIndiceMutacao() / 100.0)) {
			int mutacoes = gerador.nextInt(10) + 1;
			int ponto = 0;
			String x = crossover.getGenes(0).getNum();
			String xNovo = "" + x;
			//System.out.println(x + " " + xNovo + " X ");

			for (int i = 0; i < mutacoes; i++) {
				ponto = gerador.nextInt(10);
				xNovo = (ponto == 0 ? "" : xNovo.substring(0, ponto)) + (xNovo.charAt(ponto) == '1' ? "0" : "1")
						+ (ponto == 9 ? "" : xNovo.substring(ponto + 1));
			}
			String y = crossover.getGenes(1).getNum();
			String yNovo = "" + y;
			//System.out.println(y + " " + yNovo + " Y ");

			mutacoes = gerador.nextInt(10) + 1;
			for (int i = 0; i < mutacoes; i++) {
				ponto = gerador.nextInt(10);
				yNovo = (ponto == 0 ? "" : yNovo.substring(0, ponto)) + (yNovo.charAt(ponto) == '1' ? "0" : "1")
						+ (ponto == 9 ? "" : yNovo.substring(ponto + 1));
			}
			crossover = new Cromossomo(xNovo, yNovo);

		}

		return crossover;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < dna.length; i++) {
			result = result.append("Cromossomo " + (i + 1) + ": " + dna[i].toString() + "\n");
		}
		return result.toString();
	}

	public double getIndiceMutacao() {
		return indiceMutacao;
	}

	public void setIndiceMutacao(double indiceMutacao) {
		this.indiceMutacao = indiceMutacao;
	}
}
