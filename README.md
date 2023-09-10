# FileExplorer
基于 java swing 开发的资源管理器。使用 java swing 美化包beautyeye对原生的 java swing 优化。

# 实习内容
### 实现一个文件系统
#### 1. 功能要求 
- 界面上显示树形目录结构
- 根节点是“我的电脑”
- “我的电脑”下有几个盘符（C、D、E等）就有几个子节点，递归显示文件系统下的所有文件信息（分支可以是目录也可以是文件，叶子节点都是文件）
- 能够创建目录、创建文件、删除目录、删除文件、复制文件、粘贴文件
   
#### 2. 界面要求：界面要友好，让用户操作使用起来非常方便
---

一、实习题目与要求：<br>
> 实现一个文件系统<br>
> （1）功能要求 <br>
> a）界面上显示树形目录结构<br>
> b）根节点是“我的电脑”<br>
> c）“我的电脑”下有几个盘符（C、D、E等）就有几个子节点，递归显示文件系统下的所有文件信息（分支可以是目录也可以是文件，叶子节点都是文件）<br>
> d)能够创建目录、创建文件、删除目录、删除文件、复制文件、粘贴文件<br>
>    
> （2）界面要求：界面要友好，让用户操作使用起来非常方便

二、需求分析<br>
   1. 一点想法<br>
初拿到这题的时候，都会觉得，这个简单！做过！<br>
但是！想想上学期的java作业我的“FileExplorer V1.0”，用java swing 写的，没什么架构，功能单一，甚至有些功能上还存在着潜在的缺陷。所以这次我打算头开始写。<br>
选择语言方面，由于考虑安全性的JavaScript 已逐渐抛弃的本地文件访问，所以做成静态网页就显得不合乎实际。<br>
由于题目要求为桌面应用程序开发，考虑三种方式：① python + QT ② C++ 和 QT ③Java 和 Swing。
本项目选择方式③。<br>

几个方面：<br>
> i. 首先是文件结构，改用MVC结构，同样基于java 编程 和Swing GUI。按编程项目规范优化目录结构（ src(m,v,c),  image,  doc,  lib,  … ）<br>
> ii. 其次是界面美化，做出“扁平化效果”，以显得更简洁。<br>
> iii. 另外，多线程思想。不会使文件复制移动的时候GUI假死，增加进度条显示，窗口多开等等。<br>
> vi. 异常处理的完善，和信息校验等，保证程序相对健壮。<br>
> v.  尝试使用算法，如递归实现删除文件夹，尝试BFS做一个文件的搜索<br>


（1）、问题描述<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结合题目，类比windows资源管理器，分析主要核心功能有：<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;①文件的复制，剪切，粘贴。（多线程）<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;②文件（夹）的删除。（递归算法实现）<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;③新建文件，文件夹。<br>
 其他功能及扩展功能：<br>
 文件（夹）属性查看；在某一目录下根据文件名查找文件（夹）；程序的“多开”；根据给定的目录跳转到指定文件（夹）；目录的上一页（前进），下一页（后退）；菜单栏的实现；通过键盘的快捷键操作；

（2）、系统环境

    由于java是基于JVM的，所以运行无特别系统限制。通用于windows。linux上需要对代码稍加修饰。

（3）、运行要求

        JDK 1.8及以上java环境；
        依赖的外部包：
		    Beautyeye_inf.jar     ——java swing 风格包。

三、软件设计与实现

（1）、数据结构与存储结构的设计

&ensp;&ensp;&ensp;&ensp;由于本系统主要是对文件操作，需要的数据即硬盘文件，硬盘文件采用的树状目录结构。所以可以打造一颗“JTree”，用来存储文件结构；通过“JTable”显示文件夹下的所有文件及一些基本信息。
&ensp;&ensp;&ensp;&ensp;理论上，可以通过递归的方法将文件初始化进Jtree，但实际操作，会因为硬盘文件内容众多，导致JVM内存不足，或者运行出来异常卡顿，程序容易死掉，无法实现相关功能，所以需要为了更好的效果，需要另寻它法。
&ensp;&ensp;&ensp;&ensp;这里我才用的动态加载目录节点的信息。当左侧的JTree的节点被点击时，分析目录结构，更新左下角的信息“该文件夹下有多少子项目”，实现预加载。当结点被展开时，所有子项目被添加到点击的节点上，达到节点的更新。节点折叠时，移除所有子节点，以减少资源占用，同时更方便更新UI。

（2）、算法设计

&ensp;&ensp;&ensp;&ensp;①文件的定位。树层序遍历的思想和寻找根节点到叶子节点的思想。首先对需要定位的文件路径进行分析，切割成小块，每一块代表一个节点名称（文件名称），每次遍历一级目录，如果遇到对应的文件，则将该节点展开。依次循环，处理完所有“块”之后，即可定位到目标文件，同时完成根节点到目标文件的动态构建。

&ensp;&ensp;&ensp;&ensp;②文件的搜索。搜索有三种方式：一种是仅对当前目录低一级的子项目搜索，时间复杂度O(n)；第二种是对当前文件夹所有子文件进行搜索，需要递归，可以选择BFS，或者DFS的算法实现；第三种是全硬盘下的文件搜索。（二，三方法最好采用多线程，避免搜索过多占用主进程CPU资源，页面假死）

&ensp;&ensp;&ensp;&ensp;③文件的复制（核心）。采用字节流的方式，每次循环：先读入一定数量的文件字节，然后写入另一个文件，之后累加计算已经复制的百分比，更新进度条进swing的EventQueue队列，进入下一次循环。


   多线程调用方式：

&ensp;&ensp;&ensp;&ensp;主进程中再新开一个进程，执行自己写的paste()函数，paste()函数首先开一个创建JProgressBar的窗口Gui进程run0()并通过SwingUtilities.invokeLater(run0);将其加入Swing的EventQueue()中并执行。然后创建一个用来更新Ui的进程run()等待调用,然后paste()开始在while(){}中执行文件的读写，每读写一次，执行一次run()，Ui即可更新一次。<br>
&ensp;&ensp;&ensp;&ensp;首先在外部定义两个进程。一个是JProgressBar的窗口Gui进程,另一个是copy后台进程。

&ensp;&ensp;&ensp;&ensp;线程调用关系：
![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/1_thread.png)

（3）、模块设计 & 类的函数成员和数据成员设计

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目目录：
![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/2_file.png)

MVC架构：

主要思想：将窗口的每个部分模块化，每个小模块对应一个controller。

（1）、FileModel为一个file属性的扩展，除了file已有的函数，增添了一些属性，如获得文件大小（getFileSize(), 返回值为xxxB, xxxKB, xxxMB, xxxGB, xxxTB的字符串），获得文件的创建时间getCreateTime(),修改时间getModifiedTime()， 访问时间getLatestAccessTime()等等。

FileModelController()则包含一些静态成员方法，如:<br>
    文件（夹）的粘贴paste()，<br>
    删除delete(), deleteDir()，<br>
    新建CreateMyFile(), CreateMyDir()等。<br>

（2）、FileTablePanel为一个Panel属性的扩展类，返回设计好的包含jTable的Jpanel。

FileModelController()则包含一些可以用于Table更新显示成员方法。
如：IniTable(),	SetTable()等。

（3）、FileTreePanel为一个Panel属性的扩展类，返回设计好的包含jTree的Jpanel。FileTreeController()则包含一些可以用于Tree更新显示成员方法。
如：树的节点展开监听操作, 树的节点折叠舰艇操作等。等。

（4）MyMenuBar, MyPopupMenu, NavigatePanel类比同上。

（5）PropertiesWindow，文件的属性窗口，通过PropertiesController更新显示。

（6）AboutWindow是menuBar上“关于本系统”功能显示窗口。（本打算通过解析docs目录下的About.json文件设置相关内容，已达到可以从外部直接更新的办法，该程序中尚未使用此方式。）

（7）Help 读取docs目录下的Help文本文件，显示在界面上。可以以用来包括系统介绍，功能使用说明，快捷键等。做成外部文件读取的方式，便于更新维护升级。

另：一些组件的监听操作，添加在MainWindows中，调用controller中相应方法，传递相应的参数，实现功能。

![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/3_mvc.png)

（4）、界面设计及其它模块设计与实现

主页


![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/demo_01.png)


弹出菜单页

 
 ![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/demo_2.png)


文件复制页面（多线程，可以同时复制多个）   


![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/demo_3_1.png)


文件属性页面


![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/demo_3_2.png)


打开多级窗口，允许程序多开(多线程)。


![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/demo_4.png)
 

关于本系统                                  系统帮助
 

![Image text](https://raw.githubusercontent.com/Mr-Toxic/FileExplorer/master/image-folder/demo_5.png)


注：左下角会提示当前选中的目录有多少“一级子项目”



四、调试信息

（1）、主要问题及解决办法。

问题1：某些组件使用updataUI()虽然能更新成功，但是会报错。

原因分析解决：Swing 不是线程安全的，采用EventQueue()，一个主进程，只有加入队列的操作才会被调用。所以选择更改代码写法：
SwingUtilities.invokeLater(xxx.UpdateUI());

问题2：复制或者文件时，界面假死，只有复制或移动结束，才会更新一次UI，JProgressBar没有中间过程。

原因分析解决：因为复制或者移动涉及到的粘贴，会大量读写文件，大量占用资源，再加上Swing不是线程安全的，造成线程阻塞，UI停止更新。而复制或移动结束后，CPU资源得以释放，再来处理Swing的UI更新。解决：把耗资源的线程单独拿出来放在后台执行，通过另一个线程不断的SwingUtilities.invokeLater(run)
更新前端UI， 页面就不会卡死，可以看到进度信息了。线程调用模式参考本文档中“算法设计③”中的图形。

（2）、对设计和编码的回顾讨论和分析；

这次阶段性阶段性项目实训，让我更加认识到MVC的重要性，以及设计MVC的复杂，需要多多实践培养这种思想，使代码更标准化，更易懂，更易维护。
同时我认识到多线程在处理并发任务，前后端交互的重要地位！
还有，学习算法，不仅是一种思想，更要能够实现，能够应用！

（3）、算法的时间和空间复杂度的分析，进一步改进等 

本系统目前涉及到算法的地方不是很多，也不复杂。目前的搜索还是按照当前文件夹搜索第一级子文件的方式，复杂度为O(n)。后面扩展开发的情况下，可以在“查找文件”上做文章，选择一种比较高效的文件搜索模式，甚至建立本地索引数据库以提升速度等，都是有可能实现，同时需要复杂算法深究的地方。

（4）、总结

这个项目，我已基本完成了FileExplorer V2.0的开发。相比去年不过千行的V1.0，不夸张的说这个有着近3000多行的V2.0版本已经是“质的飞跃”了。
经过这近两周的上机实践和紧张开发当中，我收获良多。首先在知识应用上，有了很大的提高，其中也包含了我对swing，mvc，多线程，算法等的新认识。阶段性项目实训是一个费城有意义的一门课，与其他以讲授为主的课程，这门课非常讲求实践，只要愿意认真去做，可以很好很快的提升自己的能力水平。


