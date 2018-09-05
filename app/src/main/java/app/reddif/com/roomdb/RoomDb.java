package app.reddif.com.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import app.reddif.com.model.DataModel;
import app.reddif.com.roomdb.Dao.DataListDao;

/**
 * Created by chan4u on 8/30/2018.
 */
@Database(entities = {DataModel.class}, version = 3, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
    private static RoomDb INSTANCE;

    public static RoomDb getRoomDb(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), RoomDb.class, "database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }
    public abstract DataListDao dataListDao();
}
