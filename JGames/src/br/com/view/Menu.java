package br.com.view;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.com.desafios.Desafio1;
import br.com.util.Constantes;
import br.com.util.Mensagem;

public class Menu {

	private static String nome;
	
	public static String getNome() {
		return nome;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

			UIManager.put("swing.boldMetal", Boolean.FALSE);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					login();
					
					createMenu();
				}
			});
		} catch (Exception e) {
			Mensagem.showMessage("SO não suportado!");
			System.exit(0);
		}
	}
	
	private static void login() {
		while (true) {
			nome = JOptionPane.showInputDialog("Login: ");
			
			if (nome == null) {
				System.exit(0);
			} else if (nome.trim().isEmpty()) {
				continue;
			} else {
				break;
			}
		}
	}

	private static void createMenu() {
		if (!SystemTray.isSupported()) {
			return;
		}

		final TrayIcon trayIcon = new TrayIcon(createImage("img/AvistaTray.png", "Logo"));
		final SystemTray tray = SystemTray.getSystemTray();

		final PopupMenu popup = new PopupMenu();
		final MenuItem minhaPontuacao = new MenuItem("Minha Pontuacao");
		final MenuItem recordes = new MenuItem("Recordes");
		final MenuItem versao = new MenuItem("Versao");
		final MenuItem sair = new MenuItem("Sair");
		final MenuItem Logoff = new MenuItem("Logoff");
		final MenuItem desafio1 = new MenuItem("Desafio 1");
		final MenuItem desafio2 = new MenuItem("Desafio 2");

		popup.add(desafio1);
		popup.add(desafio2);
		popup.addSeparator();
		popup.add(minhaPontuacao);
		popup.add(recordes);
		popup.addSeparator();
		popup.add(versao);
		popup.addSeparator();
		popup.add(Logoff);
		popup.add(sair);

		trayIcon.setPopupMenu(popup);
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip(Constantes.NOME);

		try {
			tray.add(trayIcon);
		} catch (Exception e) {
			return;
		}

		versao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popup.setEnabled(false);
				Mensagem.showMessage("Versão: ".concat(Constantes.VERSAO));
				popup.setEnabled(true);
			}
		});

		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});
		
		Logoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popup.setEnabled(false);
				nome = null;
				login();
				popup.setEnabled(true);
			}
		});

		minhaPontuacao.setEnabled(false);
		
		recordes.setEnabled(false);
		
		desafio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popup.setEnabled(false);
				
				Desafio1 d1 = new Desafio1();
				
				popup.setEnabled(true);
			}
		});
		
		desafio2.setEnabled(false);
	}

	private static Image createImage(String path, String description) {
		URL imageURL = Menu.class.getResource(path);

		if (imageURL == null) {
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}
}
