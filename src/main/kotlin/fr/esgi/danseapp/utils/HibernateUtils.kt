package fr.esgi.danseapp.utils

import org.hibernate.SessionFactory
import org.hibernate.boot.Metadata
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistry
import org.hibernate.boot.registry.StandardServiceRegistryBuilder


object HibernateUtils {
    private var sessionFactory: SessionFactory? = null
    private var registry: StandardServiceRegistry? = null
    fun getSessionFactory(): SessionFactory? {
        return sessionFactory
    }

    // thread-safe implementation of the singleton pattern
    init {
        // Create sessionFactory from the hibernate.cfg.xml file
        registry = StandardServiceRegistryBuilder().configure().build()
        val sources = MetadataSources(registry)
        val metadata: Metadata? = sources.metadataBuilder.build()
        sessionFactory = metadata?.sessionFactoryBuilder?.build()
    }
}