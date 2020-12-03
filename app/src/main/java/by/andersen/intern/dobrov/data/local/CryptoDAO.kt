package by.andersen.intern.dobrov.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface CryptoDAO {

    @get:Query("SELECT * FROM cryptodata")
    val allCryptoData: Observable<List<CryptoData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptoData(favoriteCryptoData: CryptoData): Completable

    @Query("DELETE FROM cryptodata")
    fun deleteCryptoData(): Completable
}