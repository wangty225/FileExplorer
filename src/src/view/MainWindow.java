package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;

import controller.FileModelController;
import controller.FileTableController;
import controller.FileTreeController;
import controller.NavPanelController;
import controller.PopMenuController;
import controller.PropertiesController;
import main.RunExplorerThread;
import model.FileModel;
import model.FileTablePanel;
import model.FileTreePanel;
import model.MyMenuBar;
import model.MyPopupMenu;
import model.NavigatePanel;
import model.PropertiesWindow;


public class MainWindow extends JFrame implements ActionListener, TreeSelectionListener, TreeWillExpandListener{
	
	/**
	 * @author WTY
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame jFrame = this;
	public  JPanel contPanel = new JPanel();
	private JPanel navPanel = new JPanel();
	private JPanel bodyPanel = new JPanel();
	private JPanel footpanel = new JPanel();
	private FileTablePanel tablePanel;
	private FileTreePanel treePanel;
	private MyMenuBar menuBar = new MyMenuBar();
	private NavigatePanel MyNavPanel = new NavigatePanel();
	private JLabel footLabel = new JLabel(" ▲ " + "0" + "个项目");
	public FileTreeController FTreeC;
	public FileTableController FTableC; 
	public MyPopupMenu jPopMenu = new MyPopupMenu();
	
	
	public MainWindow(){
	
		addListenToMyMenuBar();
		
		Color navColor = new Color(198, 240, 255);
		MyNavPanel.setBackground(navColor);
		navPanel.add(MyNavPanel);
		navPanel.setBackground(navColor);
		
		MyNavPanel.preBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputPath = MyNavPanel.pathTextField.getText();
				new NavPanelController(treePanel).preAction(inputPath);
			}
		});
		
		MyNavPanel.nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputPath = MyNavPanel.pathTextField.getText();
				new NavPanelController(treePanel).nextAction(inputPath);
			}
		});
		
		MyNavPanel.goBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputPath = MyNavPanel.pathTextField.getText();
				new NavPanelController(treePanel).goAction(inputPath, true);
			}
		});
		MyNavPanel.searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sname = MyNavPanel.searchField.getText();
				if(!sname.equals("") && sname != null)
					new NavPanelController(tablePanel).searchAction(sname);
				else {
					String inputPath = MyNavPanel.pathTextField.getText();
					new NavPanelController(treePanel).goAction(inputPath, true);
				}		
			}
		});

		for(int i=0;i<jPopMenu.getMenuItemCount();i++) {
			jPopMenu.jMenuItem[i].addActionListener(this);
		}

		
		treePanel = new FileTreePanel();
		JScrollPane treeJsp = new JScrollPane(treePanel.tree);
		treeJsp.setPreferredSize(new Dimension(220, 800));
		treeJsp.setOpaque(true);
//		treeJsp.setBackground(new Color(255,222,173));
		FTreeC = new FileTreeController(treePanel.tree, treePanel.rootNode);
		FTreeC.IniTree();
		treePanel.tree.addTreeSelectionListener(this);
		treePanel.tree.addTreeWillExpandListener(this);
//		treePanel.tree.addMouseListener(mouseAdapter);

//		bodyPanel.add(new Button("body"));
		tablePanel = new FileTablePanel();
		JTable table = tablePanel.jTable;
		table.addMouseListener(mouseAdapter); 
		JScrollPane tableJsp = new JScrollPane(table);
//		tableJsp.setBackground(new Color(190,245,220));
		
		bodyPanel.add(tableJsp, BorderLayout.CENTER);
//		bodyPanel.setBackground(new Color(190,245,220));
		FTableC = new FileTableController(table);
		FTableC.IniTable();
		
		
		footpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		footLabel.setFont(new Font("幼圆", Font.PLAIN, 14));
		footpanel.add(footLabel);
		footpanel.setBackground(Color.white);
		

		contPanel.setLayout(new BorderLayout(3, 2));
		contPanel.add(navPanel, BorderLayout.NORTH);
		contPanel.add(treeJsp, BorderLayout.WEST);
		
		contPanel.add(tableJsp, BorderLayout.CENTER);
		contPanel.add(footpanel, BorderLayout.SOUTH);
				
		jFrame.setTitle("File Explorer V2.0");
		ImageIcon logoIcon = new ImageIcon("images\\logo.png");
		jFrame.setIconImage(logoIcon.getImage());
		jFrame.setLayout(new BorderLayout());
		jFrame.setJMenuBar(menuBar);
		jFrame.add(contPanel, BorderLayout.CENTER);
		
		jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setBounds(100, 100, 900, 640);
		jFrame.setVisible(true);
	}
	
	MouseAdapter mouseAdapter = new MouseAdapter() {   
        // 点击鼠标  
        public void mousePressed(MouseEvent event) {   
            // 调用triggerEvent方法处理事件  
              triggerEvent(event);  
        } 
        
        // 释放鼠标  
        public void mouseReleased(MouseEvent event) {   
            triggerEvent(event);  
        }  

        private void triggerEvent(MouseEvent event) { // 处理事件  
               
            if(event.getButton()==MouseEvent.BUTTON3) {
//				 int focusedRowIndex = tablePanel.jTable.rowAtPoint(event.getPoint());
//		         if (focusedRowIndex == -1) return;		                
//		         tablePanel.jTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex); 
				jPopMenu.show(event.getComponent(), event.getX(),  
                        event.getY());   
			}
        }  
    };
    
	

//	public static void main(String [] args) {
//		
////		try {
////			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
////			System.out.println("SUCCESS!");
////		} catch (Exception e) {
////			System.err.println("BeautyEye Load Failed!");
////		}
//		
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
//		} catch (Exception e) {
//			System.err.println("UIManager Load Failed!");
////			e.printStackTrace();
//		}
//
////		new MainFrame();
//	}
	
	
	@Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		// TODO 自动生成的方法存根
		System.out.println("1展开操作");
//		treePanel.tree.setSelectionPath(new TreePath(event.getPath()));
		String path = FTreeC.TreeWillExpand(event);

		if(path!="" && path!=null) {
//			System.out.println("展开操作，调用setTable的path: " + path);
			FileModel file = new FileModel(path);
			FTableC.setTable(file.getMyListFiles()); 
			MyNavPanel.pathTextField.setText(path);
		}else {
//			System.out.println("未setTable()！");
		}

	}
	@Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		// TODO 自动生成的方法存根
		System.out.println("1折叠操作");
		FTreeC.TreeWillCollapse(event);
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO 点击节点：①获得该节点写的文件或文件夹数目；②更新导航栏MyNavPanel.pathTextField显示的路径；③更新body的table
		System.out.println("1点击改变操作");
		String path = FTreeC.ValueChanged(e);
		
		if(path!="" && path!=null) {
//			System.out.println("valueChanged操作，调用setTable的path: " + path);
			try {
				FileModel file = new FileModel(path);
				if(file.isDirectory())
					FTableC.setTable(file.getMyListFiles());
				else if(file.isFile()){
					File [] f = { file };
					FTableC.setTable(f);
				}
				else {
					File [] f = {  };
					FTableC.setTable(f);
				}
					
			}catch(Exception e0) {
				System.out.println("目标路径拒绝访问");
				JOptionPane.showMessageDialog(null, "没有权限，文件夹拒绝访问!", "位置不可用", JOptionPane.ERROR_MESSAGE);
			}
		}else {
//			System.out.println("valueChanged调用setTable失败！");
		}
		MyNavPanel.pathTextField.setText(path);
		footLabel.setText(" ▲ " + FTreeC.itemNum + "个项目");
		FTreeC.itemNum = 0;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		 new PopMenuController(jPopMenu).eventHandler(e, MyNavPanel.pathTextField.getText(), 
				 new NavPanelController(treePanel) , FTableC);
		
	}

	private static String srcFilePath = null;
	private static String destFilePath = null;
	private static  boolean copyFlag = true;
	
	class copyThread extends Thread {
		public void run() {
			try {
				new FileModelController().paste(srcFilePath, destFilePath , copyFlag);
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally{
				srcFilePath = null;
				destFilePath = null;
				copyFlag = true;
			}
		}
	}; 
	
	public void addListenToMyMenuBar() {
		menuBar.newFileItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘新建文件’菜单项");
				String path  = MyNavPanel.pathTextField.getText();
				FileModelController.createMyFile(path);
			}
		});
		
		menuBar.newDirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘新建文件夹’菜单项");
				String path  = MyNavPanel.pathTextField.getText();
				FileModelController.createMyDir(path);
			}
		});

		menuBar.newWindowItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘打开新窗口’菜单项");
				new RunExplorerThread().start();
			}
		});

		menuBar.queryFileItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘查询文件’菜单项");
				String sname = JOptionPane.showInputDialog("请输入要查找的文件名：");
				if( sname == null || sname.equals("")) {
					System.out.println("啥都没输入");
				}
				else {			
					new NavPanelController(tablePanel).searchAction(sname);
				}
					
//				else {
//					String inputPath = MyNavPanel.pathTextField.getText();
//					new NavPanelController(treePanel).goAction(inputPath, true);
//				}		
			}
		});

		menuBar.Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘退出’菜单项");
				System.exit(1);
			}
		});

		

		
		menuBar.copyFileItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘复制文件’菜单项");
				srcFilePath = JOptionPane.showInputDialog("请输入源文件路径：");
				destFilePath = JOptionPane.showInputDialog("请输入目标文件路径：");
				copyFlag = true;
				if(srcFilePath !=null && !srcFilePath.equals("") && destFilePath !=null && !destFilePath.equals(""))
					new copyThread().start();
			}
		});
		
		menuBar.moveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘移动文件’菜单项");
				srcFilePath = JOptionPane.showInputDialog("请输入源文件路径：");
				destFilePath = JOptionPane.showInputDialog("请输入目标文件路径：");
				copyFlag = false;
				if(srcFilePath !=null && !srcFilePath.equals("") && destFilePath !=null && !destFilePath.equals(""))
					new copyThread().start();
			}
		});
		
		menuBar.deleteFileItem.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘删除文件’菜单项");
				String path = JOptionPane.showInputDialog("请输入查询属性的有效文件（夹）路径：");
				if(path==null || path.equals("")) {
					// nothing
				}
				else if(new File(path).isFile()) {
					if(new FileModelController().delete(path)) {
						JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);	
					}
				}
				else if(new File(path).isDirectory()) {
					System.out.println("删除：选择 目录的文件夹" + path);
					int confirm = JOptionPane.showConfirmDialog(null, "你确定要删除目录 "+ path + " 下所有文件吗？");
					if(confirm == JOptionPane.YES_OPTION) {
						System.out.println("执行删除2");
						if(FileModelController.deleteDirectory(path)) {
							JOptionPane.showMessageDialog(null, "删除成功");
						}
						else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
						// 谨慎删除
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "删除失败！请选择一个文件进行删除！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		menuBar.fileProperitiesItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘文件属性’菜单项");
				String path = JOptionPane.showInputDialog("请输入查询属性的有效文件（夹）路径：");
				if(path!=null && (!path.equals(""))) {
					PropertiesWindow propWindow = new PropertiesWindow();
					PropertiesController PropC = new PropertiesController(propWindow);
					PropC.UpdateWindow(path);
				}
			}
		});
		
		
		
		menuBar.listViewItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘列表显示’菜单项");
				
			}
		});
		menuBar.tiledViewItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘平铺显示’菜单项");
				
			}
		});
		
		
		menuBar.aboutSystemItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘关于本系统’菜单项");
//				AboutSystem aboutSystem = new AboutSystem();
				new AboutWindow();
			}
		});
		menuBar.helpItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=======>用户选择了‘系统帮助’菜单项");
				new HelpWindow();
				
			}
		});	
	}
}
