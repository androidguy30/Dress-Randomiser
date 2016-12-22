package kausthubhadhikari.com.crowdfire.model.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import kausthubhadhikari.com.crowdfire.model.pojo.FavGarmentPojo;
import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;
import kausthubhadhikari.com.crowdfire.utils.misc.AppConstants;

/**
 * Created by kausthubhadhikari on 21/12/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "wardrobe.db";
    private static int DB_VERSION = 1;
    private static String TABLE_NAME_CLOTH = "cloths";
    private static String TABLE_NAME_FAV = "fav";
    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_CLOTH + "(" + AppConstants.DB_KEY_ID + " INTEGER PRIMARY KEY," + AppConstants.DB_KEY_IMAGE + " BLOB," + AppConstants.DB_KEY_TYPE + " TEXT)";
        String CREATE_FAV_TABLE = "CREATE TABLE " + TABLE_NAME_FAV + "(" + AppConstants.DB_KEY_FAV_UPGAR_ID + " INTEGER," + AppConstants.DB_KEY_FAV_LOGAR_ID + " INTEGER," + AppConstants.DB_KEY_FAV_UPGAR_IMAGE + " BLOB," + AppConstants.DB_KEY_FAV_LOGAR_IMAGE + " BLOB)";
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_FAV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CLOTH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FAV);

        onCreate(db);
    }

    public void insertData(GarmentPojo garmentPojo) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstants.DB_KEY_TYPE, garmentPojo.type.name().toUpperCase());
        values.put(AppConstants.DB_KEY_IMAGE, garmentPojo.imageData);
        database.insert(TABLE_NAME_CLOTH, null, values);
    }

    public ArrayList<GarmentPojo> getUpperGarments() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<GarmentPojo> data = new ArrayList<>();
        String QUERY = "SELECT * FROM " + TABLE_NAME_CLOTH + " WHERE " + AppConstants.DB_KEY_TYPE + " = 'UPPER'";
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                GarmentPojo garmentPojo = new GarmentPojo();
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
        String QUERY = "SELECT * FROM " + TABLE_NAME_CLOTH + " WHERE " + AppConstants.DB_KEY_TYPE + " = 'LOWER'";
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                GarmentPojo garmentPojo = new GarmentPojo();
                garmentPojo.imageData = cursor.getBlob(cursor.getColumnIndex(AppConstants.DB_KEY_IMAGE));
                garmentPojo.type = cursor.getString(cursor.getColumnIndex(AppConstants.DB_KEY_TYPE)).equalsIgnoreCase("lower") ? AppConstants.ClothType.LOWER : AppConstants.ClothType.UPPER;
                data.add(garmentPojo);
            } while (cursor.moveToNext());
        }
        return data;
    }


    public ArrayList<FavGarmentPojo> getFavouritesSelection() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<FavGarmentPojo> favData = new ArrayList<>();
        String QUERY = "SELECT * FROM " + TABLE_NAME_FAV;
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                FavGarmentPojo data = new FavGarmentPojo();
                data.lo_gar_id = cursor.getInt(cursor.getColumnIndex(AppConstants.DB_KEY_FAV_LOGAR_ID));
                data.lo_grad_image = cursor.getBlob(cursor.getColumnIndex(AppConstants.DB_KEY_FAV_LOGAR_IMAGE));
                data.up_gar_id = cursor.getInt(cursor.getColumnIndex(AppConstants.DB_KEY_FAV_UPGAR_ID));
                data.up_grad_image = cursor.getBlob(cursor.getColumnIndex(AppConstants.DB_KEY_FAV_UPGAR_IMAGE));
                favData.add(data);
            } while (cursor.moveToNext());
        }
        return favData;
    }

    public boolean isFavourite(int lowerGarmentId, int upperGarmentId) {
        SQLiteDatabase database = getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME_FAV + " WHERE " + AppConstants.DB_KEY_FAV_UPGAR_ID + " = " + upperGarmentId + " AND " + AppConstants.DB_KEY_FAV_LOGAR_ID + " = " + lowerGarmentId;
        Cursor cursor = database.rawQuery(QUERY, null);
        return cursor.getCount() == 0 ? false : true;
    }

    public void addFavouritedData(FavGarmentPojo data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstants.DB_KEY_FAV_LOGAR_ID, data.lo_gar_id);
        contentValues.put(AppConstants.DB_KEY_FAV_LOGAR_IMAGE, data.lo_grad_image);
        contentValues.put(AppConstants.DB_KEY_FAV_UPGAR_ID, data.up_gar_id);
        contentValues.put(AppConstants.DB_KEY_FAV_UPGAR_IMAGE, data.up_grad_image);
        db.insert(TABLE_NAME_FAV, null, contentValues);
    }

}
