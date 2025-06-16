package org.gogood.`data`.database

import androidx.room.RoomDatabaseConstructor

public actual object GoGoodDatabaseConstructor : RoomDatabaseConstructor<GoGoodDatabase> {
  actual override fun initialize(): GoGoodDatabase =
      org.gogood.`data`.database.GoGoodDatabase_Impl()
}
