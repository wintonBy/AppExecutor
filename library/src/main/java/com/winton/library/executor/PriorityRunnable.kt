package com.winton.library.executor

/**
 * @author: winton
 * @time: 2018/9/18 上午9:18
 * @desc: 具备优先级的线程
 */
class PriorityRunnable:Runnable {

    val priority:Priority
    private val runnable:Runnable
    var SED:Long = -1

    constructor(runnable: Runnable):this(null,runnable)

    constructor( priority: Priority?, runnable: Runnable){
        if (priority != null){
            this.priority = priority
        }else{
            this.priority = Priority.NORMAL
        }
        this.runnable = runnable
    }


    override fun run() {
        runnable.run()
    }
}