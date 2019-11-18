package com.hoggen.sublimation.enums;

public enum UserStateEnum {
    OFFLINE(10000, "非法用户"),
    SUCCESS(0, "操作成功"),
    PASS(10002, "通过认证"),
    INNER_ERROR(10001, "操作失败"),
    EMPTY(10003, "用户为空"),
    NAMEOFFLINE(10004, "用户名不合法"),
    PASSOFFLINE(10005, "密码不合法"),
    ALREADY(10006, "该用户名已存在"),
    PHONEEMPTY(10007, "电话号码为空"),
    REALNAMEEMPTY(10008, "真是姓名为空"),
    ROLEIILLEGAl(10009, "用户角色非法"),
    OPEILLEGAl(10010, "不能操作自己"),
    INFOILLEGAl(10011, "参数错误");

    private int state;

    private String stateInfo;

    private UserStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static UserStateEnum stateOf(int index) {
        for (UserStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
