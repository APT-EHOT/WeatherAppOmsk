package com.example.sirius.listapp.Support;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sirius.listapp.Database.Meteo;
import com.example.sirius.listapp.R;

import java.util.List;

public class MeteoAdapter
        extends RecyclerView.Adapter<MeteoAdapter.ViewHolder> {

    private List<Meteo> data;

    public MeteoAdapter(final List<Meteo> data) {
        this.data = data;
    }

    @NonNull
    @Override
    // сохраняет ссылки на все вью-элементы item
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return
                new ViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_meteo_new,
                                parent,
                                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meteo meteo = data.get(position);

        holder.date.setText(getNormalDate(meteo.date));

        holder.tod.setText(getNormalTod(meteo.tod));

        String normalPressure = "Давление: " + meteo.pressure + " мм. рт. ст.";
        holder.pressure.setText(normalPressure);

        String normalTemp = Html.fromHtml(meteo.temp).toString();
        holder.temp.setText(normalTemp);

        String normalHumidity = "Влажность: " + meteo.humidity + "%";
        holder.humidity.setText(normalHumidity);

        String normalWind = "Ветер: " + meteo.wind;
        holder.wind.setText(normalWind);

        holder.cloud.setText(meteo.cloud);

        holder.img.setImageResource(getImg(meteo.cloud, meteo.tod));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView date;
        private TextView tod;
        private TextView pressure;
        private TextView temp;
        private TextView humidity;
        private TextView wind;
        private TextView cloud;
        private ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            date = view.findViewById(R.id.date);
            tod = view.findViewById(R.id.tod);
            pressure = view.findViewById(R.id.pressure);
            temp = view.findViewById(R.id.temp);
            humidity = view.findViewById(R.id.humidity);
            wind = view.findViewById(R.id.wind);
            cloud = view.findViewById(R.id.cloud);
            img = view.findViewById(R.id.img);

        }
    }

    // преобразование даты в адекватный формат
    private String getNormalDate(String input) {

        String monthWord;

        String monthNumber =
                Character.toString(input.charAt(5)) +
                        Character.toString(input.charAt(6));

        switch (monthNumber) {
            case "01":
                monthWord = "январь";
                break;

            case "02":
                monthWord = "февраль";
                break;

            case "03":
                monthWord = "март";
                break;

            case "04":
                monthWord = "апрель";
                break;

            case "05":
                monthWord = "май";
                break;

            case "06":
                monthWord = "июнь";
                break;

            case "07":
                monthWord = "июль";
                break;

            case "08":
                monthWord = "август";
                break;

            case "09":
                monthWord = "сентябрь";
                break;

            case "10":
                monthWord = "октябрь";
                break;

            case "11":
                monthWord = "ноябрь";
                break;

            case "12":
                monthWord = "декабрь";
                break;

            default:
                monthWord = "ERR_NO_MONTH";
                break;
        }


        return Character.toString(input.charAt(8)) +
                Character.toString(input.charAt(9)) +

                ", " + monthWord + " " +

                Character.toString(input.charAt(0)) +
                Character.toString(input.charAt(1)) +
                Character.toString(input.charAt(2)) +
                Character.toString(input.charAt(3));
    }

    // преобразование времени суток в адекватный формат
    private String getNormalTod(String input) {

        String result;

        switch (input) {
            case "0":
                result = "Ночь";
                break;
            case "1":
                result = "Утро";
                break;
            case "2":
                result = "День";
                break;
            case "3":
                result = "Вечер";
                break;

            default:
                result = "ERR_NO_TOD";
                break;

        }
        return result;
    }

    // установка картинки к прогнозу
    private Integer getImg(String input, String tod) {

        int result;

        if (((tod.equals("0")) || (tod.equals("3"))) && (input.equals("Ясно"))) {
            result = R.drawable.ic_moon_foreground;
        } else {
            switch (input) {
                case "Ясно":
                    result = R.drawable.ic_sun_foreground;
                    break;
                case "Малооблачно":
                    result = R.drawable.ic_cloudy_foreground;
                    break;
                case "Пасмурно":
                    result = R.drawable.ic_more_cloudy_foreground;
                    break;
                case "Облачно":
                    result = R.drawable.ic_more_cloudy_foreground;
                    break;
                case "Пасмурно, небольшой снег":
                    result = R.drawable.ic_snowing_alter_foreground;
                    break;
                case "Пасмурно, снег":
                    result = R.drawable.ic_snowing_alter_foreground;
                    break;
                case "Облачно, небольшой снег":
                    result = R.drawable.ic_snowing_alter_foreground;
                    break;

                default:
                    result = R.drawable.ic_launcher_background;
                    break;
            }
        }
        return result;
    }

    // СЕТТЕР ДАННЫХ (пока что не нужен)
    /*public void setData(List<Meteo> data) {
        this.data = data;
    }*/
}
