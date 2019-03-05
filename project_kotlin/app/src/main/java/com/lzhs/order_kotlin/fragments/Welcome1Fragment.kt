package com.lzhs.order_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lzhs.commonlibrary.bases.fragment.BaseFragment
import com.lzhs.commonlibrary.configs.ARoutConfig.startMainActivity

import com.lzhs.order_kotlin.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_welcome1.*
import java.util.concurrent.TimeUnit


class Welcome1Fragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = Welcome1Fragment()
    }

    private lateinit var mDisposable: Disposable

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_welcome1, container, false)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mDisposable = Observable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe {
                    if (6 - it <= 0) stopTime()
                    mTextSkipAdver.text = "${6 - it} 秒跳过广告"
                }
        mViewClick.setOnClickListener { stopTime() }

    }

    private fun stopTime() {
        if (!mDisposable.isDisposed) mDisposable.dispose()
        startMainActivity()
        activity!!.finish()
    }

}
