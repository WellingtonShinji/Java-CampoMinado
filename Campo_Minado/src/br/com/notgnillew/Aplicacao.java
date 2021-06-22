package br.com.notgnillew;

import modelo.Tabuleiro;
import visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(10,10,10);
		new TabuleiroConsole(tabuleiro);
	
		}
		
	}

