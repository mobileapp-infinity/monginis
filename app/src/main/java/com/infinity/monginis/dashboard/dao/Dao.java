package com.infinity.monginis.dashboard.dao;


import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.infinity.monginis.dashboard.model.SaveOrder;

import java.util.List;

@androidx.room.Dao
public interface Dao {


    @Query("SELECT * FROM saveOrder" )
    List<SaveOrder> getAll();


    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insert(SaveOrder note);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(SaveOrder repos);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(SaveOrder note);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(SaveOrder... note);      // Note... is varargs, here note is an array







}
