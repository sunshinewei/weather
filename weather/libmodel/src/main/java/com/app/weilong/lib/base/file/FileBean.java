package com.app.weilong.lib.base.file;


import com.app.weilong.lib.base.net.BaseResponse;

/**
 * create by weilong on 2020/4/28
 * email: 1436699184@qq.com
 */
public class FileBean extends BaseResponse<FileBean.DataBean> {


    public static class DataBean {
        /**
         * filename : wx_camera_1587725956334.jpg,wx_camera_1587725956334.jpg,IMG_20200423_195726R.jpg
         * filesize : 1084140,1084140,3160767
         * tmpurl : http://101.200.143.85/lgbucket/files/imageTemp/mobile/2020/04/28/userzone/estate/20200428184800687u9y1Yp1o.jpg,http://101.200.143.85/lgbucket/files/imageTemp/mobile/2020/04/28/userzone/estate/20200428184800689mT4LhakS.jpg,http://101.200.143.85/lgbucket/files/imageTemp/mobile/2020/04/28/userzone/estate/20200428184800691f4PBYsXu.jpg
         */

        private String filename;
        private String filesize;
        private String tmpurl;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getFilesize() {
            return filesize;
        }

        public void setFilesize(String filesize) {
            this.filesize = filesize;
        }

        public String getTmpurl() {
            return tmpurl;
        }

        public void setTmpurl(String tmpurl) {
            this.tmpurl = tmpurl;
        }
    }
}
