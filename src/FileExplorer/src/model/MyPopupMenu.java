package model;

import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyPopupMenu extends JPopupMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPopupMenu jPopMenu = this;
	public JMenuItem []jMenuItem = new JMenuItem[100];
	private int ItemCount = 0;
	
	public MyPopupMenu() {
		jMenuItem[0] = new JMenuItem("打开文件夹");
		jMenuItem[0].setName("打开文件夹");
		jMenuItem[1] = new JMenuItem("复制");
		jMenuItem[1].setName("复制");
		jMenuItem[2] = new JMenuItem("剪切");
		jMenuItem[2].setName("剪切");
		jMenuItem[3] = new JMenuItem("粘贴");
		jMenuItem[3].setName("粘贴");
		jMenuItem[4] = new JMenuItem("删除");
		jMenuItem[4].setName("删除");
		jMenuItem[5] = new JMenuItem("新建文件");
		jMenuItem[5].setName("新建文件");
		jMenuItem[6] = new JMenuItem("新建文件夹");
		jMenuItem[6].setName("新建文件夹");
		jMenuItem[7] = new JMenuItem("文件属性");
		jMenuItem[7].setName("文件属性");
		

		jPopMenu.add(jMenuItem[0]);
		jPopMenu.add(jMenuItem[1]);
		jPopMenu.add(jMenuItem[2]);
		jPopMenu.add(jMenuItem[3]);
		jPopMenu.add(jMenuItem[4]);
		jPopMenu.addSeparator();
		jPopMenu.add(jMenuItem[5]);
		jPopMenu.add(jMenuItem[6]);
		jPopMenu.addSeparator();
		jPopMenu.add(jMenuItem[7]);
		jMenuItem[0].setPreferredSize(new Dimension(160, 20));
		jMenuItem[3].setEnabled(false);
		
		ItemCount = 8;
		
	}
	
	public int getMenuItemCount() {
		return ItemCount;
	}

}
