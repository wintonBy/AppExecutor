# AppExecutor
应用线程池，可以指定任务的优先级，以及任务执行顺序

## 集成方式
### step1 Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### step2 Add the dependency
```
dependencies {
	        implementation 'com.github.wintonBy:AppExecutor:1.0'
	}
```
## 使用
### 初始化线程池
```
//提供的构造方法有（可根据需求选择）：
    constructor(corePoolSize: Int,maximumPoolSize: Int,fifo: Boolean):this(corePoolSize,maximumPoolSize,1,fifo)

    constructor(corePoolSize: Int, maximumPoolSize: Int) : this(corePoolSize, maximumPoolSize, 1)

    constructor(corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long) : this(corePoolSize, maximumPoolSize, keepAliveTime, false)


    constructor(corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long, fifo: Boolean) : this(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, PriorityBlockingQueue<Runnable>(maximumPoolSize, initComparator(fifo)), PriorityExecutor.mThreadFactory)


    constructor(corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long, unit: TimeUnit?, workQueue: BlockingQueue<Runnable>?, threadFactory: ThreadFactory?) : super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory)



//使用示例
val priorityExecutor:PriorityExecutor by lazy {
        //根据需求指定核心线程数，最大线程数，是否先进先出
        PriorityExecutor(2,10,true)
    }
```
### 添加任务
```
//使用示例
 for(i in 0..10){
    val task1 = PriorityRunnable(Runnable {
        Thread.sleep(500)
        Log.d("winton","普通级别任务$i")
    })
    App.INSTANCE.priorityExecutor.execute(task1)
 }

```
## License
 Copyright 2018 Winton

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


