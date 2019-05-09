package com.bawei.exam19;

import java.util.List;

public class MyBean {

    Results result;

    public Results getResult() {
        return result;
    }

    public MyBean(Results result) {
        this.result = result;
    }

    public void setResult(Results result) {
        this.result = result;
    }

    public static class Results{
        Rxxps rxxp;
        Pzshs pzsh;
        Mlsss mlss;

        public Results(Rxxps rxxp, Pzshs pzsh, Mlsss mlss) {
            this.rxxp = rxxp;
            this.pzsh = pzsh;
            this.mlss = mlss;
        }

        public Rxxps getRxxp() {
            return rxxp;
        }

        public void setRxxp(Rxxps rxxp) {
            this.rxxp = rxxp;
        }

        public Pzshs getPzsh() {
            return pzsh;
        }

        public void setPzsh(Pzshs pzsh) {
            this.pzsh = pzsh;
        }

        public Mlsss getMlss() {
            return mlss;
        }

        public void setMlss(Mlsss mlss) {
            this.mlss = mlss;
        }

        public static class Rxxps{
            List<CommodityLists> commodityList;

            public Rxxps(List<CommodityLists> commodityList) {
                this.commodityList = commodityList;
            }

            public List<CommodityLists> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityLists> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityLists{
                String commodityName;
                String masterPic;

                public CommodityLists(String commodityName, String masterPic) {
                    this.commodityName = commodityName;
                    this.masterPic = masterPic;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }
            }
        }
        public static class Pzshs{
            List<Pzshs.CommodityLists> commodityList;
            public Pzshs(List<Pzshs.CommodityLists> commodityList) {
                this.commodityList = commodityList;
            }

            public List<Pzshs.CommodityLists> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<Pzshs.CommodityLists> commodityList) {
                this.commodityList = commodityList;
            }
            public static class CommodityLists{
                String commodityName;
                String masterPic;
                public CommodityLists(String commodityName, String masterPic) {
                    this.commodityName = commodityName;
                    this.masterPic = masterPic;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }
            }
        }
        public static class Mlsss{
            List<Mlsss.CommodityLists> commodityList;
            public Mlsss(List<Mlsss.CommodityLists> commodityList) {
                this.commodityList = commodityList;
            }

            public List<Mlsss.CommodityLists> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<Mlsss.CommodityLists> commodityList) {
                this.commodityList = commodityList;
            }
            public static class CommodityLists{
                String commodityName;
                String masterPic;
                public CommodityLists(String commodityName, String masterPic) {
                    this.commodityName = commodityName;
                    this.masterPic = masterPic;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }
            }
        }
    }

}
