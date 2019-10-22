package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;


public class MessageWindow extends JFrame {
	/**
	 * @author WTY
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel info1 ;
	public JLabel info2 = new JLabel(" FROM: ");
	public JLabel info3 = new JLabel(" TO:   ");
	public JProgressBar progressBar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(
			new Runnable() {
				public void run() {
					try {
						MessageWindow frame = new MessageWindow("(复制)");
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.run();
//		);		
	}

	/**
	 * Create the frame.
	 */
	public MessageWindow(String option) {
		info1 = new JLabel(" 正在" + option + "文件...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 226);
		
		
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new GridLayout(4, 1));
		
		info1 = new JLabel(" 正在"+ option +"文件...   已完成  "+"0%");
		info1.setFont(new Font("幼圆", Font.PLAIN, 18));
		rootPanel.add(info1);
		
		progressBar = new JProgressBar();
		progressBar.setValue(20);
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setFont(new Font("幼圆", Font.PLAIN, 16));
		progressBar.setPreferredSize(new Dimension(320, 20));
		progressBar.setEnabled(false);
		progressBar.setOpaque(false);
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		jp.add(progressBar);
		rootPanel.add(jp);
		
		info2.setFont(new Font("幼圆", Font.PLAIN, 14));
		info3.setFont(new Font("幼圆", Font.PLAIN, 14));
		rootPanel.add(info2);
		rootPanel.add(info3);
		
		rootPanel.setOpaque(true);
		setTitle("正在"+ option +"文件");
		setResizable(false);
		rootPanel.setBackground(Color.white);
		add(rootPanel);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
