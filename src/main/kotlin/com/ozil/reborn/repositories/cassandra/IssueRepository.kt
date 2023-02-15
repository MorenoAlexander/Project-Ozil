package com.ozil.reborn.repositories.cassandra

import com.ozil.reborn.entities.cassandraEntities.Issue
import org.springframework.data.cassandra.repository.CassandraRepository

@org.springframework.stereotype.Repository
interface IssueRepository : CassandraRepository<Issue,String> {

    fun getIssueByTicketId(id: String): Issue?

    fun getIssuesByLabels(labels: HashSet<String>): List<Issue>


    fun searchIssuesByTicketIdOrTitleOrLabels(id: String, title: String, labels: HashSet<String>): List<Issue>

}
