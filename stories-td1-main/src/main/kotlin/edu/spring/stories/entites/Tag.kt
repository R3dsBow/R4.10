package edu.spring.stories.entites

import jakarta.persistence.*

@Entity
@Table(name="tag")
open class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int?=null

    @Column(length = 255)
    open var color:String?=null

    @Column(length = 20)
    open var label:String?=null

    @ManyToMany
    @JoinColumn(name="stories", nullable = false)
    open val story= mutableSetOf<Story>()

}