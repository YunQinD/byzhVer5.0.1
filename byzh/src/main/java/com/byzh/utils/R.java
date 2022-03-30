package com.byzh.utils;

import lombok.Data;

/**
 * 返回值标准包装类
 */
@Data
public class R {
    private boolean flag;
    private Object data;
    public R() {}
    public R(boolean flag) {
        this.flag = flag;
        this.data = null;
    }
    public R(boolean flag,Object data) {
        this.flag = flag;
        this.data = data;
    }
}
