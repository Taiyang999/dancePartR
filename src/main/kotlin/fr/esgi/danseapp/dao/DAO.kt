package fr.esgi.danseapp.dao

interface DAO<T> {
    fun get(id : Long) : T?

    fun getAll() : List<T>

    fun save(t : T) : Unit

    fun update(t : T) : Unit

    fun delete(t : T) : Unit
}