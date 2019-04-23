package Draw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor {

	public File resumo;
	public FileWriter escritor;
	public BufferedWriter wr;
	private int maxGer;

	public Escritor(int populacao, double mutacao, int geracoes, int exec) throws IOException {
		maxGer = geracoes;

		String resumu = "G:/Meu Drive/Matérias 5º Semestre/IA/EP/res/" + populacao + "_pop_" + (int) mutacao + "_mut_"
				+ geracoes + "_ger" + "/" + exec + ".csv";
		try {
			new File("G:/Meu Drive/Matérias 5º Semestre/IA/EP/res/" + populacao + "_pop_" + (int) mutacao + "_mut_"
					+ geracoes + "_ger" + "/").mkdir();
			resumo = new File(resumu);
			escritor = new FileWriter(resumu);

			escritor.append("ger,avgFit,bestFit\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void escrever(int geracao, int i, int j) {

		try {
			escritor.append(geracao + "," + i + "," + j + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void encerra() throws IOException {

		escritor.flush();
		escritor.close();
	}

	public void resumo() {
		String res = resumo.toString().substring(0, resumo.toString().indexOf("0.csv"));
		File listaArq[] = (new File(res)).listFiles();
		/*for (File file : listaArq) {
			System.out.println(file.toString());
		}*/
		int geracoes = maxGer;
		double[] avgFit = new double[geracoes];
		double[] bestFit = new double[geracoes];

		for (int i = 0; i < listaArq.length - 1; i++) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(listaArq[i].toString()));
				reader.readLine();
				int j = 0;
				while (reader.ready()) {
					String s = reader.readLine();
					//System.out.println(s);
					s = s.substring(s.indexOf(",")+1);
				//	System.out.println(s);
					avgFit[j] += Double.parseDouble(s.substring(0, s.indexOf(",")));
					//System.out.println(avgFit[j]+" no j: "+j);
					s = s.substring(s.indexOf(",")+1);
					//System.out.println(s);
					bestFit[j] += Double.parseDouble(s.substring(0));
					j++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		for (int i = 0; i < geracoes; i++) {
			avgFit[i] = avgFit[i] / 10.0;
			bestFit[i] = bestFit[i] / 10.0;
			try {
				escritor.append(i+1 + "," + avgFit[i] + "," + bestFit[i]+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			encerra();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
