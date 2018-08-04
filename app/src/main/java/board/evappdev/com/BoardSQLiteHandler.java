package board.evappdev.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Avram on 05.12.2017.
 * Это класс для создания БД для сохранения персональных данных пользователя и их использования
 */

public class BoardSQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = BoardSQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Board_android.db";

    private static final String TABLE_BOARD = "Board";

    private static final String KEY_ID = "id";
    private static final String TITLE = "title";
    private static final String REGION = "region";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_PATH = "image_path";

    public BoardSQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_BOARD + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT,"
                + REGION + " TEXT," + PRICE + " TEXT,"
                + DESCRIPTION + " TEXT," + IMAGE_PATH + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOARD);

        // Create tables again
        onCreate(db);
    }

    public void addBoard(Board board) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, board.getTitle());
        values.put(REGION, board.getRegion());
        values.put(PRICE, board.getPrice());
        values.put(DESCRIPTION, board.getDescription());
        values.put(IMAGE_PATH, board.getPathImage());


        long id = db.insert(TABLE_BOARD, null, values);
        db.close();

        Log.d(TAG, "New board inserted into sqlite: " + id);
    }

    public List<Board> getBoardData() {

        List<Board> boardList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_BOARD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        if (cursor.moveToFirst() == false) {
            cursor.close();
            db.close();
            return null;
        }

        while (cursor.moveToNext()) {
            Board board = new Board();
            if (cursor.getCount() > 0) {
                board.setTitle(cursor.getString(1));
                board.setRegion(cursor.getString(2));
                board.setPrice(cursor.getInt(3));
                board.setDescription(cursor.getString(4));
                board.setPathImage(cursor.getString(5));
            }
            boardList.add(board);
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching board from Sqlite: " + boardList.toString());

        return boardList;
    }

    /**
     * Удаление всех данных из таблицы БД
     * */
    public void deleteBoardData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_BOARD, null, null);
        db.close();

        Log.d(TAG, "Deleted all boards info from sqlite");
    }

}