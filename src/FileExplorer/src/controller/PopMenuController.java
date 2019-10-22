package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.MyPopupMenu;
import model.PropertiesWindow;


public class PopMenuController {
	private MyPopupMenu jPopMenu;
	public JMenuItem []menuItem;
//	private FileTableController FTableC;
//	private NavPanelController NPanelC;
//	
	
	public PopMenuController(MyPopupMenu jPopupMenu) {
		// TODO 自动生成的构造函数存根
		this.jPopMenu = jPopupMenu;
		this.menuItem = jPopupMenu.jMenuItem;
	}
//	
//	public PopMenuController(MyPopupMenu jPopupMenu, FileTableController FTableC, NavPanelController NPanelC) {
//		// TODO 自动生成的构造函数存根
//		this.jPopMenu = jPopupMenu;
//		this.menuItem = jPopupMenu.jMenuItem;
//		this.FTableC = FTableC;
//		this.NPanelC = NPanelC;
//	}
//	
	//初始化鼠标右键menu
	
	static String srcFilePath = null;
	String destFilePath = null;
	static boolean copyFlag = true;
	static String copyFileName = null;
	
	class copyThread extends Thread {
		public void run() {
			try {
				new FileModelController().paste(srcFilePath, destFilePath + copyFileName, copyFlag);
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			finally{
				srcFilePath = null;
				destFilePath = null;
				copyFlag = true;
			}
		}
	};
	
	
	//  时间处理函数
	@SuppressWarnings({ "null", "static-access" })
	public void eventHandler(ActionEvent e, String inputPath, NavPanelController NPanelC, FileTableController FTableC) {
		int index;
		for(index=0;index < jPopMenu.getComponentCount();index++) {
			if((JMenuItem)e.getSource()==menuItem[index]) {
				System.out.println(index + ": " + menuItem[index].getName());
				break;
			}
		}
		
		String path = inputPath;
		String focused = null;
		if(FTableC.jTable.getSelectedRowCount() != 1){
			focused = null;
		}
		else {
			focused = FTableC.jTable.getValueAt(FTableC.jTable.getSelectedRow(), 1).toString();
			if(focused.length() == 8 && focused.charAt(6) == ':') {  
				// 选中的事根目录（"本地磁盘（X）："）, 8代表根目录（"本地磁盘（X）："的长度）
				System.out.println(inputPath);
				System.out.println(focused);
				focused = focused.substring(5,6) + ":" + File.separator;
			}
		}


		switch (index) {
		case 0:  //  打开	
			if(new File(path).isFile() || new File(inputPath + focused).isFile()) {
				JOptionPane.showMessageDialog(null, "请选择一个文件夹再打开！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				path = inputPath + focused;
				NPanelC.goAction(path, true);
			}
			break;
			
		case 1:  // 复制
			menuItem[3].setEnabled(true);
			copyFlag = true;
			if(new File(path).isFile()) {
				srcFilePath = path;
				// 确定copyFileName。（编写不可读代码的艺术？？）
				if(path.charAt(path.length()-1) == File.separatorChar)
					copyFileName = path.substring(0, path.length()-1);
				for(int i=copyFileName.length()-1; i>=0;i--) {
					if(copyFileName.charAt(i) == File.separatorChar) {
						copyFileName = copyFileName.substring(i+1);
						break;
					}	
				}
			}
			else if(new File(inputPath + focused).isFile()) {
				srcFilePath = inputPath + focused;
				copyFileName = focused;
			}
			else {
				JOptionPane.showMessageDialog(null, "请选择一个文件再操作！", "提示", JOptionPane.INFORMATION_MESSAGE);
				menuItem[3].setEnabled(false);
			}
	
			break;
			
		case 2:  // 剪切
			menuItem[3].setEnabled(true);
			copyFlag = false;
			if(new File(path).isFile()) {
				srcFilePath = path;
				// 确定copyFileName。（编写不可读代码的艺术？？）
				if(path.charAt(path.length()-1) == File.separatorChar)
					copyFileName = path.substring(0, path.length()-1);
				for(int i=copyFileName.length()-1; i>=0;i--) {
					if(copyFileName.charAt(i) == File.separatorChar) {
						copyFileName = copyFileName.substring(i+1);
						break;
					}	
				}
			}
			else if(new File(inputPath + focused).isFile()) {
				srcFilePath = inputPath + focused;
				copyFileName = focused;
			}
			else {
				JOptionPane.showMessageDialog(null, "请选择一个文件再操作！", "提示", JOptionPane.INFORMATION_MESSAGE);
				menuItem[3].setEnabled(false);
			}
			
			break;
			
		case 3:  // 粘贴
			System.out.println("开始调用paste！");
			if(new File(path).isFile()) {
				JOptionPane.showMessageDialog(null, "请选择一个文件夹再粘贴！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(new File(path + focused).isDirectory()) {
				destFilePath = path + focused;
			}
			else if(new File(path).isDirectory()) {
				destFilePath = path;
			}
			else {
				JOptionPane.showMessageDialog(null, "粘贴失败！", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			}
			if(destFilePath.charAt(destFilePath.length()-1)!=File.separatorChar)
				destFilePath += File.separatorChar;
					
			new copyThread().start();
			
			break;
		case 4:  // 删除
			if(new File(path).isFile()) {
				if(new FileModelController().delete(path)) {
					JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);	
				}
			}
			else if(new File(path + focused).isFile()){
				if(new FileModelController().delete(path + focused)) {
					JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);	
				}
			}
			else if(new File(path + focused).isDirectory()) {
				System.out.println("删除：选择+点击 目录的文件夹" + path + focused);
				int confirm = JOptionPane.showConfirmDialog(null, "你确定要删除"+ path + focused + "吗？");
				if(confirm == JOptionPane.YES_OPTION) {
					System.out.println("执行删除1");
					if(FileModelController.deleteDirectory(path)) {
						JOptionPane.showMessageDialog(null, "删除成功");
					}
					else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
					// 谨慎删除
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
			break;
		case 5:  // 新建文件
			FileModelController.createMyFile(path);
			break;
		case 6:  // 新建文件夹
			FileModelController.createMyDir(path);
			break;
		case 7:  // 属性
			PropertiesWindow propWindow = new PropertiesWindow();
			PropertiesController PropC = new PropertiesController(propWindow);
				
			
			if(new File(path + focused).isFile() || new File(path + focused).isDirectory()) {
				PropC.UpdateWindow(path + focused);
			}
			else if(new File(path).isFile() || new File(path).isDirectory())
				PropC.UpdateWindow(path);
			else {
				JOptionPane.showMessageDialog(null, "属性打开失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
			
		default:
			System.err.print("[ERROR]JPopupMenu菜单功能未设置！");
			break;
		}
	}
}
