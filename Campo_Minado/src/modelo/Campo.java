package modelo;



import java.util.ArrayList;
import java.util.List;


import excecao.ExplosaoException;

public class Campo {

	
	private final int linha;
	private final int coluna;
	private boolean aberto = false;
	private boolean mina = false;
	private boolean marcado = false;
	private List <Campo> vizinhos = new ArrayList<>();
	
    Campo (int linha, int coluna) {
	  this.linha = linha;
	  this.coluna = coluna;
   }
    
        boolean adicionarVizinho (Campo vizinho){
    	
    	boolean linhadiferente = linha != vizinho.linha;	
    	boolean colunadiferente = coluna != vizinho.coluna;
    	boolean diagonal = linhadiferente && colunadiferente;
    	int deltalinha = Math.abs(linha - vizinho.linha);
    	int deltacoluna = Math.abs(coluna - vizinho.coluna);
    	int deltasoma = deltacoluna + deltalinha;
    	
    	if (deltasoma == 1 && !diagonal) {
    		vizinhos.add(vizinho);	
    		return true;
    	}else if(deltasoma == 2 && diagonal) {
    		vizinhos.add(vizinho);
    		return true;
    	}else {
    		return false;
    	}
  
   }
    void AlternarMarcacao() {
    	
    	if(!aberto) {
    		marcado =!marcado;
    		
    	}
    }
    	boolean abrir() {
    		if(!aberto & !marcado) {
    			aberto = true;
    			if (mina) {
    				throw new ExplosaoException();
    				
    			}
    			if (VizinhoSeguro()) {
    				vizinhos.forEach(v -> v.abrir());	
    			}
    			return true;
    		}else {
    	     return false;
    	     }
    	}
    	
   boolean VizinhoSeguro() {
	   return vizinhos.stream().noneMatch(v -> v.mina);   
   }
   
   void minar () {
	   mina = true;
	   
   }
   
   public boolean isMinado() {
	   return mina;
	   
   }
		   
   public boolean isMarcado() {
	   
	   return marcado;
	   
   }
   public boolean IsAberto() {
	   return aberto;
	   
   }
   void  setAberto(boolean aberto) {
	   this.aberto = aberto;
	   
   }
   public boolean IsFechado() {
	   return !aberto;
	   
   }
   
  public int getLinha() {  
	  return linha;
	  
  }
  public int getColuna() {  
	  return coluna;
	  
  }
   boolean ObjetivoAlcancado() {
	   boolean desvendado = !mina && aberto;
	   boolean protegito = mina && marcado;
	   return desvendado || protegito;
   }
	  long minasNaVizinhanca() {
		  return vizinhos.stream().filter(v -> v.mina).count();
		  
		  
	  }
	   void Reiniciar () {
		   aberto = false;
		   mina = false;
		   marcado = false;   
	   }
   public String toString() {
	   
	   if(marcado) {
		   return"X";
		    }else if (aberto && mina) {
		    	return "*";	
		    }else if(aberto && minasNaVizinhanca()> 0) {
		    	return Long.toString(minasNaVizinhanca());	
		    }else if(aberto) {
		    	return "";  	
		    }else{
		    	return"?";	
		    }
	   
   }
   
}

