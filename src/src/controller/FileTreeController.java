package controller;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import model.FileModel;

public class FileTreeController {
		private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		
		private JTree jTree;
		public FileTreeController(JTree jTree,DefaultMutableTreeNode  root) {
			// TODO 自动生成的构造函数存根
			this.rootNode=root;
			this.jTree = jTree;
		}
		
		public int itemNum = 0;  // footLable的文件数量
		public String itemPath = null;  // 导航栏内容 
		
		//初始化Jtree
		public void IniTree() {
//			FileSystemView fsv=FileSystemView.getFileSystemView();		//获得系统文件
			rootNode.removeAllChildren();
//			System.out.println("初始化tree");
			for(File f: File.listRoots()) {
				DefaultMutableTreeNode temp_node;
    			temp_node = new DefaultMutableTreeNode(f.toString().substring(0, 2));
    			temp_node.add(new DefaultMutableTreeNode("(Empty)"));
    			rootNode.add( temp_node );
			}
		}
		
		boolean FindNode(DefaultMutableTreeNode cur_node, DefaultMutableTreeNode temp_node) {
			int k =0;
   			for(k=0;k<cur_node.getChildCount();k++) {
   				if( cur_node.getChildAt(k).toString().equals(temp_node.toString()) ) {
//   				cur_node.remove(k);
//   					break;
   					return true;
   				}	
   			}
   			return false;
		}
		
		int FindNode2(DefaultMutableTreeNode cur_node, DefaultMutableTreeNode temp_node) {
			int k =0;
   			for(k=0;k<cur_node.getChildCount();k++) {
   				if( cur_node.getChildAt(k).toString().equals(temp_node.toString()) ) {
//   				cur_node.remove(k);
//   					break;
   					return k;
   				}	
   			}
   			return 0;
		}
		// 更新文件目录树的核心函数
		public boolean ReloadTree (String path, DefaultMutableTreeNode cur_node) {
//			System.out.println(path);
			FileModel fileDir = new FileModel(path);
			cur_node.removeAllChildren();
			if(fileDir.getMyFile().isDirectory()) {
				try{
//					File []files = fileDir.getMyListFiles();
					if(fileDir.getFileChildCount()>0) {
						cur_node.removeAllChildren();
						int cnt = fileDir.getFileChildCount();
						System.out.println("该目录下有 " + cnt + " 个项目");
						itemNum = cnt;
//						File [] files = fileDir.getMyListFiles();
						for(int i=0;i<cnt;i++) {
						   	if(fileDir.getMyListFiles()[i].isDirectory()&&fileDir.getMyListFiles()[i].canRead()) {//是目录
						   		DefaultMutableTreeNode temp_node;
						   		temp_node = new DefaultMutableTreeNode(fileDir.getMyListFiles()[i].getName());
						   		temp_node.add(new DefaultMutableTreeNode("(Empty)"));
//						   		if(FindNode(cur_node, temp_node)==false)
//						   			cur_node.add( temp_node );
						   		cur_node.insert(temp_node, FindNode2(cur_node, temp_node));
						   		
						   	}
					    }
						for(int i=0;i<cnt;i++) {
						   	if(fileDir.getMyListFiles()[i].isFile()){//是文件
						   		cur_node.add( new DefaultMutableTreeNode(fileDir.getMyListFiles()[i].getName()) );
						   	}
					    }
						
						
						return true;
//						ini_tab(files);
					}
					else {
//						ini_tab(files);
						JOptionPane.showMessageDialog(null, "该文件夹为空。", "提示", JOptionPane.INFORMATION_MESSAGE);
						itemNum = 0;
//						cur_node.removeAllChildren();	
						return true;
					}
				}
				catch (NullPointerException e) {
						JOptionPane.showMessageDialog(null, "没有权限，文件夹拒绝访问!", "位置不可用", JOptionPane.ERROR_MESSAGE);
						itemNum = 0;
						return false;
				}
//				System.out.println("存在");
			}
			else if( cur_node!=rootNode ){ 
				System.out.println("进入reloadtree的else分支");
//				FileModel f1 = new FileModel(path);
//				FileModel f[]= {f1};
//				itemNum = 1; // 选中的是文件
//				ini_tab(f);
				return true;
			}
			return false;
//			else System.out.println("not exists");
		}
		
		// 返回该文件是否能访问 并将选中文件（夹）下的文件个数传给 itemNum（ 0 or count ）
		public int CheckTree (String path, DefaultMutableTreeNode cur_node) {
//			System.out.println("ReloadTree2" + path );
			FileModel fileDir = new FileModel(path);
			if(fileDir.getMyFile().isDirectory()) {
				try{
					if(fileDir.getFileChildCount()>0) {
						int cnt = fileDir.getFileChildCount();
						System.out.println("该目录下有 " + cnt + " 个项目");
						itemNum = cnt;
						return 1;
					}
					else {
//						JOptionPane.showMessageDialog(null, "该文件夹为空。", "提示", JOptionPane.INFORMATION_MESSAGE);
						itemNum = 0;
						return 1;
					}
				}
				catch (NullPointerException e) {
//						JOptionPane.showMessageDialog(null, "没有权限，文件夹拒绝访问!", "位置不可用", JOptionPane.ERROR_MESSAGE);
						itemNum = 0;
						return 1;
				}
			}
			else if( cur_node!=rootNode ){ 
				System.out.println("进入reloadtree的else分支");
				itemNum = 1; 
				return 1; // 选中的是文件
			}
			return 0;
		}
		
		public String TreeWillExpand(TreeExpansionEvent event) {
			
			// TODO 自动生成的方法存根
//			System.out.println("controller 中  TreeController展开操作");
			TreePath  selectedNodepath = event.getPath();
			
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedNodepath.getLastPathComponent();
			String abs_path="";
			if (selectedNode != null) {
			     TreePath paths =selectedNodepath;
			     for (int i = 1; i < paths.getPathCount(); i++) { 
			    	 abs_path += paths.getPath()[i] + File.separator; //包括文件名
			     }
			     if(abs_path!="" && abs_path!=null) {
			    	 if(ReloadTree(abs_path,selectedNode)) 
			    		 return abs_path;
			     }
			    	 
			} 
			 else {
				 System.out.println("null");
			 }
			 return null;
		}
		
		public void TreeWillCollapse(TreeExpansionEvent event){
			// TODO 折叠并删除该节点下的所有子节点，然后添加一个 "正在加载" 节点
//			System.out.println("controller 中 TreeWillCollapse折叠操作");
			TreePath  selectedNode2 = event.getPath();
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedNode2.getLastPathComponent();
			selectedNode.removeAllChildren();
			SwingUtilities.invokeLater(new Runnable(){ 
					public void run(){
				     jTree.updateUI();
					}
				});
			selectedNode.add( new DefaultMutableTreeNode("正在加载...") );
		}

		public String ValueChanged(TreeSelectionEvent e) {
			// TODO 自动生成的方法存根
//			System.out.println("controller 中 ValueChanged 选中操作");
			String abs_path="";//不可以用"\0"
			 DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
			 if (selectedNode != null) {
			      TreePath paths = jTree.getSelectionPath();
			      for (int i = 1; i < paths.getPathCount(); i++) {
			    		  abs_path += paths.getPath()[i] + File.separator; //不包括文件名
			      }
			     if(abs_path!=""&&abs_path!=null)  {
			    	 if(CheckTree(abs_path,selectedNode) == 1 )
			    		 return abs_path;
			     }
			 } 
			 return null;
		}
		
}
