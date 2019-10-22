package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;


import javax.swing.ImageIcon;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


import org.jb2011.lnf.beautyeye.ch9_menu.BEMenuItemUI;


/**
 * 菜单栏
 */
public class MyMenuBar extends JMenuBar {

	/**
	 * @author WTY
	 */
	private static final long serialVersionUID = 1L;
	
	public JMenuItem newDirItem, newFileItem, newWindowItem, queryFileItem, Exit;
	public JMenuItem copyFileItem,moveFile, deleteFileItem,fileProperitiesItem;
	public JMenuItem listViewItem,tiledViewItem;
	public JMenuItem aboutSystemItem, helpItem;
	
	public MyMenuBar() {
		
		JMenuBar menuBar1;
		JMenu menuFile1, menuFile2, menuFile3, menuFile4;
		
		int menuBarWidth = 120;
		int menuBarHeight = 30;
		int menuItemWidth = 170;
		int menuItemHeight = 24;
		
		Font font = new Font("幼圆",Font.PLAIN,20);
		menuBar1 = new JMenuBar();
		
		
		//====================>文件
		menuFile1 = new JMenu("文件");
		menuFile1.setFont(font);
		ImageIcon openIco=new ImageIcon("images/icons/open.png");
		openIco.setImage(openIco.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
		menuFile1.setIcon(openIco);
		menuFile1.setMnemonic('Y');
		menuFile1.setPreferredSize(new Dimension(menuBarWidth,menuBarHeight));


		
		newFileItem = new JMenuItem("新建文件");
		newFileItem.setUI(new BEMenuItemUI());
		newFileItem.setMnemonic('F');
		newFileItem.setPreferredSize(new Dimension(menuItemWidth,menuItemHeight));
		newFileItem.setAccelerator(KeyStroke.getKeyStroke('F',java.awt.Event.CTRL_MASK));
		menuFile1.add(newFileItem);
		
		newDirItem = new JMenuItem("新建文件夹");
		newDirItem.setUI(new BEMenuItemUI());
		newDirItem.setMnemonic('N');
		newDirItem.setPreferredSize(new Dimension(menuItemWidth,menuItemHeight));
		newDirItem.setAccelerator(KeyStroke.getKeyStroke('N',java.awt.Event.CTRL_MASK));
		menuFile1.add(newDirItem);	
		
		newWindowItem = new JMenuItem("打开新窗口");
		newWindowItem.setUI(new BEMenuItemUI());
		newWindowItem.setMnemonic('W');
		newWindowItem.setAccelerator(KeyStroke.getKeyStroke('W',java.awt.Event.CTRL_MASK));
		menuFile1.add(newWindowItem);

		queryFileItem = new JMenuItem("查找文件");
		queryFileItem.setUI(new BEMenuItemUI());
		newWindowItem.setMnemonic('Q');
		queryFileItem.setAccelerator(KeyStroke.getKeyStroke('Q',java.awt.Event.CTRL_MASK));
		menuFile1.add(queryFileItem);


		Exit = new JMenuItem("退出");
		Exit.setUI(new BEMenuItemUI());
		Exit.setMnemonic('E');
		Exit.setAccelerator(KeyStroke.getKeyStroke('E',java.awt.Event.CTRL_MASK));
		menuFile1.add(Exit);
		menuBar1.add(menuFile1);

		//====================>操作
		menuFile2 = new JMenu("操作");
		menuFile2.setFont(font);
		ImageIcon OptIco=new ImageIcon("images/icons/operate.png");
		OptIco.setImage(OptIco.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
		menuFile2.setIcon(OptIco);
		menuFile2.setMnemonic('U');
		menuFile2.setPreferredSize(new Dimension(menuBarWidth,menuBarHeight));
		
		//menuFile.setAccelerator(KeyStroke.getKeyStroke('O',java.awt.Event.ALT_MASK));
		copyFileItem = new JMenuItem("复制文件");
		copyFileItem.setPreferredSize(new Dimension(menuItemWidth,menuItemHeight));
		copyFileItem.setMnemonic('C');
		copyFileItem.setUI(new BEMenuItemUI());
		copyFileItem.setAccelerator(KeyStroke.getKeyStroke('C',java.awt.Event.CTRL_MASK));
		menuFile2.add(copyFileItem);

		moveFile = new JMenuItem("移动文件");
		moveFile.setUI(new BEMenuItemUI());
		moveFile.setMnemonic('M');
		moveFile.setAccelerator(KeyStroke.getKeyStroke('M',java.awt.Event.CTRL_MASK));
		menuFile2.add(moveFile);

		deleteFileItem = new JMenuItem("删除文件");
		deleteFileItem.setUI(new BEMenuItemUI());
		deleteFileItem.setMnemonic('D');
		deleteFileItem.setAccelerator(KeyStroke.getKeyStroke('D',java.awt.Event.CTRL_MASK));
		menuFile2.add(deleteFileItem);

		fileProperitiesItem = new JMenuItem("文件属性");
		fileProperitiesItem.setUI(new BEMenuItemUI());
		fileProperitiesItem.setMnemonic('P');
		fileProperitiesItem.setAccelerator(KeyStroke.getKeyStroke('P',java.awt.Event.CTRL_MASK));
		menuFile2.add(fileProperitiesItem);


		menuBar1.add(menuFile2);

		
		//====================>帮助
		menuFile3 = new JMenu("查看");
		menuFile3.setFont(font);
		ImageIcon CheckIco=new ImageIcon("images/icons/check.png");
		CheckIco.setImage(CheckIco.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
		menuFile3.setIcon(CheckIco);
		menuFile3.setMnemonic('I');
		menuFile3.setPreferredSize(new Dimension(menuBarWidth,menuBarHeight));
		//menuFile.setAccelerator(KeyStroke.getKeyStroke('O',java.awt.Event.ALT_MASK));
		
		
		listViewItem = new JMenuItem("列表显示");
		listViewItem.setUI(new BEMenuItemUI());
		listViewItem.setMnemonic('L');
		listViewItem.setPreferredSize(new Dimension(menuItemWidth,menuItemHeight));
		listViewItem.setAccelerator(KeyStroke.getKeyStroke('L',java.awt.Event.CTRL_MASK));
		menuFile3.add(listViewItem);
		
		tiledViewItem = new JMenuItem("平铺显示");
		tiledViewItem.setUI(new BEMenuItemUI());
		tiledViewItem.setMnemonic('T');
		tiledViewItem.setAccelerator(KeyStroke.getKeyStroke('T',java.awt.Event.CTRL_MASK));
		menuFile3.add(tiledViewItem);
		
		tiledViewItem.setEnabled(false); // 此功能正在紧张开发中！ 2019/7/4

		menuBar1.add(menuFile3);
		
		
		//====================>帮助
		menuFile4 = new JMenu("关于");
		menuFile4.setFont(font);
		ImageIcon AboutIco=new ImageIcon("images/icons/about.png");
		AboutIco.setImage(AboutIco.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
		menuFile4.setIcon(AboutIco);
		menuFile4.setMnemonic('O');
		menuFile4.setPreferredSize(new Dimension(menuBarWidth, menuBarHeight));

		aboutSystemItem = new JMenuItem("关于本系统");
		aboutSystemItem.setPreferredSize(new Dimension(menuItemWidth,menuItemHeight));
		aboutSystemItem.setUI(new BEMenuItemUI());
		aboutSystemItem.setMnemonic('A');
		aboutSystemItem.setAccelerator(KeyStroke.getKeyStroke('A',java.awt.Event.CTRL_MASK));
		menuFile4.add(aboutSystemItem);

		helpItem = new JMenuItem("系统帮助");
		helpItem.setUI(new BEMenuItemUI());
		helpItem.setMnemonic('H');
		helpItem.setAccelerator(KeyStroke.getKeyStroke('H',java.awt.Event.CTRL_MASK));
		menuFile4.add(helpItem);

		menuBar1.add(menuFile4);

		
		
//		
//		newDirItem.addActionListener(this);
//		newWindowItem.addActionListener(this);
//		queryFileItem.addActionListener(this);
//		Exit.addActionListener(this);
//		
//		copyFileItem.addActionListener(this);
//		moveFile.addActionListener(this);
//		deleteFileItem.addActionListener(this);
//		fileProperitiesItem.addActionListener(this);
//		
//		listViewItem.addActionListener(this);
//		tiledViewItem.addActionListener(this);
//		
//		aboutSystemItem.addActionListener(this);
//		helpItem.addActionListener(this);
		
		Color menuFileColor =new Color(236, 248, 255);
		menuFile1.setOpaque(true);
		menuFile1.setBackground(menuFileColor);	
		menuFile2.setOpaque(true);
		menuFile2.setBackground(menuFileColor);	
		menuFile3.setOpaque(true);
		menuFile3.setBackground(menuFileColor);	
		menuFile4.setOpaque(true);
		menuFile4.setBackground(menuFileColor);	
		
		this.add(menuBar1);
		
	}



	public static void main(String[] args) {
		new MyMenuBar();
	}


}
