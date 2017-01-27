package com.lsvdeveloper.svt.lindt.by_time_003;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Линдт Светлана on 02.12.2016.
 */

public class DBHelper extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 16;
    private static final String DATABASE_NAME="byTime";
    public static final String TABLE_READING="reading";
    private static final String TABLE_FOR_COMPANY="forCompany";
    private static final String TABLE_FOR_ONE="forOne";
    private static final String TABLE_WITH_CHILDREN_1= "forChildren_1_3";
    private static final String TABLE_WITH_CHILDREN_3="forChildren_3_6";
    private static final String TABLE_WITH_CHILDREN_6="forChildren_6_";


    private static final String KEY_ID="_id";
    private static final String KEY_LEISURE="leisure";

    private ContentValues contentValues = new ContentValues();
    static SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("work","onCreate");

        sqLiteDatabase.execSQL("create table if not exists " + TABLE_READING + "(" + KEY_ID + " integer primary key, " + KEY_LEISURE + " text" + ")");
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_FOR_COMPANY + "(" + KEY_ID + " integer primary key, " + KEY_LEISURE + " text" + ")");
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_FOR_ONE + "(" + KEY_ID + " integer primary key, " + KEY_LEISURE + " text" + ")");
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_WITH_CHILDREN_1 + "(" + KEY_ID + " integer primary key, " + KEY_LEISURE + " text" + ")");
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_WITH_CHILDREN_3 + "(" + KEY_ID + " integer primary key, " + KEY_LEISURE + " text" + ")");
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_WITH_CHILDREN_6 + "(" + KEY_ID + " integer primary key, " + KEY_LEISURE + " text" + ")");

        long start = System.currentTimeMillis();
        sqLiteDatabase.beginTransaction();
        try {
            addValue(sqLiteDatabase,"Рей Брэдбери \"451° по Фаренгейту\"",TABLE_READING);
            addValue(sqLiteDatabase,"Грегори Дэвид Робертс \"Шантарам\"",TABLE_READING);
            addValue(sqLiteDatabase,"Джордж Оруэлл \"1984\"",TABLE_READING);
            addValue(sqLiteDatabase,"Михаил Афанасьевич Булгаков \"Мастер и Маргарита\"",TABLE_READING);
            addValue(sqLiteDatabase,"Эрих Мария Ремарк \"Три товарища\"",TABLE_READING);
            addValue(sqLiteDatabase,"Рей Брэдбери \"Вино из одуванчиков\"",TABLE_READING);
            addValue(sqLiteDatabase,"Харпер Ли \"Убить пересмешника\"",TABLE_READING);
            addValue(sqLiteDatabase,"Даниел Киз \"Цветы для Элджернона\"",TABLE_READING);
            addValue(sqLiteDatabase,"Антуан де Сент-Экзюпери \"Маленький принц\"",TABLE_READING);
            addValue(sqLiteDatabase,"Джером Д. Сэлинджер \"Над пропастью во ржи\"",TABLE_READING);
            addValue(sqLiteDatabase,"Грегори Дэвид Робертс \"Тень горы\"",TABLE_READING);
            addValue(sqLiteDatabase,"Габриэль Гарсиа Маркес \"Сто лет одиночества\"",TABLE_READING);
            addValue(sqLiteDatabase,"Лев Толстой \"Анна Каренина\"",TABLE_READING);
            addValue(sqLiteDatabase,"Айн Рэнд \"Атлант расправил плечи\"",TABLE_READING);
            addValue(sqLiteDatabase,"Энди Вейер \"Марсианин\"",TABLE_READING);
            addValue(sqLiteDatabase,"Федор Михайлович Достоевский \"Преступление и наказание\"",TABLE_READING);

            addValue(sqLiteDatabase,"Значение 1",TABLE_FOR_COMPANY);
            addValue(sqLiteDatabase,"Значение 2",TABLE_FOR_COMPANY);

            addValue(sqLiteDatabase,"Значение 1",TABLE_FOR_ONE);
            addValue(sqLiteDatabase,"Значение 2",TABLE_FOR_ONE);

            addValue(sqLiteDatabase,"Значение 1",TABLE_WITH_CHILDREN_1);
            addValue(sqLiteDatabase,"Значение 2",TABLE_WITH_CHILDREN_1);

            addValue(sqLiteDatabase,"Значение 1",TABLE_WITH_CHILDREN_3);
            addValue(sqLiteDatabase,"Значение 2",TABLE_WITH_CHILDREN_3);

            addValue(sqLiteDatabase,"Значение 1",TABLE_WITH_CHILDREN_6);
            addValue(sqLiteDatabase,"Значение 2",TABLE_WITH_CHILDREN_6);

            sqLiteDatabase.setTransactionSuccessful();
        }finally {
            sqLiteDatabase.endTransaction();
        }
        long end = System.currentTimeMillis();
        String diff = Long.toString(end - start);
        Log.d("work", "time = "+diff+" ms");
        Log.d("work","onCreate finish");
    }


    private void addValue(SQLiteDatabase sqLiteDatabase,String value,String nameTable){
        contentValues.put(KEY_LEISURE, value);
        sqLiteDatabase.insert(nameTable,null,contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("work","onUpgrade");
        sqLiteDatabase.execSQL("drop table if exists " +TABLE_READING );
        sqLiteDatabase.execSQL("drop table if exists " +TABLE_FOR_COMPANY );
        sqLiteDatabase.execSQL("drop table if exists " +TABLE_FOR_ONE );
        sqLiteDatabase.execSQL("drop table if exists " +TABLE_WITH_CHILDREN_1 );
        sqLiteDatabase.execSQL("drop table if exists " +TABLE_WITH_CHILDREN_3 );
        sqLiteDatabase.execSQL("drop table if exists " +TABLE_WITH_CHILDREN_6 );
        onCreate(sqLiteDatabase);


    }

     String getRandomStr(String nameTable) {
         db = this.getReadableDatabase();
         Cursor count = db.rawQuery("select count(*) from "+nameTable,null);
         count.moveToFirst();
         int countTable = count.getInt(0);
         int i = (int) (Math.random()*countTable);
         Cursor c = db.rawQuery("select "+KEY_LEISURE+" from "+nameTable+ " where "+KEY_ID+" =?",new String[]{Integer.toString(i+1)});
         String str;
         Log.d("work","выбираем строку "+(i+1));
         c.moveToFirst();
         str =c.getString(c.getColumnIndex(KEY_LEISURE));
         c.close();
         count.close();
        return str;

    }

    @Override
    public synchronized void close() {
        if(db != null)
            db.close();
        super.close();
    }



}
