package org.gogood.`data`.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass
import org.gogood.`data`.database.daos.FavoritesListDAO
import org.gogood.`data`.database.daos.FavoritesListDAO_Impl
import org.gogood.`data`.database.daos.TripsDAO
import org.gogood.`data`.database.daos.TripsDAO_Impl

@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class GoGoodDatabase_Impl : GoGoodDatabase() {
  private val _favoritesListDAO: Lazy<FavoritesListDAO> = lazy {
    FavoritesListDAO_Impl(this)
  }


  private val _tripsDAO: Lazy<TripsDAO> = lazy {
    TripsDAO_Impl(this)
  }


  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "925cdc68c2f4c87001ce79f1f989909f", "b543b12e8a2dfa38f711441fa8cc49f6") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `FavoritesList` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `items` TEXT NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_FavoritesList_id` ON `FavoritesList` (`id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Trips` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `items` TEXT NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_Trips_id` ON `Trips` (`id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '925cdc68c2f4c87001ce79f1f989909f')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `FavoritesList`")
        connection.execSQL("DROP TABLE IF EXISTS `Trips`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsFavoritesList: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFavoritesList.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoritesList.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoritesList.put("items", TableInfo.Column("items", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFavoritesList: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesFavoritesList: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesFavoritesList.add(TableInfo.Index("index_FavoritesList_id", false, listOf("id"),
            listOf("ASC")))
        val _infoFavoritesList: TableInfo = TableInfo("FavoritesList", _columnsFavoritesList,
            _foreignKeysFavoritesList, _indicesFavoritesList)
        val _existingFavoritesList: TableInfo = read(connection, "FavoritesList")
        if (!_infoFavoritesList.equals(_existingFavoritesList)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |FavoritesList(org.gogood.data.database.entites.FavoritesListEntity).
              | Expected:
              |""".trimMargin() + _infoFavoritesList + """
              |
              | Found:
              |""".trimMargin() + _existingFavoritesList)
        }
        val _columnsTrips: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTrips.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTrips.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTrips.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTrips.put("items", TableInfo.Column("items", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTrips: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesTrips: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesTrips.add(TableInfo.Index("index_Trips_id", false, listOf("id"), listOf("ASC")))
        val _infoTrips: TableInfo = TableInfo("Trips", _columnsTrips, _foreignKeysTrips,
            _indicesTrips)
        val _existingTrips: TableInfo = read(connection, "Trips")
        if (!_infoTrips.equals(_existingTrips)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Trips(org.gogood.data.database.entites.TripsEntity).
              | Expected:
              |""".trimMargin() + _infoTrips + """
              |
              | Found:
              |""".trimMargin() + _existingTrips)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "FavoritesList", "Trips")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(FavoritesListDAO::class, FavoritesListDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(TripsDAO::class, TripsDAO_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun favoritesListDao(): FavoritesListDAO = _favoritesListDAO.value

  public override fun tripsDao(): TripsDAO = _tripsDAO.value
}
