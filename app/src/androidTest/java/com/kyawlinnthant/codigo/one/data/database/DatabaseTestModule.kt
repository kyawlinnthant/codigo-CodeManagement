package com.kyawlinnthant.codigo.one.data.database

import android.content.Context
import androidx.room.Room
import com.kyawlinnthant.codigo.one.data.database.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object DatabaseTestModule {
    @Provides
    @Singleton
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ): MovieDatabase = Room.inMemoryDatabaseBuilder(
        context,
        MovieDatabase::class.java
    ).allowMainThreadQueries().build()
}
