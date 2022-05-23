package com.example.stickyheaders;

import java.util.List;

public class Model {



    private String categoryid;
    private String header;
    private String description;
    private String layout;

//    private List<ChildModel> ChildItemList;
//    private List<String> list;
//
//    public List<String> getList() {
//        return list;
//    }
//
//    public void setList(List<String> list) {
//        this.list = list;
//    }

    public Model(String categoryid, String header, String description, String layout) {
        this.categoryid = categoryid;
        this.header = header;
        this.description = description;
        this.layout = layout;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<ChildModel> getChildItemList() {
//        return ChildItemList;
//    }
//
//    public void setChildItemList(List<ChildModel> childItemList) {
//        ChildItemList = childItemList;
//    }
}
