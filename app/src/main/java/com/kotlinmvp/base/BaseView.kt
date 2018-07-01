package com.kotlinmvp.base

interface BaseView {
    fun showLoading();
    fun hideLoading();
    fun showError(message: String?);
}