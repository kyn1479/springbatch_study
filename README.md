# springbatch_study
此项目为springbatch的基本使用demo，以下为各个使用案例解读：
## 1.项目搭建
构建普通Springboot项目，添加依赖：spring-boot-starter-batch 此项目使用Mybatis作为数据存储库，所以添加依赖：mysql-connector-java、spring-boot-starter-jdbc
持久层使用SpringData Jpa，所以添加依赖：spring-boot-starter-data-jpa，其他辅助依赖包见pom.xml,
yml文件配置数据源datasource、Jpa、Batch等信息，

注意以下配置：

     batch:initialize-schema: always 在项目启动时自动创建Batch依赖数据表，

     job:enabled: false 关闭Springboot项目启动时运行批处理任务，使用单元测试启动各个任务！ 

主要由以下表： 

* batch_job_instance 实例表 
  
      重点字段解读：JOB_KEY：JobParameters的序列化数值，在数据批处理概念中JobInstance相当于Job+JobParameters，他用于标记同一个Job不同的实例。

* batch_job_execution

* batch_job_execution_context

* batch_job_execution_params

* batch_step_execution

* batch_step_execution_context 
  
以及其他三张 seq表，

具体关系自行百度，此项目为具体使用案例，概念问题自行百度！

## 2.Demo解读


* （1）a_hello_world 入门级案例 Job、Step的创建

      .incrementer(new RunIdIncrementer()) 作用：批处理Job根据job+jobParameters这两个参数组合来确定唯一任务，当初次执行完任务后，再次执行则会出现：Step already complete or not restartable

* （2）b_hello_world_flow 入门级案例 Flow的创建

*  （3）c_hello_world_split 入门级案例 并发执行的创建

*  （4）d_hello_world_decider 入门级案例 Decider的创建

* （5）e_hello_world_listener 入门级案例 Listener的创建

* （6）f_hello_world_paramter 入门级案例 Paramer传参

* （7）g_hello_world_item_reader 入门级案例 Reader的创建，从普通文件以、数据库读取数据

* （8）h_hello_world_item_writer 入门级案例 Writer的创建，将数据写入普通文件、数据库

* （9）i_hello_world_item_processor 入门级案例 Processor的创建，多个processor聚合

* （10）j_hello_world_partitioner 入门级案例 Partitioner的创建，实现数据分片

