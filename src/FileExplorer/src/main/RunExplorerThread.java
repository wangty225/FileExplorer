package main;
import javax.swing.UIManager;


import view.MainWindow;

public class RunExplorerThread extends Thread{
	
	public void run() {
	
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
		} catch (Exception e) {
			System.err.println("[RunExplorerThread.java] UIManager Load Failed!");
		}
		
//		try{
//			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
//		    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//		    System.out.println("SUCCESS!");
//
//		}catch(Exception e){
//
//		}
		new MainWindow();	
	}
}
