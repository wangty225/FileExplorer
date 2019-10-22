package controller;

import java.io.File;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import model.FileTablePanel;
import model.FileTreePanel;

public class NavPanelController {
	
	FileTreePanel treePanel;
	FileTablePanel tablePanel;
	
	public NavPanelController(FileTreePanel treePanel) {
		this.treePanel = treePanel;
	}
	
	public NavPanelController(FileTablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}
	
	public void queryNode(DefaultMutableTreeNode findNode, String nodeName, boolean ifExpand) {	
		for(int j=0;j<findNode.getChildCount();j++) {
			if(findNode.getChildAt(j).toString().equals(nodeName) ) {
				findNode = (DefaultMutableTreeNode) findNode.getChildAt(j);
				break;
			}
		}
		try {
			treePanel.tree.clearSelection(); // 坑点，MD。
			treePanel.tree.setSelectionPath(new TreePath(findNode.getPath()));
			if(ifExpand)
				treePanel.tree.expandPath(new TreePath(findNode.getPath()));
			else
				treePanel.tree.collapsePath(new TreePath(findNode.getPath()));
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "路径无效，请重新确认！", "错误", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	// goButton action
	public void goAction(String inputPath, boolean ifExpand) {
		if(inputPath=="" || inputPath == null || inputPath.equals("")) return ;
		int tempIndex = 0, i; // 开始提取nodeName的位置
		DefaultMutableTreeNode findNode = treePanel.rootNode;
		String procdStr = null;
		try {
			for(i=0; i<inputPath.length(); i++) {
				String temp = null;
				if(inputPath.charAt(i)==File.separatorChar) {
					temp = inputPath.substring(0, i+1); // 包括后面紧邻的文件路径分隔符
					procdStr = temp;
					String nodeName = temp.substring(tempIndex, temp.length()-1);
					tempIndex = i + 1;
	//				queryNode(findNode, nodeName);  // 貌似有bug wtf????
					for(int j=0;j<findNode.getChildCount();j++) {
						if(findNode.getChildAt(j).toString().equals(nodeName) ) {
							findNode = (DefaultMutableTreeNode) findNode.getChildAt(j);
							break;
						}
					}
					try {
						treePanel.tree.clearSelection(); // 坑点，TMD
						treePanel.tree.setSelectionPath(new TreePath(findNode.getPath()));
						treePanel.tree.expandPath(new TreePath(findNode.getPath()));
	
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "路径无效，请重新确认！", "错误", JOptionPane.ERROR_MESSAGE);
					}	
					
				}
			} // 处理到最后一个file.seperator前的所有字符
			
			//处理剩下的字符
			String leftStr = inputPath.substring(procdStr.length());
			if( (!leftStr.equals("")) && leftStr != null) { // 有剩余字符
				queryNode(findNode, leftStr, ifExpand);
	//			for(int j=0;j<findNode.getChildCount();j++) {
	//				if(findNode.getChildAt(j).toString().equals(leftStr) ) {
	//					findNode = (DefaultMutableTreeNode) findNode.getChildAt(j);
	//					break;
	//				}
	//			}
	//			try {
	//				treePanel.tree.clearSelection(); // 坑点，踏马的。
	//				treePanel.tree.setSelectionPath(new TreePath(findNode.getPath()));
	//				treePanel.tree.expandPath(new TreePath(findNode.getPath()));
	//			} catch (Exception e1) {
	//				JOptionPane.showMessageDialog(null, "输入路径无效，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
	//			}	
			}
		}
		catch(Exception ee) {
			System.err.println("GoAction() Failed!");
			JOptionPane.showMessageDialog(null, "输入路径无效，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
		}
			
	}

	// preButton action
	public void preAction(String inputPath) {
		if(inputPath=="" || inputPath == null || inputPath.equals("") || inputPath.length()<=1) return ;
		if(inputPath.charAt(inputPath.length()-1) == File.separatorChar) {
			if(inputPath.charAt(inputPath.length()-2) != ':')
				inputPath = inputPath.substring(0,inputPath.length()-1);
		}		
		int i=inputPath.length()-1;
		for(i=inputPath.length()-1;i>=0;i--) {
			if(inputPath.charAt(i) == File.separatorChar) {
				break;
			}
		}
		inputPath=inputPath.substring(0, i+1); // 提取的字符串，最后一个字符为File.seperator

		goAction(inputPath, false);
	}
	
	
	// nextButton action
	public void nextAction(String inputPath) {
		// DONE 展开当前节点 
		goAction(inputPath, true);
		// TODO (功能扩展) 将此功能改成找到当前已选中节点的下一个相邻兄弟节点
	}
	
	// findBtn action。需要使用FileTablePanel构造并调用
	public void searchAction(String sname) {
		//查找当前文件夹下的文件并定位
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for(int i=0;i<tablePanel.jTable.getRowCount();i++) {
			Vector <String> retRow = new Vector<String>();
			if(tablePanel.jTable.getValueAt(i, 1).equals(sname)) {
				for(int j=0;j<tablePanel.jTable.getColumnCount();j++) {
					retRow.add((String)tablePanel.jTable.getValueAt(i, j));
				}
				data.add(retRow);
			}
		}
		Vector<String> columnNames1 = new Vector<String>();
		columnNames1.add("  ");
		columnNames1.add("文件名");
		columnNames1.add("文件类型");
		columnNames1.add("大小");
		
		tablePanel.jTable.removeAll();
		DefaultTableModel model = new DefaultTableModel(data,columnNames1) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablePanel.jTable.setModel(model);
		tablePanel.jTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablePanel.jTable.getColumnModel().getColumn(0).setMinWidth(0);
		tablePanel.jTable.getColumnModel().getColumn(1).setPreferredWidth(360);
		tablePanel.jTable.getColumnModel().getColumn(1).setMinWidth(30);
		tablePanel.jTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		tablePanel.jTable.getColumnModel().getColumn(2).setMinWidth(30);
		tablePanel.jTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		tablePanel.jTable.getColumnModel().getColumn(3).setMinWidth(30);
		tablePanel.jTable.updateUI();
	}
	
}
