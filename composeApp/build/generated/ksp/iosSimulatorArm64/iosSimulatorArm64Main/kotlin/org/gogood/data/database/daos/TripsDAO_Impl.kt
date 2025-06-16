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
import org.gogood.`data`.database.entites.TripsEntity

@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class TripsDAO_Impl(
  __db: RoomDatabase,
) : TripsDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfTripsEntity: EntityInsertAdapter<TripsEntity>

  private val __deleteAdapterOfTripsEntity: EntityDeleteOrUpdateAdapter<TripsEntity>

  private val __updateAdapterOfTripsEntity: EntityDeleteOrUpdateAdapter<TripsEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfTripsEntity = object : EntityInsertAdapter<TripsEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `Trips` (`id`,`name`,`description`,`items`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: TripsEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.description)
        statement.bindText(4, entity.items)
      }
    }
    this.__deleteAdapterOfTripsEntity = object : EntityDeleteOrUpdateAdapter<TripsEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `Trips` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: TripsEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfTripsEntity = object : EntityDeleteOrUpdateAdapter<TripsEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `Trips` SET `id` = ?,`name` = ?,`description` = ?,`items` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: TripsEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.description)
        statement.bindText(4, entity.items)
        statement.bindLong(5, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertTripsEntity(favoritesList: TripsEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfTripsEntity.insert(_connection, favoritesList)
  }

  public override suspend fun deleteTripsEntity(favoritesList: TripsEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfTripsEntity.handle(_connection, favoritesList)
  }

  public override suspend fun updateTripsEntity(favoritesList: TripsEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfTripsEntity.handle(_connection, favoritesList)
  }

  public override suspend fun getAllTripsEntity(): List<TripsEntity> {
    val _sql: String = "SELECT * FROM Trips"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfItems: Int = getColumnIndexOrThrow(_stmt, "items")
        val _result: MutableList<TripsEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: TripsEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpItems: String
          _tmpItems = _stmt.getText(_cursorIndexOfItems)
          _item = TripsEntity(_tmpId,_tmpName,_tmpDescription,_tmpItems)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTripsEntityById(id: Int): TripsEntity? {
    val _sql: String = "SELECT * FROM Trips WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _cursorIndexOfItems: Int = getColumnIndexOrThrow(_stmt, "items")
        val _result: TripsEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_cursorIndexOfDescription)
          val _tmpItems: String
          _tmpItems = _stmt.getText(_cursorIndexOfItems)
          _result = TripsEntity(_tmpId,_tmpName,_tmpDescription,_tmpItems)
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
