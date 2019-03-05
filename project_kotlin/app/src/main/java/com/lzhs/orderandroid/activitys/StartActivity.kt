package com.lzhs.orderandroid.activitys

import android.os.Bundle
import com.lzhs.commonlibrary.bases.activity.BaseActivity
import com.lzhs.commonlibrary.configs.ARoutConfig.startWelcomeActivity
import com.lzhs.library.utils.StatusBarUtil
import com.lzhs.orderandroid.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/2/22 : 2:13 PM<br/>
 */

class StartActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTransparent(this)
        setSwipeBackEnable(false)
        //TODO("这里实现初始化")
        startWelcomeActivity()
        this.finish()
    }


}

