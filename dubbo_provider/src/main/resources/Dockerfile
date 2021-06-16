FROM java:8
#维护者
MAINTAINER caofei <caofei@sensetime.com>
#VOLUME /tmp
#创建一个目录存放jar包和配置
RUN mkdir -p /executive/jars
ADD *.jar /executive/jars/sentinel-dashboard-1.8.1.jar
#添加进入docker容器后的目录
WORKDIR /executive/jars
#修改文件的创建修改时间
RUN bash -c 'touch /executive/jars/sentinel-dashboard-1.8.1.jar'
#设置一个时区的环境变量
ENV TZ "Asia/Shanghai"
EXPOSE 9090
ENTRYPOINT ["java","-Xms1024m","-Xmx1024m","-jar","/executive/jars/sentinel-dashboard-1.8.1.jar"]
