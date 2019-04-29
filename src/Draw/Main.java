package Draw;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import Setup.Population;

public class Main {
	private static Random gerador = new Random();
	public static void main(String[] args) throws IOException {
		int MAX_POP_INICIAL = 1000;
		double MAX_MUTACAO = 2;
		double[] muts = {100, 10, 1, 0.1, 0.01, 0.001, 20, 2, 0.2, 0.02, 0.002};
		
		for (int k = 0; k < muts.length; k++) {
			
			Scanner scan = new Scanner(System.in);
			int pop = 100;
			Population p = new Population(pop);
			double mut = muts[k];
			p.setIndiceMutacao(mut);
			int geracoes = 20;
			Escritor escravao;
			for (int i = 0; i < 10; i++) {
				p = new Population(pop);
				p.setIndiceMutacao(mut);
				escravao = new Escritor(pop, p.getIndiceMutacao(), geracoes, i + 1);
				for (int j = 0; j < geracoes; j++) {
					p.selection(escravao);
					p.evaluate();
				}
				escravao.encerra();
			}
			escravao = new Escritor(pop, p.getIndiceMutacao(), geracoes, 0);
			escravao.resumo();

			scan.close();
		}
		System.out.println("FIM");
	}

}
