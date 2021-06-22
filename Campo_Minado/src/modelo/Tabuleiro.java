package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import excecao.ExplosaoException;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;
	private final List<Campo> campo = new ArrayList<>();
	
	public Tabuleiro(int linhas , int colunas ,int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		assossiarOsvizinhos();
		sortearMinas();
		}
	public void abrir(int linha,int coluna) {
		try {campo.parallelStream()
			.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(c -> c.AlternarMarcacao());
				
		}catch(ExplosaoException e) {
			campo.forEach(c -> c.setAberto(true));
			throw e;
			
		}
	}
	public void marcar(int linha,int coluna) {
		campo.parallelStream()
		.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.AlternarMarcacao());
	}
	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> mina = c -> c.isMinado();
		do {
			int x = (int) (Math.random() * campo.size());
			campo.get(x).minar();
			minasArmadas= campo.stream().filter(mina).count();
		}while(minasArmadas < minas);	
	}
	public boolean objetivoAlcancado() {
		return campo.stream().allMatch(c -> c.ObjetivoAlcancado());	
	}	  
	public void reset() {
		campo.stream().forEach(c->c.Reiniciar());
		sortearMinas();
	}
	public String toString() {
		StringBuilder y = new StringBuilder();
		int i = 0;
		for (int linha = 0; linha < linhas ; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				y.append(" ");
				y.append(campo.get(i));
				y.append(" ");
				i++;
			}
			y.append("\n");
		}
			
		
		return y.toString();	
	}
	private void assossiarOsvizinhos() {
		for(Campo c1:campo) {
			for(Campo c2: campo) {
				c1.adicionarVizinho(c2);	
			}
		}	
	}
    private void gerarCampos() {
		for(int linha = 0; linha < linhas; linha++) {
			for(int coluna  = 0; coluna < colunas; coluna++) {
				campo.add(new Campo(linha , coluna));
				
				
			}
			
			
		}
		
		
	}
	
    }
    
    
    

