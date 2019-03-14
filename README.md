# Azure-devops-demo [![Build Status](https://dev.azure.com/1909025079/azure-devops/_apis/build/status/azure-devops-Maven-CI?branchName=master)](https://dev.azure.com/1909025079/azure-devops/_build/latest?definitionId=4&branchName=master)<br>
### 使用azure devops结和github自动连续集成部署的案例spring boot项目
(此项目只是一个普通的springboot web项目,并未引入其他第三方依赖)

-----可能是最详细的Az Devops部署java项目的文章,如果你觉得还行,就点点**star**吧 ^ @  ^----
-----我是在b站看一位大佬的视频启发才想玩玩Azure Devops的, up主是Anduin2017他的视频质量都不错,欢迎大家观看.

**提示**:
此文是本人打着想玩一玩devops的想法，来记录的，用的是最直接简单的方法来实现持续部署的,适合个人玩玩demo，内容不一定适合实际生产，大家就当逛逛就行

---
好吧,切入正题,先简单讲讲Azure Devops是什么:
**简介**: 

- [Azure Devops](https://azure.microsoft.com/zh-cn/services/devops/)是MS Azure下的产品,提供devops自动集成和部署的服务平台:
![是MS Azure下的产品,提供devops服务的平台](https://upload-images.jianshu.io/upload_images/14511997-281ebbae89d704b3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)-----近年来devops的理念越来越被更多的人所提及, 敏捷开发模型下的CI连续集成, CD持续部署的概念也逐渐走向自动化, 而促成这些自动化的工具和服务又有许多种, MS旗下的Azure Devops就是为了解决集成部署自动化的难题的服务之一,也就是我们今天要讲的主角.
---
正式进入主题:
那么要完成使用Azure Devops自动集成部署一个Springboot项目我们需要那些准备工作呢?

**第1步:**

1.因为我们需要有自动部署连接的云服务器, 当然先要有一台可远程连接的云服务器啦，这里我选择了[腾讯的学生体验服务器](https://cloud.tencent.com/act/campus?fromSource=gwzcw.628615.628615.62861)，(当然你也可以使用阿里的)，在使用微信扫码支付0.01元实名认证后，我免费获取了一个体验服务器，注意这里的root用户的ip和密码，我们在后面需要使用到![](https://upload-images.jianshu.io/upload_images/14511997-489ddfa6b4f5889d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2.给你的云服务器安装JDK,并配置好环境变量,
因为我买的云服务器莫得安装JDK,所以我们需要安装jdk,(如果你已经安装jdk,可以跳过这步)这里你可以选择手动ssh连接你的云服务器安装.


**第2步**: 
需要有一个[Azure Devops](https://azure.microsoft.com/zh-cn/services/devops/)的账号,这个账号使用你的微软账户登录/邮箱注册都行, 且是免费的.

注册登陆后, 你需要创建一个组织和项目(例如我的组织190...项目名azure-devops,且是public的):![](https://upload-images.jianshu.io/upload_images/14511997-09be0a189ecc2723.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**到此我们的准备工作已经做好**

**第3步:**

在本地新建一个以maven管理的Springboot Start Web项目,并在Controller层写一个测试连接的Http api接口,在本地运行成功后, 提交到github仓库
:![](https://upload-images.jianshu.io/upload_images/14511997-0f6a9bc52bdcd899.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**第4步(使用Azure Devops的Pipelines构建CI管道)**

回到我们在第2步的页面
点击进入azure-devops项目, 选择pipelines新建一个管道
(对于新的Azure DevOps帐户，这将自动带您进入使用YAML构建管道体验,我们要提前在  点击头像|Preview features|New YAML pipeline creation experience关闭这个选项)![](https://upload-images.jianshu.io/upload_images/14511997-bcd397430c4fefba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

选中github,随便取一个连接的名字,点击验证![](https://upload-images.jianshu.io/upload_images/14511997-9d34402e6958d676.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

验证完你的github账户后,就可以选择我们刚push到GitHub的springboot项目加入自动CI集成的管道:![](https://upload-images.jianshu.io/upload_images/14511997-9b762e918008469d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击继续后,我们找到Maven的编译构建模板任务组,点击应用![](https://upload-images.jianshu.io/upload_images/14511997-33167cfc82771502.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以看见中间的3个任务就是maven模板的任务,分别是在代理主机上根据pom测试构建项目,复制构建好的项目,上传构建的项目drop到CI管道,右边是选择代理的主机系统,和pom文件位置![](https://upload-images.jianshu.io/upload_images/14511997-bd990fb89fd91b80.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


点击Triggers配置我们的CI管道会根据github的提交自动集成测试,并且保存任务排队![](https://upload-images.jianshu.io/upload_images/14511997-7f0b8cbee4a8dfe2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


点击左边栏的Builds可以看到我们正在排队运行的集成任务![](https://upload-images.jianshu.io/upload_images/14511997-fdaf5e01216cf433.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点进我们的build任务中,等待云端代理主机运行任务后,可以看到我们所有的集成测试build工作已经在代理主机上运行完成![](https://upload-images.jianshu.io/upload_images/14511997-15fa14164f1ab035.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击Maven项可以查看日志,提示构建成功![](https://upload-images.jianshu.io/upload_images/14511997-781b654e6902b35b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击Copy Files项查看日志,可以看到我们项目的jar包在代理主机上已经复制移动到某工作目录![](https://upload-images.jianshu.io/upload_images/14511997-b47eca6e2870febc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击Publish Artifac项查看我们在代理主机工作目录中的项目文件已经上传到我们的CI管道目录下![](https://upload-images.jianshu.io/upload_images/14511997-29536bd18291bc39.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---
**到此我们的Springboot项目已经通过CI管道自动集成了,接下来就是准备发布Releases工作了:**

1.配置devops的连接服务通过ssh连接到我们的云服务器![](https://upload-images.jianshu.io/upload_images/14511997-291583baa531ee5f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

其中host name是云服务器的公网ip,密码是云服务器的初始root账户密码![](https://upload-images.jianshu.io/upload_images/14511997-f6ff0b852ddcf642.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


准备工作好了后
回到Devops点击右边的Releases,选择New一个发布管道,选择一个空的Empty job工作![](https://upload-images.jianshu.io/upload_images/14511997-b5b40d174bdcb153.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

选择我们要发布的项目,选择我们从CI管道build的项目,点击添加![](https://upload-images.jianshu.io/upload_images/14511997-9a76ab881be49ab0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

设置我们的发布管道的触发条件是自动![](https://upload-images.jianshu.io/upload_images/14511997-e0d370e5406df4c4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

给部署的第一Stage1的工作台更名为copyJAR,并点击3进入设置任务![](https://upload-images.jianshu.io/upload_images/14511997-b48aac3f844b6c22.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


进入后给工作添加任务,选择添加ssh复制文件任务![](https://upload-images.jianshu.io/upload_images/14511997-4a825e11a5aa1a4f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击编辑任务,其中SSH service connection填我们在前面配置的连接云服务的ssh连接名,Source folder选择到..../target这一级,因为target下存放的是我们的jar包文件, **表示该目录下的所有文件, 最后目标目录选择/tmp, 这样jar包文件就可以发送到云服务器主机的/tmp下(注意目标目录最好是所有人可操作权限(文件是绿色的),以免出现无权操作报错)![](https://upload-images.jianshu.io/upload_images/14511997-d40e8307942c3c4d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)![](https://upload-images.jianshu.io/upload_images/14511997-66bd98ba3312ab6b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

再点击Tasks左边的选项pipeline回到工作台,并再新建一个工作命名为RunSpringboot![](https://upload-images.jianshu.io/upload_images/14511997-e24e6d112cf88f5c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

进入RunSpringboot添加任务,步骤和上个工作一样,这里我们添加ssh远程执行命令![](https://upload-images.jianshu.io/upload_images/14511997-a245ee1c4a895152.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

添加远程运行jar包的命令来运行我们的项目(注意由于ssh远程直接运行命令可能会读取不到云主机的环境变量配置,导致提示java命令未找到,所以这里写成全路径来执行jar文件), 我们的第一条命令:

`kill -9 $(netstat -nlp | grep :8080 | awk '{print $7}' | awk -F"/" '{ print $1 }')`是表示查询8080端口的进程id并kill停止它, 以免在发布新版本的时候提示端口被占用, 第二条命令:

`nohup /usr/java/jdk1.8.0_201/bin/java -jar /tmp/azure-demo-0.0.1-SNAPSHOT.jar  > log.file  2>&1 &`表示后台启动运行jar文件,并把日志打印到log.file文件中

最后点击Save保存工作![](https://upload-images.jianshu.io/upload_images/14511997-e45bc2870f79626d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



**到此我们的CD持续部署的管道就搭好了, 我们去先手动发布一次:**

点击左边Releases选项,点击Create a release,点击Create![](https://upload-images.jianshu.io/upload_images/14511997-91434eb7988685c2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以看到我们的发布工作已经在排队运行!![](https://upload-images.jianshu.io/upload_images/14511997-a147f225d6fd2ee6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击进入查看发布任务进度,我们的RunSpringboot,的任务已经成功!![](https://upload-images.jianshu.io/upload_images/14511997-4b57013d75d1f176.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


我们在浏览器输入 云主机ip:8080/test/sayHi 访问, 果然我们的项目已经启动了!!!![](https://upload-images.jianshu.io/upload_images/14511997-5d2a70bcef908540.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---
**接下来我们将在本地修改代码并提交一次到github,以验证我们的自动集成部署是否已经成功**

1.在本地修改,并提交push到github仓库:![](https://upload-images.jianshu.io/upload_images/14511997-22f84624933d60a4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![](https://upload-images.jianshu.io/upload_images/14511997-c87c19ed60972a3f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2.查看devops的构建管道,发现我们刚才的提交已经在自动集成测试构建了:![](https://upload-images.jianshu.io/upload_images/14511997-39e5b25f8e66fb29.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


3,集成测试完成后,点击到发布管道,发现我们新的提交版本已经在自动部署了:![](https://upload-images.jianshu.io/upload_images/14511997-5d924e14796bf7b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

4.部署完成后,再次访问 云主机ip:8080/test/sayHi, 更新的版本已经发布运行成功!!!![](https://upload-images.jianshu.io/upload_images/14511997-dc08145bfc527bb6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



---


**到此,本文章就结束了,我们实现了在本地更新代码,修改成一个稳定的版本后,直接push到github的主分支**(或通过dev分支合并到主分支)**,我们就可以回家睡觉了,而我们此次版本的修改会通过Azure Devops自动集成发布,这样所有连接我们的服务的请求就都有了这次新的更新.**

(我在写演示的过程中,并不是一次成功的,我重新修改任务命令发布了几次,所以发布的版本号来到了18,但本文章是最后我完全可以一次运行成功的截图版本,所以在发布的版本号上有点不同,不过不影响大致流程)**

**其实Azure Devops的玩法还可以有许多种,它支持很多语言框架和容器的自动集成部署,比如ASP.NET, Node.js的NPM, Python,  Docker容器等,并且可定制性高,各位大佬可以根据[官方文档](https://docs.microsoft.com/zh-cn/azure/devops/pipelines/?view=azure-devops)自行摸索,并且结合MicroSoft 的Azure云计算服务平台还有更丰富的玩法,不过由于Azure的使用有点复杂,这篇文章中我也就没有用到Azure的服务**(主要是注册Azure的国际账户需要VISA信用卡,我没有,而且我也不会玩Azure😂),**而是使用了最简单的云服务ssh连接来完成自动集成部署.**

**在发布项目版本更新迭代的过程中,我们的服务进程会停止,再重新启动新服务,这个过程就会短暂停止服务,而如果要实现不停服更新,就需要设置发布管道任务,详情见 这篇[官方文档](https://docs.microsoft.com/zh-cn/azure/devops/pipelines/release/?view=azure-devops),不过我们的演示只是demo,也用不着复杂发布**(其实不会哈哈)**,如果各位大佬有什么简单的方法,欢迎留言...**


