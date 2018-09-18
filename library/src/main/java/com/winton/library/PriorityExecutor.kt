package com.winton.library

import com.winton.library.executor.PriorityRunnable
import java.util.*
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * @author: winton
 * @time: 2018/9/18 上午9:14
 * @desc: 具备优先级的线程池
 */
class PriorityExecutor : ThreadPoolExecutor {

    private val atomicLong:AtomicLong = AtomicLong(1)


    constructor(corePoolSize: Int,maximumPoolSize: Int,fifo: Boolean):this(corePoolSize,maximumPoolSize,1,fifo)

    constructor(corePoolSize: Int, maximumPoolSize: Int) : this(corePoolSize, maximumPoolSize, 1)

    constructor(corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long) : this(corePoolSize, maximumPoolSize, keepAliveTime, false)


    constructor(corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long, fifo: Boolean) : this(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, PriorityBlockingQueue<Runnable>(maximumPoolSize, initComparator(fifo)), PriorityExecutor.mThreadFactory)


    constructor(corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long, unit: TimeUnit?, workQueue: BlockingQueue<Runnable>?, threadFactory: ThreadFactory?) : super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory)


    companion object {
        /**
         * 先进先出队列
         */
        private val FIFO: Comparator<Runnable> = Comparator { o1, o2 ->
            if (o1 is PriorityRunnable && o2 is PriorityRunnable) {
                var result = o1.priority.ordinal - o2.priority.ordinal
                if (result == 0) {
                    return@Comparator (o1.SED - o2.SED).toInt()
                } else {
                    return@Comparator result
                }
            }
            return@Comparator 0
        }
        /**
         * 先进后出队列
         */
        private val LIFO: Comparator<Runnable> = Comparator { o1, o2 ->
            if (o1 is PriorityRunnable && o2 is PriorityRunnable) {
                var result = o1.priority.ordinal - o2.priority.ordinal
                if (result == 0) {
                    return@Comparator (o2.SED - o1.SED).toInt()
                } else {
                    return@Comparator result
                }
            }
            return@Comparator 0
        }

        val mThreadFactory: ThreadFactory = object : ThreadFactory {

            val autoInt: AtomicInteger = AtomicInteger(0)
            override fun newThread(r: Runnable?): Thread {
                return Thread(r, "thread:${autoInt.getAndIncrement()}")
            }
        }


        fun initComparator(fifo: Boolean): Comparator<Runnable> {
            return when (fifo) {
                true -> FIFO
                false -> LIFO
            }
        }
    }

    override fun execute(command: Runnable?) {
        if (command is PriorityRunnable){
            command.SED = atomicLong.getAndIncrement()
        }
        super.execute(command)
    }

}