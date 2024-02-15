package me.vaimon.rickandmortywiki.di.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.vaimon.rickandmortywiki.data.db.AppDatabase
import me.vaimon.rickandmortywiki.data.db.CharacterDao
import me.vaimon.rickandmortywiki.data.db.PageKeyDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase): CharacterDao {
        return db.characterDao()
    }

    @Singleton
    @Provides
    fun providePageKeyDao(db: AppDatabase): PageKeyDao {
        return db.pageKeyDao()
    }
}