package morefun.lxy.com.weather.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Weather implements Serializable {
    private String date;
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class Data implements Serializable {
        private ArrayList<Forecast> forecast;

        public ArrayList<Forecast> getForecast() {
            return forecast;
        }

        public void setForecast(ArrayList<Forecast> forecast) {
            this.forecast = forecast;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "forecast=" + forecast +
                    '}';
        }
    }

    public static class Forecast implements Serializable {

        private String date;
        private String high;
        private String low;
        private String fx;
        private String fl;
        private String type;
        private String sunrise;
        private String sunset;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getFx() {
            return fx;
        }

        public void setFx(String fx) {
            this.fx = fx;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Forecast{" +
                    "date='" + date + '\'' +
                    ", high='" + high + '\'' +
                    ", low='" + low + '\'' +
                    ", fx='" + fx + '\'' +
                    ", fl='" + fl + '\'' +
                    ", type='" + type + '\'' +
                    ", sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", data=" + data +
                '}';
    }
}
