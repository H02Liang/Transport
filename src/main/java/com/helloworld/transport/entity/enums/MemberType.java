package com.helloworld.transport.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enumeration description
 * 会员等级类型
 *
 * @author LiangHang
 * @createTime 2019/11/17 22:56
 */
public enum MemberType {
    PTK(1, "普卡", "PTK"),
    YK(2, "银卡", "YK"),
    JK(3, "金卡", "JK"),
    BJK(4, "白金卡", "BJK"),
    ZSK(5, "钻石卡", "ZSK");

    /** 级别序号 */
    int index;
    /** 级别名称 */
    @JsonProperty
    String name;
    /** 级别简码 */
    String code;

    MemberType (int index, String name, String code) {
        this.index = index;
        this.name = name;
        this.code = code;
    }

    /**
     * 通过级别简码获取会员等级名称，若找不到则返回空串
     * @param code 级别简码
     * @return 会员级别名称
     */
    String getNameByCode (String code) {
        for (MemberType value : MemberType.values()) {
            if (value.getCode().equalsIgnoreCase(code)) {
                return value.getName();
            }
        }
        return "";
    }

    /**
     * 通过级别简码获取级别序号，若找不到则返回0
     * @param code 级别简码
     * @return 会员级别序号
     */
    int getIndexByCode (String code) {
        for (MemberType value : MemberType.values()) {
            if (value.getCode().equalsIgnoreCase(code)) {
                return value.getIndex();
            }
        }
        return 0;
    }

    /**
     * 通过级别序号获取级别简码，若找不到则返回空串
     * @param index 级别序号
     * @return 会员级别简码
     */
    String getCodeByIndex (int index) {
        for (MemberType value : MemberType.values()) {
            if (value.getIndex() == index) {
                return value.getCode();
            }
        }
        return "";
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
