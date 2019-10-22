package controller;

import java.io.File;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.FileModel;

public class FileTableController {
	
	public JTable jTable;
	private Vector<String> columnNames1 = new Vector<String>();
	private Vector<Vector<String>> data = new Vector<Vector<String>>();
	public FileTableController(JTable table) {
		// TODO 自动生成的构造函数存根
		this.jTable = table;
//		System.out.println("FileTableController.java   Line 19 :" + jTable.getRowCount());
	}
	public void setColumnNames() {
		columnNames1.add(" ");
		columnNames1.add("文件名");
		columnNames1.add("文件类型");
		columnNames1.add("大小");
	}
	
	public void setWidthSize() {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		jTable.getColumnModel().getColumn(0).setMinWidth(0);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(360);
		jTable.getColumnModel().getColumn(1).setMinWidth(30);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(2).setMinWidth(30);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(3).setMinWidth(30);
	}
	
	public void IniTable() {
		columnNames1.clear();
		data.clear();
		setColumnNames();
		
		for(File f: File.listRoots()) {
			Vector<String> v = new Vector<String>();
			v.add("  ");
			v.add("本地磁盘(" + f.toString().substring(0,2)+")");
			data.add(v);
		}
		DefaultTableModel t = new DefaultTableModel(data, columnNames1) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};	
		jTable.setModel(t);
		setWidthSize();
		jTable.setRowHeight(30);
		jTable.setRowMargin(0);
		jTable.setFillsViewportHeight(true); // 设置填充满viewport
	}
	
	void update_tab(File [] files){//更新Jtable的tab动态数组的内容
		data.clear();
		System.out.println("files.length:" + files.length);
		for(int j=0;j<files.length;j++) {
			Vector<String> v = new Vector<String>();
			v.add(" ");
			v.add(files[j].getName());
			v.add(files[j].isFile()?"文件":"文件夹");
			String size = null;
			size = new FileModel(files[j].getPath()).getFileSize();
			if(size!=null && size!="")
				v.add(size);
			data.add(v);
		}
	}
	
	//初始化JTable的二维数组
	public void setTable(File []files) {
		update_tab(files);
		DefaultTableModel model = new DefaultTableModel(data,columnNames1) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		jTable.setModel(model);
		setWidthSize();
	}
}
