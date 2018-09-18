package com.winton.appexecutor

import android.app.Application
import com.winton.library.PriorityExecutor
import kotlin.properties.Delegates

/**
 * @author: winton
 * @time: 2018/9/18 下午4:21
 * @desc: 描述
 */
class App:Application() {
    val priorityExecutor:PriorityExecutor by lazy {
        PriorityExecutor(2,10,true)
    }
    companion object {
        var INSTANCE:App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}