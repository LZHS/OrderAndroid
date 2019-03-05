package com.lzhs.orderandroid

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/2/22 : 3:27 PM<br/>
 */
class CustomApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        initArouter()
    }

    private fun initArouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }
}