package com.staysilly.geotouristapp.repositories;

import android.content.Context;

import com.staysilly.geotouristapp.models.Address;
import com.staysilly.geotouristapp.models.Tour;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities =  {Tour.class, Address.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class LocalDB extends RoomDatabase {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    private static LocalDB sLocalDB = null;
    public abstract TourDao tourDao();


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public static LocalDB getInstance(Context context){
        if (sLocalDB == null) {
            synchronized (LocalDB.class) {
                if (sLocalDB == null) {
                    sLocalDB = Room.databaseBuilder(context.getApplicationContext(), LocalDB.class, "Local_DB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return sLocalDB;
    }

}
