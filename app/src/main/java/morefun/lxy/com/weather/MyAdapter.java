package morefun.lxy.com.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import morefun.lxy.com.weather.bean.Weather;

public class MyAdapter extends BaseAdapter {
    List<Weather.Forecast> weathers;
    Context context;

    public MyAdapter(Context context, List<Weather.Forecast> weathers) {
        this.context = context;
        this.weathers = weathers;
    }

    @Override
    public int getCount() {
        return weathers.size();
    }

    @Override
    public Object getItem(int position) {
        return weathers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.date = convertView.findViewById(R.id.date);
            holder.temp = convertView.findViewById(R.id.temp);
            holder.type = convertView.findViewById(R.id.type);
            holder.sun = convertView.findViewById(R.id.sun);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Weather.Forecast bean = weathers.get(position);
        holder.date.setText("" + bean.getDate());
        holder.temp.setText("" + bean.getHigh() + "," + bean.getLow());
        holder.type.setText("" + bean.getType() + "," + bean.getFx() + bean.getFl());
        holder.sun.setText("日出时间" + bean.getSunrise() + ",日落时间" + bean.getSunset());

        return convertView;
    }

    class ViewHolder {
        public TextView date, temp, type, sun;
    }
}
