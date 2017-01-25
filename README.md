# 项目简介 #
本项目是应用市场的一个示例项目，展示了一个应用市场基本的功能：应用展示，应用分类，应用详情，应用下载等。

# 学习目标 #
* 自上而下的代码抽取
* 网络框架Retrofit使用
* 多线程下载

# 服务器搭建 #
* 文件说明
	* GooglePlayServer:java ee工程,我们的服务器
	* GooglePlayServer.war:java ee工程的war包形式
	* WebInfos:资源文件
	* GooglePlayServerAndroid:手机端的服务器,可以运行servlet

* 搭建方式:
	* war包方式:
		1. 把war放到tomact的webapps目录下面就可以,然后启动tomcat会自动解压war包.
		2. 启动tomcat,自动解压war包,并运行程序 
		3. 修改`webapps\GooglePlayServer\WEB-INF\classes`目录下system.properties为`dir=C:\\Users\\Leon\\Desktop\\GooglePlay\\server`(`WebInfos`所在的目录),需要注意要么用"/"或者"\\"
		4. 在pc和手机上分别验证 
		5. `注意:tomcat必须使用7以上版本`

	* 源码形式.
		1. 用java ee 版eclipse导入工程GooglePlayServer.
		2. 修改`webapps\GooglePlayServer\WEB-INF\classes`目录下system.properties为`dir=C:\\Users\\Leon\\Desktop\\GooglePlay\\server`(`WebInfos`所在的目录),需要注意要么用"/"或者"\\"
		3. 部署java ee工程到tomcat,然后运行
		4. 在pc和手机上分别验证。

# 项目初始化 #
* BaseActivity
* BaseFragment
* ButterKnife集成
* Git初始化

# 主界面 #
## 侧滑菜单 ##


## ActionBar ##
###介绍
* Action Bar 是Google 在Android 3.0之后推出的一种全新用户操作方式
* 目的是用来替换掉菜单按键功能，长按操作功能，提供一种全新的操作体验
* 统一界面.方便开发

![icon](img/actionbar.png)

### ActionBar 4大部分
![icon](img/action_bar_basics.png)

1. **app 图标**：包含了 图标，主标题，副标题，回退部分
2. **视图控件**：允许用户切换视图。视图切换控件的样式有`下拉菜单`或`选项卡`控件,d对应了,`标准`，`list`，`tab`,3种导航模式
3. **操作按钮**：在操作栏里，展示出你的 app 中最重要的操作。不能展示在操作栏里的操作，可以自动移到“更多操作”里
4. **更多操作**：把不常用的操作放到更多操作里

### 如何支持actionbar?
1. android sdk 3.0之后默认支持actionBar
2. 市面上也有开源的actionbarSherlock可以支持ActionBar
3. google 2013 i/o大会.在v7(api level 7_android2.1以上)中对actionbar进行了兼容;

![3.0以上默认就是actionBar](img/actionbar_compat.png)

### ActionBar基本使用
用来显示标题和回退的相关Api的使用

	// 获取ActionBar
	mActionBar = getSupportActionBar();

	mActionBar.setTitle("MainTitle");// 设置主title部分
	mActionBar.setSubtitle("SubTitle");// 设置子title部分

	mActionBar.setIcon(R.drawable.ic_launcher);// 设置应用图标

	mActionBar.setDisplayShowTitleEnabled(true);// 设置菜单 标题是否可见
	mActionBar.setDisplayShowHomeEnabled(true);// 设置应用图标是否
	mActionBar.setDisplayUseLogoEnabled(false);// 设置是否显示Logo优先
	mActionBar.setDisplayHomeAsUpEnabled(true);// 设置back按钮是否可见

### DrawerLayout

1. 添加DrawerLayout
2. 添加ActionBarToggle
	
		mToggle = new ActionBarDrawerToggle(MainActivity.this,//上下文
					mDrawerLayout, //DrawerLayout
					R.drawable.ic_drawer_am,//图标
					R.string.open, //
					R.string.close);
	
		mToggle.syncState();
	
		mDrawerLayout.setDrawerListener(mToggle);	

### PagerSlidingTabStrip ###

#### Android Studio 导入PagerSlidingTabStrip项目 ####
* 修改Gradle版本
* 修改Build Tool 版本
* 修改Gradle wrapper版本（参考GooglePlay配置）

####PagerSlidingTabStrip源码分析####

#### Google Play中集成PagerSlidingTabStrip ####

#### PagerSlidingTabStrip的使用
参考官方README.md

### 开源PagerSlidingTabStripg功能拓展
* 添加四个属性

			<attr name="pstTabTextSize" format="dimension" />
		    <attr name="pstTabTextColor" format="color" />
		    <attr name="pstSelectedTabTextSize" format="dimension" />
		    <attr name="pstSelectedTabTextColor" format="color" />
	
			//修改源码
			/**---------------add begin---------------**/
			private int selectedPosition = 0;
			
			private int tabTextSize = 12;
			private int tabTextColor = 0xFF666666;
			
			private int selectedTabTextSize = 12;
			private int selectedTabTextColor = 0xFF666666;
			/**---------------add end---------------**/
	
			/**---------------add begin---------------**/
			tabTextSize = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_heimaTabTextSize, tabTextSize);
			tabTextColor = a.getColor(R.styleable.PagerSlidingTabStrip_heimaTabTextColor, tabTextColor);
	
			selectedTabTextSize = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_heimaSelectedTabTextSize,
					selectedTabTextSize);
			selectedTabTextColor = a.getColor(R.styleable.PagerSlidingTabStrip_heimaSelectedTabTextColor,
					selectedTabTextColor);
			/**---------------add end---------------**/
			
			/**---------------add begin---------------**/
					if (i == selectedPosition) {
						tab.setTextColor(selectedTabTextColor);
						tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTabTextSize);
					}
			/**---------------add end---------------**/
	
			/**---------------add begin---------------**/
				selectedPosition = position;
				updateTabStyles();
			/**---------------add end---------------**/


###FragmentPagerAdapter和FragmentStatePagerAdapter的区别
* FragmentPagerAdapter:该类内的每一个生成的 Fragment 都将保存在内存之中，因此适用于那些`相对静态的页，数量也比较少`的那种；如果需要处理有很多页，并且数据动态性较大、占用内存较多的情况，应该使用FragmentStatePagerAdapter

* FragmentStatePagerAdapter:该 PagerAdapter 的实现将只保留当前页面，当页面离开视线后，就会被消除，释放其资源；而在页面需要显示时，生成新的页面(就像 ListView 的实现一样)。这么实现的好处就是当拥有大量的页面时，不必在内存中占用大量的内存。


###使用了FragmentAdapter,调用notifyDataChanged会无效,思考下为什么,也可以上网查查资料

# 网络层 #
## GooglePlay的网络数据 ##

