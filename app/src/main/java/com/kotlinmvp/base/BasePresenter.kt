package com.kotlinmvp.base

interface BasePresenter<V : BaseView> {
    fun setView(view: V);
    fun destroy();
    fun destroyedView();
}