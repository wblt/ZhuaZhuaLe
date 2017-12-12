package com.zhuazhuale.changsha.app.constant;

/**
 * Created by Administrator on 2016/11/21.
 */

public interface ICallListener<T> {
    void callSuccess(T t);

    void callFailed();

    void onFinish();
}
