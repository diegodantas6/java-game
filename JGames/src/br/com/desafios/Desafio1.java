package br.com.desafios;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.util.Mensagem;
import br.com.util.Util;
import br.com.view.Menu;

public class Desafio1 extends JDialog {

	private static final long serialVersionUID = 1L;

	public Desafio1 () {
		this(null);
	}
	
	private Desafio1 (JFrame frame) {
		super(frame, true);
		init();
	}

	private static int pontos;

	private static Random random = new Random();

	private static JPanel panel;
	private static JButton button;
	private static JLabel tentativas;
	private static JLabel acertos;
	private static JLabel record;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int MIN = 70;
	private static final int MAX_X = WIDTH - MIN;
	private static final int MAX_Y = HEIGHT - MIN;

	private void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Desafio 1");
		setSize(WIDTH, HEIGHT);
		setResizable(false);

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);

		tentativas = new JLabel();
		tentativas.setBounds(10, 10, 100, 20);
		panel.add(tentativas);

		acertos = new JLabel("Acertos: " + pontos);
		acertos.setBounds(10, 30, 100, 20);
		panel.add(acertos);

		record = new JLabel();
		record.setBounds(10, 50, 100, 20);
		panel.add(record);

		button = new JButton("X");
		button.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				pontos++;
				acertos.setText("Acertos: " + pontos);
			}
		});

		panel.add(button);

		Util.dialogCenter(this);

		setContentPane(panel);
		
		Mensagem.showMessage("Obj: Apertar mais vezes no botao X.\nDica: Voce pode apertar mais de uma vez por aparicao.\nVoce esta pronto?");
		
		rodaDesafio();

		setVisible(true);
	}

	public void rodaDesafio() {
		record.setText("Record: BETA");
	
		for (int i = 20; i > 0; i--) {
			tentativas.setText("Tentativas: " + i);
			
			int x = random.nextInt(MAX_X - MIN + 1) + MIN;
			int y = random.nextInt(MAX_Y - MIN + 1) + MIN;
		
			button.setBounds(x, y, 40, 40);

			try {
				Thread.sleep(900);
			} catch (Exception e) {}
		}
		
		int n = JOptionPane.showConfirmDialog(this, "Acertos: " + pontos + "\nGostaria de tentar novamente " + Menu.getNome() + "?", "",	JOptionPane.YES_NO_OPTION);
		
		if (n == 1) {
			this.dispose();
		} else {
			pontos = 0;
			acertos.setText("Acertos: " + pontos);
			rodaDesafio();
		}
	}
}
