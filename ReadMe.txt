
#上传android自定义aar库到jCenter  JFrog步骤：

1-> local.properties 文件修改jcenter用户名及APIKey

2-> Module / build.gradle 文件增加依赖：
//A、引用插件
	apply plugin: 'com.github.dcendents.android-maven'
	apply plugin: 'com.jfrog.bintray'

//B、此处是为了将发布的代码逻辑封装到upload.gradle脚步中去了，这样build.gradle更简洁，代码维护性更高
	apply from : 'upload.gradle'

3-> 工程根目录build.gradle文件增加依赖：
	classpath 'com.github.dcendents:android-maven-gradle-plugin:1.4.1'
	classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7.3'


4-> Module / 目录下增加upload.gradle上传脚本文件(内容无需修改)

5-> Module / 目录下增加gradle.properties 文件，里面定义upload.gradle脚本中需要的宏


==================================================================================================


#工程根目录或AS命令行，依次执行如下命令： 

./gradlew clean install


./gradlew bintrayUpload




