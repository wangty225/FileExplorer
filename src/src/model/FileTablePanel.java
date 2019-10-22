package model;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.ch5_table.BETableUI;

import controller.FileTableController;

public class FileTablePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable jTable;
	public FileTableController FTableC;
		
	/**
	 * Create the panel.
	 */
	public FileTablePanel() {
		setLayout(new BorderLayout(0, 0));
		
		jTable = new JTable();
		jTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"test0","test1","test2","test3"}
			},
			//tab_title 
			// //or
			new String[] {
				" ", "\u6587\u4EF6\u540D", "\u6587\u4EF6\u7C7B\u578B", "\u5927\u5C0F" // 文件名， 文件类型， 大小
			}
			
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
//			@SuppressWarnings("rawtypes")
//			Class[] columnTypes = new Class[] {
//				String.class, String.class, String.class
//			};
//			@SuppressWarnings("unchecked")
//			public Class<?> getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		add(jTable, BorderLayout.CENTER);
		jTable.setSelectionBackground(new Color(142,225,253));
		jTable.setSelectionForeground(Color.BLACK);
		jTable.setBorder(null);
		jTable.setFont(new Font("幼圆", Font.PLAIN, 14));
		jTable.setUI(new BETableUI());
		jTable.setShowGrid(false);
	}
	
	public static void main(String [] args) {

		JFrame jfm = new JFrame();
		jfm.setLayout(new BorderLayout());
		jfm.setSize(600, 400);
		
		JScrollPane jsp = new JScrollPane(new FileTablePanel().jTable);
		jfm.add(jsp, BorderLayout.CENTER);
		
		jfm.setVisible(true);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
