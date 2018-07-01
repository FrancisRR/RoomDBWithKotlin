package com.kotlinmvp.base

open class AbstractBasePresenter<V : BaseView> : BasePresenter<V> {

    val TAG: String? = AbstractBasePresenter::class.simpleName;
    override fun setView(view: V) {
    }

    override fun destroy() {
    }

    override fun destroyedView() {
    }
}