package com.infinity.monginis.dashboard.dao;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.infinity.monginis.dashboard.model.SaveOrder;

@Database(entities = { SaveOrder.class }, version = 2)
public abstract class SaveOrderDatabase extends RoomDatabase {



    public abstract Dao getNoteDao();

    private static SaveOrderDatabase saveOrderDb;

    public static SaveOrderDatabase getInstance(Context context) {
        if (null == saveOrderDb) {
            saveOrderDb = buildDatabaseInstance(context);
        }
        return saveOrderDb;
    }


    private static SaveOrderDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                SaveOrderDatabase.class,
                "SaveOrder")
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        saveOrderDb = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users "
                    +"ADD COLUMN address String");

        }
    };

   /* SaveOrderDatabase db = Room.databaseBuilder(getApplicationContext(), SaveOrderDatabase.class, "SaveOrder")
            .addMigrations(MIGRATION_1_2)
            .build();*/


}
