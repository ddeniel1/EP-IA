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
		
		for (int k = 0; k < 1; k++) {
			
			Scanner scan = new Scanner(System.in);
			int pop = (int)Math.pow(10, k);
			Population p = new Population(pop);
			double mut = 100;
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
