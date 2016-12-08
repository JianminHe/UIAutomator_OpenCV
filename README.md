# UIAutomator_OpenCV

Android UI自动化测试使用图像识别技术（UIAutomator + OpenCV）
从2013以来，学习移动端自动化测试已经多年。UI自动化测试技术也在不停的发展。但是，UI自动化测试与图像识别技术结合的文章，在网上比较的少见。
今天我就来介绍一下，在Android UI自动化测试中，引入开源的OpenCV库，进行图像技术的识别。

首先，要配置电脑环境 （我使用Android Studio 2.0  + Open 3.1.0 Android + 华为Mate7 ）
1.	Android Studio 中创建Android项目，并且添加UIAutomator类；
2.	配置OpenCV，具体参考 （http://blog.csdn.net/gao_chun/article/details/49359535）

然后，为了节约时间，我只讲如何使用OpenCV算法进行图片识别。进行OpenCV图像识别，需要2张图片，（原图和模板图片）
     
（原图）                            （模板图）
  将原图（ScreenShot.png）和模板图（s1.png）拷贝到手机 /mnt/sdcard目录；

在UIAutomator中创建测试类，编写一个测试方法
 

图像识别的关键方法，识别以后，会返回图片在原图中的位置；
 

下面，会自动化测试的同学就知道改怎么做了吧。通过页面返回的坐标点，使用UiDevice.getInstance().click(x, y) 函数去实现点击事件。

OpenCV的图像识别，需要保持一致的精度（比如原图和模板图片都是1080P精度），但是在实际的测试过程中，可能会出现原图是720P的，但是模板是1080P精度。这就涉及到图片的缩放。1080P转720P图片的代码如下（转的接近720图片效果） （resizeRate是转换的比例）
 
