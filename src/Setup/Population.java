package Setup;

import java.io.IOException;
import java.util.Random;

import Draw.Escritor;

public class Population {
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
			parcial[i] = (double) dna[i].isEqual(target);
			if (parcial[i] == 0)
				System.out.println("Encontrou");
		}

		/*
		 * for (double d : parcial) { System.out.print(d + " "); System.out.println(); }
		 */

		return parcial;
	}

	public int[] bubbleSort(int[] arr) {
		int n = arr.length;
		int temp = 0;
		Cromossomo temp2 = new Cromossomo();
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
					temp2 = dna[j - 1];
					dna[j - 1] = dna[j];
					dna[j] = temp2;
				}

			}
		}
		return arr;
	}

	public void selection(Escritor escravao) {

		double[] fitness = evaluate();
		int[] fitnessInt = new int[fitness.length];
		for (int i = 0; i < fitnessInt.length; i++) {
			fitnessInt[i] = (int) fitness[i];
		}
		fitnessInt = bubbleSort(fitnessInt);

		int tamNewGen = 0;
		for (int i = 0; i < fitness.length; i++) {
			tamNewGen += fitnessInt[i];
		}
		try {
			escravao.escritor.append(geracao+","+ (tamNewGen / fitnessInt.length)+","+fitnessInt[0]+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(geracao+" "+ (tamNewGen / fitnessInt.length)+" "+fitnessInt[0]);
		Cromossomo[] dnaNextGen = new Cromossomo[tamNewGen];

		int ateagora = 0;
		for (int i = 0; i < fitnessInt.length; i++) {
			for (int j = ateagora; j < (ateagora + fitnessInt[fitnessInt.length - 1 - i]); j++) {
				dnaNextGen[j] = new Cromossomo(dna[i]);
			}

			ateagora += fitnessInt[fitnessInt.length - i - 1];
		}
		int mutacao = (int) ((tamanho * (getIndiceMutacao() / 100.0)));
		for (int i = 0; i < (tamanho - mutacao); i++) {
			dna[i] = dnaNextGen[gerador.nextInt(tamNewGen)];
		}
		Cromossomo mutante = new Cromossomo();
		for (int i = (tamanho - mutacao); i < dna.length; i++) {
			dna[i] = mutante.crossover(dnaNextGen[gerador.nextInt((int) tamNewGen)],
					dnaNextGen[gerador.nextInt((int) tamNewGen)]);
		}

		geracao++;

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
