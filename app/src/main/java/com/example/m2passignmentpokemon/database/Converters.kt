package com.example.m2passignmentpokemon.database

import androidx.room.TypeConverter
import com.example.m2passignmentpokemon.model.*
import com.example.m2passignmentpokemon.model.Set
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromAbilitiesList(value: List<Ability>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAbilitiesList(value: String): List<Ability>? {
        val listType = object : TypeToken<List<Ability>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromAttacksList(value: List<Attack>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAttacksList(value: String): List<Attack>? {
        val listType = object : TypeToken<List<Attack>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromCardmarket(value: Cardmarket?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCardmarket(value: String): Cardmarket? {
        return gson.fromJson(value, Cardmarket::class.java)
    }

    @TypeConverter
    fun fromImages(value: Images?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toImages(value: String): Images? {
        return gson.fromJson(value, Images::class.java)
    }

    @TypeConverter
    fun fromLegalities(value: Legalities?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toLegalities(value: String): Legalities? {
        return gson.fromJson(value, Legalities::class.java)
    }

    @TypeConverter
    fun fromResistanceList(value: List<Resistance>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toResistanceList(value: String): List<Resistance>? {
        val listType = object : TypeToken<List<Resistance>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromSet(value: Set?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toSet(value: String): Set? {
        return gson.fromJson(value, Set::class.java)
    }

    @TypeConverter
    fun fromTcgplayer(value: Tcgplayer?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toTcgplayer(value: String): Tcgplayer? {
        return gson.fromJson(value, Tcgplayer::class.java)
    }

    @TypeConverter
    fun fromWeaknesseList(value: List<Weaknesse>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toWeaknesseList(value: String): List<Weaknesse>? {
        val listType = object : TypeToken<List<Weaknesse>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromIntList(value: List<Int>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIntList(value: String): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, listType)
    }
}
