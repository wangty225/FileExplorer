package model;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI.NormalColor;
import org.jb2011.lnf.beautyeye.ch6_textcoms.BETextFieldUI;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NavigatePanel extends JPanel {
	/**
	 * @author wty
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel panel = this;
	public JButton preBtn = new JButton("上一页");
	public JButton nextBtn = new JButton("下一页");
	public JTextField pathTextField = new JTextField();
	public JButton goBtn = new JButton("Go");
	public JTextField searchField = new JTextField();
	public JButton searchBtn = new JButton("Search");
	/**
	 * Create the panel.
	 */
	public NavigatePanel() {

		setLayout(new GridLayout(1, 1, 0, 0));
		
		this.setBackground(Color.white);
		panel.setOpaque(false);
		panel.setLayout(new FlowLayout());

		
		preBtn.setBounds(10, 21, 70, 28);
		preBtn.setUI(new BEButtonUI().setNormalColor(NormalColor.lightBlue));
		preBtn.setOpaque(false);
		panel.add(preBtn);
		
		nextBtn.setBounds(85, 21, 70, 28);
		nextBtn.setUI(new BEButtonUI().setNormalColor(NormalColor.lightBlue));
		panel.add(nextBtn);
		
		pathTextField.setFont(new Font("幼圆", Font.PLAIN, 16));
		pathTextField.setColumns(50);
		pathTextField.setUI(new BETextFieldUI());
		panel.add(pathTextField);
		

		goBtn.setBounds(717, 21, 56, 28);
		goBtn.setUI(new BEButtonUI().setNormalColor(NormalColor.green));
		panel.add(goBtn);
		

		searchField = new JTextField();
		searchField.setFont(new Font("幼圆", Font.PLAIN, 16));
		searchField.setColumns(20);
		searchField.setUI(new BETextFieldUI());
		panel.add(searchField);
		

		searchBtn.setBounds(717, 21, 56, 28);
		ImageIcon findIco=new ImageIcon("images/icons/search.png");
		findIco.setImage(findIco.getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT));
		searchBtn.setIcon(findIco);
		searchBtn.setUI(new BEButtonUI().setNormalColor(NormalColor.red));
		panel.add(searchBtn);
	}
	
//	public static void main(String [] args) {
//		JFrame jf = new JFrame();
//		jf.setLayout(new BorderLayout());
//		JPanel jp = new JPanel();
//		jp.add(new NavigatePanel());
//		jp.setPreferredSize(new Dimension(600,40));
//		jf.add(jp, BorderLayout.NORTH);
//		jf.setSize(900, 650);
//		jf.setLayout(new BorderLayout());
//		jf.setVisible(true);
//	}
//	
}
