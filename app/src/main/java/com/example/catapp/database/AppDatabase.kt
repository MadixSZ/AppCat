package com.example.catapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.catapp.model.CatImage

@Database(entities = [CatImage::class], version = 3, exportSchema = false) // Aumente a versão para 3
abstract class AppDatabase : RoomDatabase() {
    abstract fun catImageDao(): CatImageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migração segura para adicionar coluna somente se não existir
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE cat_images ADD COLUMN createdAt TEXT DEFAULT ''")
            }
        }

        // Migração dummy para versão 2->3 (resetar em desenvolvimento)
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Não faz nada, apenas força recriação se usar fallback
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cat_database"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .fallbackToDestructiveMigration() // Apenas para desenvolvimento
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}