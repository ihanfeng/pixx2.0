FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /pigx-tx-manager

WORKDIR /pigx-tx-manager

EXPOSE 5004
EXPOSE 9998

ADD ./pigx-visual/pigx-tx-manager/target/pigx-tx-manager.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar pigx-tx-manager.jar
