package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.FileModel;
import view.MessageWindow;

public class FileModelController {
	FileModel fileModel;
	File f = new File("");
	
	private MessageWindow messageWindow;
	private double percent = 0;
	// operation   true为复制   false 为剪切   // , MessageWindow messageWindow
	public void paste(String from, String to, boolean operation) throws FileNotFoundException{
		System.out.println("开始执行paste！");
		String option = (operation)?"复制":"移动";
//		messageWindow = new MessageWindow(option);
		
		FileInputStream fis = new FileInputStream(from);
		FileOutputStream fos = new FileOutputStream(to);
		DataInputStream dis = new DataInputStream(fis);
		DataOutputStream dos = new DataOutputStream(fos);
		
		Runnable run0 =  new Runnable() {
			public void run() {
				try {
					messageWindow = new MessageWindow(option);
					messageWindow.info2.setText(" FROM: " + from);
					messageWindow.info3.setText(" TO:   " + to);
					messageWindow.setLocationRelativeTo(null);
					messageWindow.addWindowListener(new WindowListener() {
						
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO 自动生成的方法存根
							try {
								dis.close();
								dos.close();
								fis.close();
								fos.close();
								// 关闭窗口时，同时关闭读写流，解除占用
							} catch (IOException e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
							}
							
							System.out.println("关闭读写流。");
						}

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO 自动生成的方法存根
							
						}

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO 自动生成的方法存根
							
						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO 自动生成的方法存根
							
						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO 自动生成的方法存根
							
						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO 自动生成的方法存根
							
						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO 自动生成的方法存根
							
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		SwingUtilities.invokeLater(run0);
		
		long copiedSize=0;

		int size = 1024*10;
//		System.out.println("from   "+from);
//		System.out.println("to     "+to);
		byte [] buffer = new byte[size];
		File srcFile = new File(from);
		percent = 0;
		DecimalFormat df = new DecimalFormat("#.00"); 
		
		Runnable run =  new Runnable() {
			public void run() {
				try {
					messageWindow.info1.setText(" 正在"+ option +"文件...   已完成  " + df.format(percent) +"%");
					messageWindow.progressBar.setValue((int)percent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		

		double MemoryInUsed;

		try {

			while((dis.read(buffer,0,size)) != -1) {
				dos.write(buffer,0,size);
				copiedSize += size; 
				if(srcFile.length()/size!=0)
					MemoryInUsed = (srcFile.length()/size + 1)*size*1.0;  // 文件实际占用空间
				else 
					MemoryInUsed = srcFile.length();
				percent = (copiedSize*100.0/MemoryInUsed); // 百分比
//				System.out.println("percent" + df.format(percent));
				SwingUtilities.invokeLater(run);
			}
			dis.close();
			dos.close();
			fis.close();
			fos.close();
			
			if(operation==false)
			{	
				if(delete(from))
					JOptionPane.showMessageDialog(null,"剪切成功!","成功",JOptionPane.INFORMATION_MESSAGE);//复制失败showDialog()
				else
					JOptionPane.showMessageDialog(null,"文件已复制，删除源文件失败！","剪切失败",JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"复制成功!","成功",JOptionPane.INFORMATION_MESSAGE);
			}
			System.out.println("执行paste完毕！");
			
//			return true;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"复制失败!","失败",JOptionPane.ERROR_MESSAGE);//复制失败showDialog()
			//第一个参数为null，为要居中窗口,默认为windows桌面
//			return false;
		}	
			
	}
	
	
	 
	// 创建文件
	public static void createMyFile(String path) {
		if(new File(path).isFile()) {
			JOptionPane.showMessageDialog(null, "请选择一个目录以创建文件", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String newFileName = JOptionPane.showInputDialog("请输入文件名：");
		System.out.println("new file name: " + newFileName);
		if(newFileName==null || newFileName.equals("")) return;
		File f = new File(path + File.separator + newFileName);
		if(f.canRead()) {
			JOptionPane.showMessageDialog(null, "已存在该文件，尝试重新输入新文件名", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			try {
				f.createNewFile();
				System.out.println("new file path: " + f.getAbsolutePath());
				JOptionPane.showMessageDialog(null, f.getAbsolutePath() +" 创建成功！", "成功", JOptionPane.INFORMATION_MESSAGE);						
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "文件创建失败！", "异常", JOptionPane.ERROR_MESSAGE);						

			}
		}
	}
	
	// 创建文件夹
	public static void createMyDir(String path) {
		if(new File(path).isFile()) {
			JOptionPane.showMessageDialog(null, "请选择一个目录以创建文件", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String newDirName = JOptionPane.showInputDialog("请输入文件夹名：");
		System.out.println("new Dir name: " + newDirName);
		if(newDirName==null || newDirName.equals("")) return;
		File f = new File(path + File.separator + newDirName);
		if(f.exists()) {
			JOptionPane.showMessageDialog(null, "已存在该文件夹，尝试重新输入新文件夹名", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			f.mkdirs();
			JOptionPane.showMessageDialog(null, f.getAbsoluteFile() +  " 创建成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static boolean delete(String src) {
		File del_file = new File(src);
		if(del_file.exists())
			return del_file.delete();
		return false;
	}
//	private static boolean deleteFile(File file) {
//		return file.delete();
//	}

	public static boolean deleteDirectory(String path) {
		boolean flag = true;
		File dirFile = new File(path);
		if (!dirFile.isDirectory()) {
			return flag;
		}
		File[] files = dirFile.listFiles();
		for (File file : files) {

			if (file.isFile()) {
				flag = delete(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				flag = deleteDirectory(file.getAbsolutePath());
			}
			if (!flag) { 
				break;
			}
		}
		flag = dirFile.delete(); 
		return flag;
	}

//	public static boolean deleteDir(File RootFile) {
////		File RootFile = new File(path);
//		System.out.println(RootFile.getAbsolutePath());
//		if(RootFile.listFiles() == null) {
//			System.out.println("递归删除：" + RootFile.getAbsolutePath());
//			delete(RootFile.getAbsolutePath());
//			return true;
//		}
//		// 递归删除子文件
//		for(File f: RootFile.listFiles()) {
//			if(f.isDirectory()) {
//				if(f.listFiles().length > 0) {
//					deleteDir(f);
//				}
//				else {
//					delete(f.getAbsolutePath());
//				}
//			}	
//		}
//		// 删除跟节点
//		if(delete(RootFile.getAbsolutePath()))
//			return true;
//		return false;
//	}
	
}
