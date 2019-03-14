# Azure-devops-demo [![Build Status](https://dev.azure.com/1909025079/azure-devops/_apis/build/status/azure-devops-Maven-CI?branchName=master)](https://dev.azure.com/1909025079/azure-devops/_build/latest?definitionId=4&branchName=master)<br>
### 使用[azure devops](https://azure.microsoft.com/zh-cn/services/devops/)结和github自动连续集成部署的案例spring boot项目
(此项目只是一个普通的springboot web项目,并未引入其他第三方依赖)

### 完整的[教程步骤请点这里看我的简书文章](https://www.jianshu.com/p/6eb5e388c112),或直接搜索我的简书:pjqdyd 进入主页查看文章.

-----可能是最详细的Az Devops部署java项目的文章,如果你觉得还行,就点点**star**吧 ^ @  ^----

**看完文章,我们就可实现在本地更新代码,修改成一个稳定的版本后,直接push到github的主分支后**(或通过dev分支合并到主分支)**,我们就可以回家睡觉了,而我们此次版本的修改会通过Azure Devops自动集成发布,这样所有连接我们的服务的请求就都有了这次新的更新.**

**几张截图:**

CI的构建管道已经自动集成构建的项目:
![](https://upload-images.jianshu.io/upload_images/14511997-dd6930eeb0ff7ba4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

构建项目成功的摘要:
![](https://upload-images.jianshu.io/upload_images/14511997-db47e0e6cc8477fd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

已自动发布的项目版本:
![](https://upload-images.jianshu.io/upload_images/14511997-78c50e2defae72aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



**提示**:
此文是本人打着想玩一玩devops的想法，来记录的，用的是最直接简单的方法来实现持续部署的,适合个人玩玩demo，内容不一定适合实际生产.
