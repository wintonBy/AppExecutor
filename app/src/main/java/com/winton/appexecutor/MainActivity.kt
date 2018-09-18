package com.winton.appexecutor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.winton.library.executor.PriorityRunnable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 0..10){
            val task1 = PriorityRunnable(Runnable {
                Thread.sleep(500)
                Log.d("winton","普通级别任务$i")
            })
            App.INSTANCE.priorityExecutor.execute(task1)
        }
//        for(i in 0..10){
//            val task2 = PriorityRunnable(Priority.HIGH, Runnable {
//                Thread.sleep(500)
//                Log.d("winton","高优先级任务")
//            })
//            App.INSTANCE.priorityExecutor.execute(task2)
//        }
//        for(i in 0..10){
//            val task2 = PriorityRunnable(Priority.LOW, Runnable {
//                Thread.sleep(500)
//                Log.d("winton","低优先级任务")
//            })
//            App.INSTANCE.priorityExecutor.execute(task2)
//        }





    }
}
