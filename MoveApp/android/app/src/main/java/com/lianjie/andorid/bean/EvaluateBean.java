package com.lianjie.andorid.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/7/8
 * Time: 16:51
 */
public class EvaluateBean {
    /**
     * code : 1000
     * msg : 获取数据成功
     * data : {"impressLists":[[{"id":1,"item":"重点对象"},{"id":2,"item":"要求女保洁"},{"id":3,"item":"有洁癖"},{"id":4,"item":"特殊要求多"},{"id":5,"item":"长期不做"},{"id":6,"item":"联系不上"},{"id":7,"item":"租户严格"},{"id":8,"item":"脾气不好"},{"id":9,"item":"怕打扰"}],[{"id":10,"item":"养宠物"},{"id":11,"item":"有防盗门"},{"id":12,"item":"机械锁"},{"id":13,"item":"垃圾多"},{"id":14,"item":"有损毁"},{"id":15,"item":"很干净"},{"id":16,"item":"油污重"},{"id":17,"item":"脏乱差"},{"id":18,"item":"位置好"}]],"orderPingJiaLists":[{"id":1,"value":"没有微波炉"},{"id":2,"value":"没有热水器"},{"id":3,"value":"没有桌子"},{"id":4,"value":"无法拍照"},{"id":5,"value":"没有垃圾桶"},{"id":6,"value":"租户在家"},{"id":7,"value":"没有床品"},{"id":8,"value":"没有门禁卡"},{"id":9,"value":"租户验收合格"},{"id":10,"value":"水电已关"},{"id":11,"value":"没有台灯"},{"id":12,"value":"两个卫生间"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<List<ImpressListsBean>> impressLists;
        private List<OrderPingJiaListsBean> orderPingJiaLists;

        public List<List<ImpressListsBean>> getImpressLists() {
            return impressLists;
        }

        public void setImpressLists(List<List<ImpressListsBean>> impressLists) {
            this.impressLists = impressLists;
        }

        public List<OrderPingJiaListsBean> getOrderPingJiaLists() {
            return orderPingJiaLists;
        }

        public void setOrderPingJiaLists(List<OrderPingJiaListsBean> orderPingJiaLists) {
            this.orderPingJiaLists = orderPingJiaLists;
        }

        public static class ImpressListsBean {
            /**
             * id : 1
             * item : 重点对象
             */

            private int id;
            private String item;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getItem() {
                return item;
            }

            public void setItem(String item) {
                this.item = item;
            }
        }

        public static class OrderPingJiaListsBean {
            /**
             * id : 1
             * value : 没有微波炉
             */

            private int id;
            private String value;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

//
//    @Override
//    public String toString() {
//        return "EvaluateBean{" +
//                "code=" + code +
//                ", msg='" + msg + '\'' +
//                ", data=" + data +
//                '}';
//    }
//
//    /**
//     * code : 1000
//     * msg : 获取数据成功
//     * data : [[{"id":1,"item":"重点对象"},{"id":2,"item":"要求女保洁"},{"id":3,"item":"有洁癖"},{"id":4,"item":"特殊要求多"},{"id":5,"item":"长期不做"},{"id":6,"item":"联系不上"},{"id":7,"item":"租户严格"},{"id":8,"item":"脾气不好"},{"id":9,"item":"怕打扰"}],[{"id":10,"item":"养宠物"},{"id":11,"item":"有防盗门"},{"id":12,"item":"机械锁"},{"id":13,"item":"垃圾多"},{"id":14,"item":"有损毁"},{"id":15,"item":"很干净"},{"id":16,"item":"油污重"},{"id":17,"item":"脏乱差"},{"id":18,"item":"位置好"}]]
//     */
//
//    private int code;
//    private String msg;
//    private List<List<DataBean>> data;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<List<DataBean>> getData() {
//        return data;
//    }
//
//    public void setData(List<List<DataBean>> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//
//        /**
//         * id : 1
//         * item : 重点对象
//         */
//
//        private int id;
//        private String item;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getItem() {
//            return item;
//        }
//
//        public void setItem(String item) {
//            this.item = item;
//        }
//    }
//














}
