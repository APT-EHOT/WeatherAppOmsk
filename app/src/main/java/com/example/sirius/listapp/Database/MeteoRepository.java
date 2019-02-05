package com.example.sirius.listapp.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MeteoRepository {

    private MeteoDAO meteoDAO;
    private LiveData<List<Meteo>> todayMeteo;
    private LiveData<List<Meteo>> tomorrowMeteo;
    private LiveData<List<Meteo>> weekMeteo;

    public MeteoRepository(Application application) {
        MeteoDB database = MeteoDB.getInstance(application);
        meteoDAO = database.meteoDAO();
        todayMeteo = meteoDAO.getTodayMeteo();
        tomorrowMeteo = meteoDAO.getTomorrowMeteo();
        weekMeteo = meteoDAO.getWeekMeteo();
    }

    public void insert(Meteo meteo) {
        new InsertMeteoAsyncTask(meteoDAO).execute(meteo);
    }

    public void deleteAllMeteo() {
        new DeleteAllMeteoAsyncTask(meteoDAO).execute();
    }

    public LiveData<List<Meteo>> getTodayMeteo() {
        return todayMeteo;
    }

    public LiveData<List<Meteo>> getTomorrowMeteo() {
        return tomorrowMeteo;
    }

    public LiveData<List<Meteo>> getWeekMeteo() {
        return weekMeteo;
    }




    private static class InsertMeteoAsyncTask extends AsyncTask
            <Meteo, Void, Void> {

        private MeteoDAO meteoDAO;

        private InsertMeteoAsyncTask(MeteoDAO meteoDAO) {
            this.meteoDAO = meteoDAO;
        }

        @Override
        protected Void doInBackground(Meteo... Meteos) {
            meteoDAO.insert(Meteos[0]);
            return null;
        }
    }

    private static class DeleteAllMeteoAsyncTask extends AsyncTask
            <Meteo, Void, Void>{

        private MeteoDAO meteoDAO;

        DeleteAllMeteoAsyncTask(MeteoDAO meteoDAO) {
            this.meteoDAO = meteoDAO;
        }

        @Override
        protected Void doInBackground(Meteo... meteos) {
            meteoDAO.deleteAllMeteo();
            return null;
        }
    }


}
