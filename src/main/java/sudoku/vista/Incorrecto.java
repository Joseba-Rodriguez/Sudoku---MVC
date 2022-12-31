package sudoku.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Incorrecto extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private ImageIcon imageF = new ImageIcon("src\\main\\resources\\image\\fail2.png");
	private static String directory="src\\main\\resources\\music\\fail.wav";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Incorrecto frame = new Incorrecto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Incorrecto() {
		setResizable(false);
		setBounds(100, 100, 625, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblNewLabel(), BorderLayout.CENTER);
		lblNewLabel.setText("");
		lblNewLabel.setIcon(imageF);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		MusicStuff musicObject = new MusicStuff();
		musicObject.playMusic(directory);
		
		this.setVisible(true);
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
		}
		return lblNewLabel;
	}
}