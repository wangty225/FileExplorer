package main;


// src中的源文件，共计约2700行。
public class MainThread {
	public static void main(String [] args) {
		RunExplorerThread r = new RunExplorerThread();
		r.start();
	}
}
