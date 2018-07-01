package com.kotlinmvp.Utils

import rx.Observable
import rx.schedulers.Schedulers


public class RxJavaUtils {
    companion object {
        fun <T> applyObserverSchedulers(): Observable.Transformer<T, T> {
            return Observable.Transformer { observable ->
                observable.subscribeOn(Schedulers.io())
            }
        }
    }
}