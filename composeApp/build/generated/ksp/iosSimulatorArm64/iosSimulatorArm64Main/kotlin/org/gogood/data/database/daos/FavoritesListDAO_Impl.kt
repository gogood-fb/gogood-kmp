package org.gogood.`data`.database.daos

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import org.gogood.`data`.database.entites.FavoritesListEntity

@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FavoritesListDAO_Impl(
  __db: RoomDatabase,
) : FavoritesListDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfFavoritesListEntity: EntityInsertAdapter<FavoritesListEntity>

  private val __deleteAdapterOfFavoritesListEntity: EntityDeleteOrUpdateAdapter<FavoritesListEntity>

  private val __updateAdapterOfFavoritesListEntity: EntityDeleteOrUpdateAdapter<FavoritesListEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfFavoritesListEntity = object : EntityInsertAdapter<FavoritesListEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `FavoritesList` (`id`,`name`,`items`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FavoritesListEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.items)
      }
    }
    this.__deleteAdapterOfFavoritesListEntity = object :
        EntityDeleteOrUpdateAdapter<FavoritesListEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `FavoritesList` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: FavoritesListEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfFavoritesListEntity = object :
        EntityDeleteOrUpdateAdapter<FavoritesListEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `FavoritesList` SET `id` = ?,`name` = ?,`items` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: FavoritesListEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.items)
        statement.bindLong(4, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertFavoritesList(favoritesList: FavoritesListEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfFavoritesListEntity.insert(_connection, favoritesList)
  }

  public override suspend fun deleteFavoritesList(favoritesList: FavoritesListEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfFavoritesListEntity.handle(_connection, favoritesList)
  }

  public override suspend fun updateFavoritesList(favoritesList: FavoritesListEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfFavoritesListEntity.handle(_connection, favoritesList)
  }

  public override suspend fun getAllFavoritesLists(): List<FavoritesListEntity> {
    val _sql: String = "SELECT * FROM FavoritesList"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfItems: Int = getColumnIndexOrThrow(_stmt, "items")
        val _result: MutableList<FavoritesListEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FavoritesListEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpItems: String
          _tmpItems = _stmt.getText(_cursorIndexOfItems)
          _item = FavoritesListEntity(_tmpId,_tmpName,_tmpItems)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getFavoritesListById(id: Int): FavoritesListEntity? {
    val _sql: String = "SELECT * FROM FavoritesList WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfItems: Int = getColumnIndexOrThrow(_stmt, "items")
        val _result: FavoritesListEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpItems: String
          _tmpItems = _stmt.getText(_cursorIndexOfItems)
          _result = FavoritesListEntity(_tmpId,_tmpName,_tmpItems)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
