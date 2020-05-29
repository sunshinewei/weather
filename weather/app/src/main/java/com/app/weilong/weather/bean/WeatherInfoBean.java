package com.app.weilong.weather.bean;

import com.app.weilong.lib.base.net.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * create by weilong on 2020/5/26
 * email: 1436699184@qq.com
 */
public class WeatherInfoBean extends BaseResponse {


    /**
     * count : 1
     * lives : [{"province":"北京","city":"东城区","adcode":"110101","weather":"晴","temperature":"28","winddirection":"北","windpower":"≤3","humidity":"23","reporttime":"2020-05-27 15:30:46"}]
     */

    private String count;
    private ArrayList<LivesBean> lives;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<LivesBean> getLives() {
        return lives;
    }

    public void setLives(ArrayList<LivesBean> lives) {
        this.lives = lives;
    }

    public static class LivesBean {
        /**
         * province : 北京
         * city : 东城区
         * adcode : 110101
         * weather : 晴
         * temperature : 28
         * winddirection : 北
         * windpower : ≤3
         * humidity : 23
         * reporttime : 2020-05-27 15:30:46
         */

        private String province;
        private String city;
        private String adcode;
        private String weather;
        private String temperature;
        private String winddirection;
        private String windpower;
        private String humidity;
        private String reporttime;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWinddirection() {
            return winddirection;
        }

        public void setWinddirection(String winddirection) {
            this.winddirection = winddirection;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getReporttime() {
            return reporttime;
        }

        public void setReporttime(String reporttime) {
            this.reporttime = reporttime;
        }
    }
}
