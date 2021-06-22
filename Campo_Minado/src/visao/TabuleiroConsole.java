package visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import excecao.ExplosaoException;
import excecao.SairException;
import modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		executarJogo();	
	}
	private void executarJogo() {
		try {
			boolean continuar = true;
			while (continuar) { 
				cicloDoJogo();
					System.out.println("BORA JOGAR DENOVO?? (S/n) ");
					String resposta = entrada.nextLine();
					if("n".equalsIgnoreCase(resposta)){
					continuar = false;
			}else {
				tabuleiro.reset();
			}
		}
	}catch (SairException e) {
		System.out.println("VALEW POR JOGAR...");
	}finally {
		entrada.close();
		
	}
	}
	private void cicloDoJogo() {
		try {
			
		while (!tabuleiro.objetivoAlcancado()) {
			System.out.println(tabuleiro);	
			
			String digitado = capturarValorDigitado("DIGITE ( Y , X ):");
			Iterator<Integer> xy = Arrays.stream(digitado.split(","))
			.map(e -> Integer.parseInt(e.trim())).iterator();
			digitado = capturarValorDigitado ("1 PARA ABRIR OU 2 PARA DESMARCAR");
			if("1".equals(digitado)) {
				tabuleiro.abrir(xy.next(), xy.next());
				
			}else if("2".equals(digitado)) {
				tabuleiro.marcar(xy.next(), xy.next());
			}
		}
		System.out.println("ARREBENTOU...");
		}catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("SE FUDEU...");
		}
	}
	private String capturarValorDigitado (String texto){
		System.out.print(texto);
		String digitado = entrada.nextLine();
		if("Sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
			
		}
		return digitado;
	}
}
