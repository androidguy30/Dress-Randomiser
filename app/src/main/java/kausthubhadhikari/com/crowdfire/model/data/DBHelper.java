package kausthubhadhikari.com.crowdfire.model.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;
import kausthubhadhikari.com.crowdfire.utils.misc.AppConstants;

/**
 * Created by kausthubhadhikari on 21/12/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "wardrobe.db";
    private static int DB_VERSION = 1;
    private static String TABLE_NAME = "cloths";
    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + AppConstants.DB_KEY_DESC + " TEXT," + AppConstants.DB_KEY_IMAGE + " BLOB," + AppConstants.DB_KEY_DESC + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void insertData(GarmentPojo garmentPojo) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstants.DB_KEY_TYPE, garmentPojo.type.name().toUpperCase());
        values.put(AppConstants.DB_KEY_IMAGE, garmentPojo.imageData);
        values.put(AppConstants.DB_KEY_DESC, garmentPojo.description);
        database.insert(TABLE_NAME, null, values);
    }

    public ArrayList<GarmentPojo> getUpperGarments() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<GarmentPojo> data = new ArrayList<>();
        String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + AppConstants.DB_KEY_TYPE + " = 'UPPER'";
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                GarmentPojo garmentPojo = new GarmentPojo();
                garmentPojo.description = cursor.getString(cursor.getColumnIndex(AppConstants.DB_KEY_DESC));
                garmentPojo.imageData = cursor.getBlob(cursor.getColumnIndex(AppConstants.DB_KEY_IMAGE));
                garmentPojo.type = cursor.getString(cursor.getColumnIndex(AppConstants.DB_KEY_TYPE)).equalsIgnoreCase("upper") ? AppConstants.ClothType.UPPER : AppConstants.ClothType.LOWER;
                data.add(garmentPojo);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public ArrayList<GarmentPojo> getLowerGarments() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<GarmentPojo> data = new ArrayList<>();
        String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + AppConstants.DB_KEY_TYPE + " = 'LOWER'";
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                GarmentPojo garmentPojo = new GarmentPojo();
                garmentPojo.description = cursor.getString(cursor.getColumnIndex(AppConstants.DB_KEY_DESC));
                garmentPojo.imageData = cursor.getBlob(cursor.getColumnIndex(AppConstants.DB_KEY_IMAGE));
                garmentPojo.type = cursor.getString(cursor.getColumnIndex(AppConstants.DB_KEY_TYPE)).equalsIgnoreCase("lower") ? AppConstants.ClothType.LOWER : AppConstants.ClothType.UPPER;
                data.add(garmentPojo);
            } while (cursor.moveToNext());
        }
        return data;
    }

}
