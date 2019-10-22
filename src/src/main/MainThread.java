package main;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年7月8日 下午2:29:46 
* 类说明  src中源文件，共计约2700行。
*/
public class MainThread {
	public static void main(String [] args) {
		RunExplorerThread r = new RunExplorerThread();
		r.start();
	}
}
