package kausthubhadhikari.com.crowdfire.model.data;

import android.content.Context;

import java.util.ArrayList;

import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;

/**
 * Created by kausthubhadhikari on 21/12/16.
 */

public class Database {

    private Context context;
    private DBHelper dbHelper;

    public Database(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void addGarment(GarmentPojo garmentDetails) {
        dbHelper.insertData(garmentDetails);
    }

    public ArrayList<GarmentPojo> getUpperGarments() {
        return dbHelper.getUpperGarments();
    }

    public ArrayList<GarmentPojo> getLowerGarments() {
        return dbHelper.getLowerGarments();
    }

}
