package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI.NormalColor;

public class PropertiesWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	public static PropertiesWindow _instance = null;
	public String path;
	public JTextField NameField, Location;
	public JLabel ImageLabel, SizeLabel, Size, LocationLabel, CreateTimeLabel, CreateTime, ModifyTimeLabel, ModifyTime,
			AccessTimeLabel, AccessTime, TypeLabel, Type, IncludeLabel, Include, FileSysLabel, FileSys, UsedSizeLabel,
			UsedSize, AvaiSizeLabel, AvaiSize, TotalSizeLabel, TotalSize;
	public JPanel HeadPanel, MidPanel, BotPanel;
	public JButton EnsureBtn;
	public double Used, Available, Total;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PropertiesWindow frame = new PropertiesWindow();
					frame.setVisible(true);
//					frame.Init1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void Init1() {// 文件属性界面初始化
		HeadPanel = new JPanel();
		HeadPanel.setBounds(10, 10, 330, 100);
		HeadPanel.setOpaque(false);
		HeadPanel.setLayout(null);
//		ImageLabel = new JLabel();
//		ImageLabel.setBounds(50, 30, 40, 40);
		NameField = new JTextField();
		NameField.setText("null");
		NameField.setFont(new Font("幼圆", Font.PLAIN, 18));
		NameField.setEditable(false);
		NameField.setOpaque(false);
		NameField.setBounds(50, 40, 240, 30);
//		HeadPanel.add(ImageLabel);
		HeadPanel.add(NameField);
		HeadPanel.setOpaque(true);
		HeadPanel.setBackground(Color.white);
		HeadPanel.setBorder(BorderFactory.createTitledBorder("文件名称"));
		getContentPane().add(HeadPanel);

		// 中间的Panel
		MidPanel = new JPanel();
		MidPanel.setOpaque(false);
		MidPanel.setBounds(10, 110, 330, 190);
		MidPanel.setLayout(null);
		MidPanel.setBorder(BorderFactory.createTitledBorder("大小及位置"));
		
		TypeLabel = new JLabel();
		TypeLabel.setLocation(10, 30);
		TypeLabel.setSize(50, 30);
		TypeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		TypeLabel.setOpaque(false);
		TypeLabel.setText("类型:");
		
		Type = new JLabel();
		Type.setLocation(60, 30);
		Type.setSize(300, 30);
		Type.setFont(new Font("Serif", Font.PLAIN, 18));
		Type.setOpaque(false);
		Type.setText("文件夹");
		
		SizeLabel = new JLabel();
		SizeLabel.setLocation(10, 76);
		SizeLabel.setSize(50, 30);
		SizeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		SizeLabel.setOpaque(false);
		SizeLabel.setText("大小:");
		Size = new JLabel();
		Size.setLocation(60, 76);
		Size.setSize(300, 30);
		Size.setOpaque(false);
		Size.setFont(new Font("Serif", Font.PLAIN, 18));
		Size.setText("0");

		LocationLabel = new JLabel("位置：");
		LocationLabel.setBounds(10, 130, 60, 25);
		LocationLabel.setOpaque(false);
		LocationLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		Location = new JTextField();
		Location.setSize(260, 20);
		Location.setLocation(60, 130);
		Location.setEditable(false);
		Location.setOpaque(false);
		Location.setText("C:\\Windows\\System32\\asd\\sdsd");
		Location.setFont(new Font("Serif", Font.PLAIN, 16));
		MidPanel.add(TypeLabel);
		MidPanel.add(Type);
		MidPanel.add(SizeLabel);
		MidPanel.add(Size);
		MidPanel.add(LocationLabel);
		MidPanel.add(Location);
		MidPanel.setOpaque(true);
		MidPanel.setBackground(Color.white);
		getContentPane().add(MidPanel);

		// 底部的Panel
		BotPanel = new JPanel();
		BotPanel.setBounds(10, 300, 330, 200);
		BotPanel.setLayout(null);
		BotPanel.setBorder(BorderFactory.createTitledBorder("相关时间"));
		
		CreateTimeLabel = new JLabel("创建时间：");
		CreateTimeLabel.setBounds(10, 50, 90, 20);
		CreateTimeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		CreateTime = new JLabel("‎2015‎年‎10‎月‎30‎日,‏‎6:15:27");
		CreateTime.setBounds(100, 50, 200, 20);
		CreateTime.setFont(new Font("Serif", Font.PLAIN, 16));

		ModifyTimeLabel = new JLabel("修改时间：");
		ModifyTimeLabel.setBounds(10, 100, 90, 20);
		ModifyTimeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		ModifyTime = new JLabel("‎2015‎年‎10‎月‎30‎日,‏‎6:15:27");
		ModifyTime.setBounds(100, 100, 200, 20);
		ModifyTime.setFont(new Font("Serif", Font.PLAIN, 16));

		AccessTimeLabel = new JLabel("访问时间：");
		AccessTimeLabel.setBounds(10, 150, 90, 20);
		AccessTimeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		AccessTime = new JLabel("‎2015‎年‎10‎月‎30‎日,‏‎6:15:27");
		AccessTime.setBounds(100, 150, 200, 20);
		AccessTime.setFont(new Font("Serif", Font.PLAIN, 16));

		BotPanel.add(CreateTimeLabel);  	
		BotPanel.add(CreateTime);	    	
		BotPanel.add(ModifyTimeLabel);  	
		BotPanel.add(ModifyTime);			
		BotPanel.add(AccessTimeLabel);		
		BotPanel.add(AccessTime);			
		BotPanel.setOpaque(true);
		BotPanel.setBackground(Color.white);
		getContentPane().add(BotPanel);

		// 确定按钮
		EnsureBtn = new JButton("确定");
		EnsureBtn.setBounds(240, 510, 90, 30);
		EnsureBtn.setUI(new BEButtonUI().setNormalColor(NormalColor.green));
		EnsureBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dispose();
			}
		});
		getContentPane().add(EnsureBtn);
	}
	
	/**
	 * Create the frame.
	 */
	public PropertiesWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.setTitle("文件属性");
		this.setBounds(500, 500, 366, 590);
		this.getContentPane().setLayout(null);
		Init1();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

}
