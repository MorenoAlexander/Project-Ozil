package com.ozil.reborn.entities.cassandraEntities

import lombok.Getter
import lombok.Setter
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.*;

@Table
@Getter
@Setter
open class Issue {
    @PrimaryKeyColumn(name = "ticket_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    open var ticketId: String? = null


    @PrimaryKeyColumn(name = "title", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    open var title: String? = null;

    @Column
    open var labels: HashSet<String>? = HashSet<String>();

}
