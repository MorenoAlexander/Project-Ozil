package com.ozil.reborn.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

@Configuration
@EnableCassandraRepositories
class CassandraConfig : AbstractCassandraConfiguration() {
    override fun getKeyspaceName(): String {
        return "ozil_reborn"
    }



    override fun getContactPoints(): String {
        return "localhost";
    }

    override fun getPort() : Int {
        return 9042;
    }





}
