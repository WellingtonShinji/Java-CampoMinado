package modelo;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excecao.ExplosaoException;

public class CampoTeste {

     private Campo campo;
     
     @BeforeEach
     void iniciarCampo() {
    	 campo = new Campo(3 , 3);	 
     }

        @Test
     void testevizinhoDistancia1Esquerda() {
    	 Campo vizinho = new Campo(3,2);
    	 boolean resultado = campo.adicionarVizinho(vizinho);
    	 assertTrue(resultado);
        }
        @Test
    void testevizinhoDistancia1Direita() {
        	 Campo vizinho = new Campo(3,4);
        	 boolean resultado = campo.adicionarVizinho(vizinho);
        	 assertTrue(resultado);
        }
    	@Test
    void testevizinhoDistancia1Acima() { 
   	       Campo vizinho = new Campo(2,3);
   	       boolean resultado = campo.adicionarVizinho(vizinho);
   	       assertTrue(resultado);
    	}
   	   @Test      
    void testevizinhoDistancia1Abaixo() { 
	       Campo vizinho = new Campo(2,2);
	       boolean resultado = campo.adicionarVizinho(vizinho);
	       assertTrue(resultado);
      }    
   	   @Test      
   	   void testevizinhoDistancia2() { 
   		   Campo vizinho = new Campo(4,3);
   		   boolean resultado = campo.adicionarVizinho(vizinho);
   		   assertTrue(resultado);
   	   }    
   	@Test      
    void testeNaoVisinho() { 
	       Campo vizinho = new Campo(1,1);
	       boolean resultado = campo.adicionarVizinho(vizinho);
	       assertFalse(resultado);
      } 
   	@Test
   	void testeValorPadraoAtributoMarcado() {
   		assertFalse(campo.isMarcado());
   		
   	}
   	@Test
   	void testeAlternarMarcacao() {
   		campo.AlternarMarcacao();
   		assertTrue(campo.isMarcado());
   		
   	}
   	@Test
   	void testeAlternarMarcacaoDuasChamadas() {
   		campo.AlternarMarcacao();
   		campo.AlternarMarcacao();
   		assertFalse(campo.isMarcado());
   		
   	}
   	@Test
   	void AbrirCampoSemMinaNaoMarcado() {
   		assertTrue(campo.abrir());
   		
   	}
   	@Test
   	void AbrirCampoSemMinaMarcado() {
   		campo.AlternarMarcacao();
   		assertFalse(campo.abrir());
   		
   	}
   	@Test
   	void AbrirCampoMinaMarcado() {
   		campo.AlternarMarcacao();
   		campo.minar();
   		assertFalse(campo.abrir());
   		
   	}
   	@Test
   	void AbrirCampoMinanaoMarcado() {
   		campo.AlternarMarcacao();
   		campo.minar();
   		assertFalse(campo.abrir());
   		
   	}
   	@Test
   	void AbrirComvizinho() {
   		Campo campo11 = new Campo(1,1);
   		Campo campo22 = new Campo(2,2);
   		campo22.adicionarVizinho(campo11);
   		campo.adicionarVizinho(campo22);
   		campo.abrir();
   		assertTrue(campo22.IsAberto()&& campo11.IsAberto());
   		
   	}
   	@Test
   	void AbrirComvizinho2() {
   		Campo campo11 = new Campo(1,1);
   		Campo campo12 = new Campo(1,1);
   		campo12.minar();
   		
   		Campo campo22 = new Campo(2,2);
   		campo22.adicionarVizinho(campo11);
   		campo22.adicionarVizinho(campo12);
   		campo.adicionarVizinho(campo22);
   		campo.abrir();
   		assertTrue(campo22.IsAberto()&& campo11.IsFechado());
   		
   	}
}

	
	

