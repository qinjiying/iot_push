# iot_push
基于netty+mqtt 3.1.1协议开发的物联网消息推送框架。

项目目录
mqtt简介
功能
如何使用
更新日志
基于netty4.1-final+springboot实现的 Mqtt 3.1.1 物联网标准推送协议

mqtt简介
MQTT 协议是 IBM 开发的即时通讯协议，相对于 IM 的实际上的准标准协议 XMPP 来说，MQTT 更小，更快，更轻量。MQTT 适合于任何计算能力有限，工作在低带宽、不可靠的网络中的设备，包括手机，传感器等等。

功能
## 服务端 example(iot_push_server_starter_test)

已实现：
发布订阅功能
遗言通知
会话session数据
发布保留消息
主题过滤（/test 会接受到 /test/yy 的主题消息）
实现标准的 qos0 qos1 qos2消息确认机制
ssl加密
支持ws协议
集成spring容器
如何使用
安装lombok插件
下载源码
springboot
jdk8
导入IDE
配置yml 或者properties 文件 yml
简单测试：运行包 test 下的 测试 文件，即可开启测试客户端。
压力测试：推荐使用jmeter 的mqtt插件 插件
## 客户端 example(iot_push_client_starter_test)

基于springboot 配置方式yml

配置实现 MqttListener 类并添加MqttMessageListener指定订阅的topic跟服务质量

@Autowired Procuder producer 即可使用;

编码 java
