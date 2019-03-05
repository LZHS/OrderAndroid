package com.lzhs.commonlibrary.bases.activity

import com.lzhs.library.bases.BaseSwipeBackActivity

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2019/2/18 : 3:29 PM<br/>
 */
abstract class BaseActivity : BaseSwipeBackActivity() {
    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}