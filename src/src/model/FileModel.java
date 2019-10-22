package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;

public class FileModel extends File{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	File file = this;
	File [] files;
	
	public FileModel(String pathname) {
		super(pathname);
//		System.out.println("pathname: " + pathname);
		try {
			files = file.listFiles();
		}catch(Exception e) {
			System.out.println("[info] FileModel.java 构造file.listFiles失败");
		}

	}
	
	public String getFileName() {
		return super.getAbsolutePath();
	}
	
	public File getMyFile() {
		return file;
	}
	
	public File [] getMyListFiles() {
		return files;
	}
	
	public int getFileChildCount() {
		
		return files.length;
	}
	
	public String getFileSize(){
		String fileSize = ""; 
	    if(super.exists() && super.isFile()){
	    long fileS = super.length();
	      DecimalFormat df = new DecimalFormat("#.00"); 
	           if (fileS < 1024) {
	        	   if(!((double) fileS - 0 <0.000006))
	               fileSize = df.format((double) fileS) + "B";
	        	   else fileSize="0B";
	           } else if (fileS < 1048576) {
	               fileSize = df.format((double) fileS / 1024) + "KB";
	           } else if (fileS < 1073741824) {
	               fileSize = df.format((double) fileS / 1048576) + "MB";
	           } else {
	               fileSize = df.format((double) fileS / 1073741824) +"GB";
	           }
	    }else if(super.exists() && super.isDirectory()){
	    	fileSize = "";
	    }else{
	    	fileSize = "0B";
	    }
	    return fileSize;
	 }
	
	
	public String getFilePath() {
		
		return super.getAbsolutePath();
	}
	
	public ImageIcon getSmallFileIcon() {
		
		return null;
	}
	
	public ImageIcon getLargeFileIcon() {
		
		return null;
	}
	
	/**
	 * 读取文件创建时间
	 */
	public static String getCreateTime(String filePath) {
		Path p = Paths.get(filePath);
		Calendar calendar = Calendar.getInstance();
		BasicFileAttributes att;
//		Date time = new Data;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			att = Files.readAttributes(p, BasicFileAttributes.class);
//			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(att.creationTime().toMillis());
//			time = calendar.getTime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取文件的属性
		return formatter.format(calendar.getTime());
	}

	public static String getModifiedTime(String filePath) {
//		File f = new File(filePath);
		Calendar cal = Calendar.getInstance();
//		long time = f.lastModified();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(cal.getTime());
	}

	public static String getLatestAccessTime(String filePath) {
		Path p = Paths.get(filePath);
		Calendar calendar = Calendar.getInstance();
		BasicFileAttributes att;
//		Date time = new Data;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			att = Files.readAttributes(p, BasicFileAttributes.class);

			calendar.setTimeInMillis(att.lastAccessTime().toMillis());
//			time = calendar.getTime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取文件的属性
		return formatter.format(calendar.getTime());
	}
	
}
