package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AboutWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutWindow frame = new AboutWindow();
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
	public AboutWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("软件名称：");
		nameLabel.setBounds(22, 20, 93, 29);
		contentPane.add(nameLabel);
		
		JLabel label_1 = new JLabel("版本：");
		label_1.setBounds(22, 47, 93, 34);
		contentPane.add(label_1);
		
		JLabel lblReleaseDate = new JLabel("release date:");
		lblReleaseDate.setBounds(22, 78, 93, 29);
		contentPane.add(lblReleaseDate);
		
		JLabel label_2 = new JLabel("作者：");
		label_2.setBounds(22, 108, 93, 27);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("详细：");
		label_3.setBounds(22, 145, 58, 15);
		contentPane.add(label_3);
		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
//		textArea.setBounds(22, 172, 404, 122);
		textArea.setText("    本系统是一个使用java编程语言，通过 swing与美化包beautyeye的结合构建GUI，基于MVC架构，的文件资源管理器。通过近两星期的努力，该系统已基本具备对文件的基本操作，包括复制，剪切，删除， 查找，查看属性等相关信息等等。MVC的架构，加上多线程对耗资源时间的优化。该项目有比较好的易读性和扩展性。一些功能还可以继续完善。\"\r\n");
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(32, 170, 409, 129);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setLineWrap(true);        //激活自动换行功能 
		textArea.setWrapStyleWord(true);            // 激活断行不断字功能
		contentPane.add(scrollPane);
		
		JLabel lblNull = new JLabel("FileExplorer");
		lblNull.setBounds(172, 20, 254, 29);
		contentPane.add(lblNull);
		
		JLabel lblVersion = new JLabel("V1.0");
		lblVersion.setBounds(172, 47, 254, 34);
		contentPane.add(lblVersion);
		
		JLabel lblDate = new JLabel("2019-07-04");
		lblDate.setBounds(172, 78, 254, 29);
		contentPane.add(lblDate);
		
		JLabel lblAuthor = new JLabel("Njtech Uni. CST-Software 1702 WTY");
		lblAuthor.setBounds(172, 108, 254, 27);
		contentPane.add(lblAuthor);
		
		setTitle("About the System");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
