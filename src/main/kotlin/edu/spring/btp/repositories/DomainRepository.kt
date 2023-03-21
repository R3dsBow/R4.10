package edu.spring.btp.repositories

import edu.spring.btp.entities.Domain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DomainRepository:JpaRepository<Domain, Int> {
    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" ORDER BY rand() LIMIT 1")
    fun getRandomDomain(): Domain

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" INNER JOIN \"domain_providers\" ON \"providers_id\"=:providerId ORDER BY rand() LIMIT 1")
    fun getRandomDomainFromProvider(providerId:Int): Domain

    @Query(nativeQuery = true, value = "SELECT * FROM \"domain\" WHERE \"name\" = :name")
    fun findByName(name: String):Domain

    @Query(nativeQuery = true, value = "SELECT * FROM \"domain\" WHERE \"parent_id\" IN (SELECT \"id\" FROM \"domain\" where \"name\" = :name)")
    fun findByParentName(name:String):List<Domain>

    @Query(nativeQuery = true, value = "SELECT * FROM \"domain\" WHERE \"parent\" IS NULL")
    fun findWithoutParent(name: String):List<Domain>

    @Query(nativeQuery = true, value = "SELECT * FROM \"domain\" WHERE \"parent_id\" IN (SELECT \"id\" FROM \"domain\")")
    fun findByParentId(id: Int):List<Domain>
//
//    fun findRandomByParentId(id: Int): Domain

}