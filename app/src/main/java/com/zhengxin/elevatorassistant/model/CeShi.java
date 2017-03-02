package com.zhengxin.elevatorassistant.model;

import com.zhengxin.ealibrary.utils.BaseBean;

/**
 * Created by long on 2017/2/27.
 */

public class CeShi extends BaseBean{

    /**
     * Category : province
     * Code : 11
     * ParentCode : 0
     * Name : 北京市
     */

    private String Category;
    private String Code;
    private String ParentCode;
    private String Name;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getParentCode() {
        return ParentCode;
    }

    public void setParentCode(String ParentCode) {
        this.ParentCode = ParentCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
