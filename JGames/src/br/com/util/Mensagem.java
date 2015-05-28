package br.com.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Mensagem {
	
	public static void showMessage(String mensagem) {
		showMessage(null, mensagem);
	}

	public static void showMessage(Component component, String mensagem) {
		JOptionPane.showMessageDialog(component, mensagem, Constantes.NOME, JOptionPane.INFORMATION_MESSAGE);
	}
}
