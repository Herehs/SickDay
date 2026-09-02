package com.example.up.`data`.local.database

import androidx.room3.InvalidationTracker
import androidx.room3.RoomOpenDelegate
import androidx.room3.migration.AutoMigrationSpec
import androidx.room3.migration.Migration
import androidx.room3.util.TableInfo
import androidx.room3.util.TableInfo.Companion.read
import androidx.room3.util.dropFtsSyncTriggers
import androidx.room3.util.performClear
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.example.up.`data`.local.database.dao.NoteDao
import com.example.up.`data`.local.database.dao.NoteDao_Impl
import javax.`annotation`.processing.Generated
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

@Generated(value = ["androidx.room3.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL", "MemberExtensionConflict"])
internal class AppDatabase_Impl : AppDatabase() {
  private val _noteDao: Lazy<NoteDao> = lazy {
    NoteDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(2, "784233d71fa69cedd65b85490c3605d1", "c90dab69620cfe8ef1e7539ef0417fc1") {
      public override suspend fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `notes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` INTEGER NOT NULL, `generalHealth` INTEGER NOT NULL, `note` TEXT NOT NULL, `drowsiness` INTEGER NOT NULL, `pressure` INTEGER NOT NULL, `weakness` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '784233d71fa69cedd65b85490c3605d1')")
      }

      public override suspend fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `notes`")
      }

      public override suspend fun onCreate(connection: SQLiteConnection) {
      }

      public override suspend fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override suspend fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override suspend fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override suspend fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsNotes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsNotes.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotes.put("date", TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotes.put("generalHealth", TableInfo.Column("generalHealth", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotes.put("note", TableInfo.Column("note", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotes.put("drowsiness", TableInfo.Column("drowsiness", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotes.put("pressure", TableInfo.Column("pressure", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotes.put("weakness", TableInfo.Column("weakness", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysNotes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesNotes: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoNotes: TableInfo = TableInfo("notes", _columnsNotes, _foreignKeysNotes, _indicesNotes)
        val _existingNotes: TableInfo = read(connection, "notes")
        if (!_infoNotes.equals(_existingNotes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |notes(com.example.up.data.local.database.entity.NoteEntity).
              | Expected:
              |""".trimMargin() + _infoNotes + """
              |
              | Found:
              |""".trimMargin() + _existingNotes)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "notes")
  }

  public override suspend fun clearAllTables() {
    performClear(this, false, "notes")
  }

  protected override fun getRequiredColumnTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _columnTypeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _columnTypeConvertersMap.put(NoteDao::class, NoteDao_Impl.getRequiredColumnConverters())
    return _columnTypeConvertersMap
  }

  protected override fun getRequiredDaoReturnTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _daoReturnTypeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _daoReturnTypeConvertersMap.put(NoteDao::class, NoteDao_Impl.getRequiredDaoReturnTypeConverters())
    return _daoReturnTypeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun noteDao(): NoteDao = _noteDao.value
}
