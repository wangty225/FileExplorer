package model;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import java.awt.Font;
import java.io.File;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.jb2011.lnf.beautyeye.ch16_tree.BETreeUI;


import java.awt.BorderLayout;

public class FileTreePanel extends JTree{
	
	/**
	 * @author wty
	 */
	private static final long serialVersionUID = 1L;
	
	public JTree tree;
	public DefaultMutableTreeNode rootNode;

	/**
	 * Create the panel.
	 */
	public FileTreePanel() {
		
		FileSystemView fsv=FileSystemView.getFileSystemView();		//获得系统文件
		File root=fsv.getFiles(fsv.getHomeDirectory(), true)[0];
		rootNode = new DefaultMutableTreeNode(root);
		tree = new JTree(rootNode);
		tree.setUI(new BETreeUI());
		tree.setFont(new Font("幼圆", Font.PLAIN, 14));
		tree.setRowHeight(20);
		tree.setOpaque(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);//设置仅单选
		tree.setExpandsSelectedPaths(false);

	}

	public static void main(String [] args) {
		
		JFrame jfm = new JFrame();
		jfm.setLayout(new BorderLayout());
		jfm.setSize(300, 400);
		JScrollPane jsp = new JScrollPane(new FileTreePanel().tree);
//		jsp.setPreferredSize(new Dimension(300, 600));
		jfm.add(jsp, BorderLayout.CENTER);
//		jfm.add(new Label("center"), BorderLayout.CENTER);
		jfm.setVisible(true);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

///**
// * 实现tree节点绘制器的功能 
// */
//@SuppressWarnings("serial")
//class FolderRenderer extends DefaultTreeCellRenderer {
//	
//	public FolderRenderer() {
//		
//	}
//	
//	public Component getTreeCellRendererComponent(JTree tree, Object object, boolean sel, boolean expanded,
//			boolean leaf, int row, boolean hasFocus) {
//		FileSystemView fsv=FileSystemView.getFileSystemView();
//		Icon icon = fsv.getSystemIcon((File)object);
//		setLeafIcon(icon);
//		setOpenIcon(icon);
//		setClosedIcon(icon);
//		setOpaque(false);
//		return super.getTreeCellRendererComponent(tree, object, sel, expanded, leaf, row, hasFocus);
//	}
//}