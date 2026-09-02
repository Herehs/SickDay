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
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
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
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.date)
        statement.bindDouble(3, entity.generalHealth.toDouble())
        statement.bindText(4, entity.note)
        statement.bindDouble(5, entity.drowsiness.toDouble())
        statement.bindDouble(6, entity.pressure.toDouble())
        statement.bindDouble(7, entity.weakness.toDouble())
      }
    }
    this.__deleteAdapterOfNoteEntity = object : EntityDeleteOrUpdateAdapter<NoteEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `notes` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: NoteEntity) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfNoteEntity = object : EntityDeleteOrUpdateAdapter<NoteEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `notes` SET `id` = ?,`date` = ?,`generalHealth` = ?,`note` = ?,`drowsiness` = ?,`pressure` = ?,`weakness` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: NoteEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.date)
        statement.bindDouble(3, entity.generalHealth.toDouble())
        statement.bindText(4, entity.note)
        statement.bindDouble(5, entity.drowsiness.toDouble())
        statement.bindDouble(6, entity.pressure.toDouble())
        statement.bindDouble(7, entity.weakness.toDouble())
        statement.bindLong(8, entity.id)
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

  public override suspend fun getById(id: Long): NoteEntity? {
    val _sql: String = "SELECT * FROM notes WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfGeneralHealth: Int = getColumnIndexOrThrow(_stmt, "generalHealth")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _columnIndexOfDrowsiness: Int = getColumnIndexOrThrow(_stmt, "drowsiness")
        val _columnIndexOfPressure: Int = getColumnIndexOrThrow(_stmt, "pressure")
        val _columnIndexOfWeakness: Int = getColumnIndexOrThrow(_stmt, "weakness")
        val _result: NoteEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpGeneralHealth: Float
          _tmpGeneralHealth = _stmt.getDouble(_columnIndexOfGeneralHealth).toFloat()
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpDrowsiness: Float
          _tmpDrowsiness = _stmt.getDouble(_columnIndexOfDrowsiness).toFloat()
          val _tmpPressure: Float
          _tmpPressure = _stmt.getDouble(_columnIndexOfPressure).toFloat()
          val _tmpWeakness: Float
          _tmpWeakness = _stmt.getDouble(_columnIndexOfWeakness).toFloat()
          _result = NoteEntity(_tmpId,_tmpDate,_tmpGeneralHealth,_tmpNote,_tmpDrowsiness,_tmpPressure,_tmpWeakness)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
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
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpGeneralHealth: Float
          _tmpGeneralHealth = _stmt.getDouble(_columnIndexOfGeneralHealth).toFloat()
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpDrowsiness: Float
          _tmpDrowsiness = _stmt.getDouble(_columnIndexOfDrowsiness).toFloat()
          val _tmpPressure: Float
          _tmpPressure = _stmt.getDouble(_columnIndexOfPressure).toFloat()
          val _tmpWeakness: Float
          _tmpWeakness = _stmt.getDouble(_columnIndexOfWeakness).toFloat()
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
