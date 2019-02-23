package com.example.ueditor.define;

/**
 * 处理状态接口
 * @author ggg1235
 *
 */
public interface State {

    public boolean isSuccess();

    public void putInfo(String name, String val);

    public void putInfo(String name, long val);

    public String toJSONString();

}