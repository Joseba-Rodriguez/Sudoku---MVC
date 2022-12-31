package sudoku.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextLayout;
import java.awt.Color;


public class Correcto extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtCorrecto;
	private ImageIcon imageF = new ImageIcon("src\\main\\resources\\image\\correcto.png");
	private static String directory="src\\main\\resources\\music\\victory.wav";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Correcto frame = new Correcto();
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
	public Correcto() {
		setResizable(false);
		setBounds(100, 100, 545, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblNewLabel(), BorderLayout.CENTER);
		lblNewLabel.setIcon(imageF);
		contentPane.add(getTxtCorrecto(), BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		MusicStuff musicObject = new MusicStuff();
		musicObject.playMusic(directory);
		
		
		
		this.setVisible(true);
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		}
		return lblNewLabel;
	}
	private JTextField getTxtCorrecto() {
		if (txtCorrecto == null) {
			txtCorrecto = new JTextField();
			txtCorrecto.setForeground(Color.yellow);
			txtCorrecto.setFont(new Font("Tahoma", Font.BOLD, 20));
			txtCorrecto.setHorizontalAlignment(SwingConstants.CENTER);
			txtCorrecto.setEditable(false);
			txtCorrecto.setText("\u00A1VICTORIA ROYALE!");
			
		
			txtCorrecto.setLayout(getLayout());
			
			txtCorrecto.setColumns(10);
		}
		return txtCorrecto;
	}
	
	
	
	class WindowEventHandler extends WindowAdapter {
		  public void windowClosing(WindowEvent evt) {
			    System.out.println("Call your method here");
			  }
			}
}