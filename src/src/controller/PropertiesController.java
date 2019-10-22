package controller;


import model.FileModel;
import model.PropertiesWindow;

public class PropertiesController {
	private PropertiesWindow propWindow;
	
	public PropertiesController(PropertiesWindow propWindow) {
		// TODO 自动生成的构造函数存根
		this.propWindow = propWindow;
	}
	
	void IniWindow() {
		propWindow.setVisible(true);
	}
	
	public void UpdateWindow(String path) {
		FileModel file = new FileModel(path);
		if(path.charAt(1) == ':') {
			String disk = "本地磁盘（"+path.charAt(0)+"）";
			propWindow.NameField.setText(disk);
		}
		else {
			propWindow.NameField.setText(file.getName());
		}
			
		
		propWindow.Type.setText((file.isDirectory())?"文件夹":"文件");
		propWindow.Size.setText(file.getFileSize());
		propWindow.Location.setText(path);
		String Create_Time = FileModel.getCreateTime(path);
		String Modify_Time = FileModel.getModifiedTime(path);
		String Last_Access = FileModel.getLatestAccessTime(path);
		
		propWindow.CreateTime.setText(Create_Time);
		propWindow.ModifyTime.setText(Modify_Time);
		propWindow.AccessTime.setText(Last_Access);
		
	}
	
}
