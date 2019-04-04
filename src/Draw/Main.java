package Draw;

import java.io.IOException;
import java.util.Scanner;

import Setup.Population;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Digite a populacao inicial: ");
		int pop = scan.nextInt();
		Population p = new Population(pop);
		System.out.print("Digite a taxa de mutacao: ");
		double mut = scan.nextDouble();
		p.setIndiceMutacao(mut);
		System.out.println("Digite o numero de geracoes: ");
		int geracoes = scan.nextInt();
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

		scan.close();
		System.out.println(p.toString());
	}

}
