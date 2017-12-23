package com.zhuazhuale.changsha.model.entity.eventbus;

/**
 * author:  ljy
 * date:    2017/9/28
 * description: EventBus通信的事件类,地址更新
 */

public class AddressEvent {
    private String addressFresh;

    public AddressEvent(String addressFresh) {
        this.addressFresh = addressFresh;
    }

    public String getAddressFresh() {
        return addressFresh;
    }

    public void setAddressFresh(String addressFresh) {
        this.addressFresh = addressFresh;
    }
}
