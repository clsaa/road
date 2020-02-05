https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started.html

https://cloud.tencent.com/developer/article/1348741

## IntelliJ Platform Configuration

Explicitly setting the Setup DSL attributes intellij.version and intellij.type tells the Gradle plugin to use that configuration of the IntelliJ Platform to build the plugin project. If a local installation of IntelliJ IDEA is the desired type and version of the IntelliJ Platform, use intellij.localPath to point to that installation. If the intellij.localPath attribute is set, do not set the intellij.version and intellij.type attributes as this could result in undefined behavior.

## GRADLE下载问题

可以手动下载

gradle下载的文件所在位置: .gradle/caches/modules-2/files-2.1

里面目录其实就是下载的文件的SHA1码，是文件的SHA1

mac的话可以用shasum命令查看:shasum (文件路径)


## Plugin Class Loaders
A separate class loader is used to load the classes of each plugin. This allows each plugin to use a different version of a library, even if the same library is used by the IDE itself or by another plugin.

By default, the main IDE class loader loads classes that were not found in the plugin class loader. However, in the plugin.xml file, you may use the <depends> element to specify that a plugin depends on one or more other plugins. In this case the class loaders of those plugins will be used for classes not found in the current plugin. This allows a plugin to reference classes from other plugins.