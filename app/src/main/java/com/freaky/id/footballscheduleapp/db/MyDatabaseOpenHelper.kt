package com.freaky.id.footballscheduleapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.MATCH_ID to TEXT + UNIQUE,
            Favorite.DATE_EVENT to TEXT,
            Favorite.TEAM_HOME_ID to TEXT,
            Favorite.TEAM_HOME_NAME to TEXT,
            Favorite.TEAM_HOME_SCORE to TEXT,
            Favorite.TEAM_AWAY_ID to TEXT,
            Favorite.TEAM_AWAY_NAME to TEXT,
            Favorite.TEAM_AWAY_SCORE to TEXT)

        db.createTable(FavoriteTeams.TABLE_FAVORITE, true,
                        FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeams.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeams.TEAM_NAME to TEXT,
            FavoriteTeams.TEAM_BANNER to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeams.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)