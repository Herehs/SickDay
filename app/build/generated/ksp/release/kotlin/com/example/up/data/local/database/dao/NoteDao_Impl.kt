package com.example.up.`data`.local.database.dao

import androidx.room3.EntityDeleteOrUpdateAdapter
import androidx.room3.EntityInsertAdapter
import androidx.room3.RoomDatabase
import androidx.room3.coroutines.createFlow
import androidx.room3.util.getColumnIndexOrThrow
import androidx.room3.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.up.`data`.local.database.entity.NoteEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.ULong
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.toULong
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room3.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL", "MemberExtensionConflict"])
internal class NoteDao_Impl(
  __db: RoomDatabase,
) : NoteDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfNoteEntity: EntityInsertAdapter<NoteEntity>

  private val __deleteAdapterOfNoteEntity: EntityDeleteOrUpdateAdapter<NoteEntity>

  private val __updateAdapterOfNoteEntity: EntityDeleteOrUpdateAdapter<NoteEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfNoteEntity = object : EntityInsertAdapter<NoteEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `notes` (`id`,`date`,`generalHealth`,`note`,`drowsiness`,`pressure`,`weakness`) VALUES (nullif(?, 0),?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: NoteEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.date)
        statement.bindLong(3, entity.generalHealth.toLong())
        statement.bindText(4, entity.note)
        statement.bindLong(5, entity.drowsiness.toLong())
        statement.bindLong(6, entity.pressure.toLong())
        statement.bindLong(7, entity.weakness.toLong())
      }
    }
    this.__deleteAdapterOfNoteEntity = object : EntityDeleteOrUpdateAdapter<NoteEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `notes` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: NoteEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfNoteEntity = object : EntityDeleteOrUpdateAdapter<NoteEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `notes` SET `id` = ?,`date` = ?,`generalHealth` = ?,`note` = ?,`drowsiness` = ?,`pressure` = ?,`weakness` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: NoteEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.date)
        statement.bindLong(3, entity.generalHealth.toLong())
        statement.bindText(4, entity.note)
        statement.bindLong(5, entity.drowsiness.toLong())
        statement.bindLong(6, entity.pressure.toLong())
        statement.bindLong(7, entity.weakness.toLong())
        statement.bindLong(8, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(note: NoteEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfNoteEntity.insert(_connection, note)
  }

  public override suspend fun delete(note: NoteEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfNoteEntity.handle(_connection, note)
  }

  public override suspend fun update(note: NoteEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfNoteEntity.handle(_connection, note)
  }

  public override fun getAllAsFlow(): Flow<List<NoteEntity>> {
    val _sql: String = "SELECT * FROM notes ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("notes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfGeneralHealth: Int = getColumnIndexOrThrow(_stmt, "generalHealth")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _columnIndexOfDrowsiness: Int = getColumnIndexOrThrow(_stmt, "drowsiness")
        val _columnIndexOfPressure: Int = getColumnIndexOrThrow(_stmt, "pressure")
        val _columnIndexOfWeakness: Int = getColumnIndexOrThrow(_stmt, "weakness")
        val _result: MutableList<NoteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: NoteEntity
          val _tmpId: ULong
          _tmpId = _stmt.getLong(_columnIndexOfId).toULong()
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpGeneralHealth: Int
          _tmpGeneralHealth = _stmt.getLong(_columnIndexOfGeneralHealth).toInt()
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpDrowsiness: Int
          _tmpDrowsiness = _stmt.getLong(_columnIndexOfDrowsiness).toInt()
          val _tmpPressure: Int
          _tmpPressure = _stmt.getLong(_columnIndexOfPressure).toInt()
          val _tmpWeakness: Int
          _tmpWeakness = _stmt.getLong(_columnIndexOfWeakness).toInt()
          _item = NoteEntity(_tmpId,_tmpDate,_tmpGeneralHealth,_tmpNote,_tmpDrowsiness,_tmpPressure,_tmpWeakness)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredColumnConverters(): List<KClass<*>> = emptyList()

    public fun getRequiredDaoReturnTypeConverters(): List<KClass<*>> = emptyList()
  }
}
