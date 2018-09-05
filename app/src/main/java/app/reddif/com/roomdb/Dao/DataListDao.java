package app.reddif.com.roomdb.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import app.reddif.com.model.DataModel;

/**
 * Created by chan4u on 8/30/2018.
 */

@Dao
public interface DataListDao {

    /* gifs data model queries*/
    @Query("SELECT * FROM data_model")
    List<DataModel> getData();

    @Insert
    void insertList(List<DataModel> data);

    @Delete
    void delete(DataModel data);

    @Query("DELETE FROM data_model")
    void clearData();

}
