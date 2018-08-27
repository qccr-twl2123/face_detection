# 发布脚本
```text
#!/bin/sh
cd /home/workspace
rm -rf face_detection
#杀掉进程
pid=`ps -ef | grep face_detection-1.0.0.jar | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
   kill -9 $pid
fi

#下载资源
git clone  https://github.com/qccr-twl2123/face_detection.git
cd /home/workspace/face_detection

#编译资源
mvn spring-boot:run
echo "release success...."
```