package com.zht.bean;

import java.util.List;

public class LayuiTable {

    private Integer id;

    private Integer page;

    private Integer limit;

    private Integer index;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getIndex() {
        return (this.page-1)*this.limit;
    }

    /***********以上是分页的请求数据*************/


    /***********分页返回数据开始*************/

    private Page pagePage;

    class Page{

        private Integer code;

        private String msg;

        private Integer count;

        private List data;

        public Integer getCode() {
            return 0;
        }

        public void setCode(Integer code) {
            this.code = code == null? 0 : code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List getData() {
            return data;
        }

        public void setData(List data) {
            this.data = data;
        }
    }

    public void setPagePage(Integer count, List data){
        pagePage = new Page();
        pagePage.setCount(count);
        pagePage.setData(data);
    }

    public Page getPagePage(){
        return this.pagePage;
    }
}
