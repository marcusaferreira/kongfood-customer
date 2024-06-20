package br.com.fiap.techchallenge.kongfood.customer.framework.drivers.db.orm

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class CustomerEntity(
    @Id
    val id: String,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "email",nullable = false, unique = true)
    val email: String,
    @Column(name = "phone",nullable = true)
    val phone: String?,
    @Column(name = "cpf", nullable = true, unique = true)
    val cpf: String?,
    @Column(name = "isActive",nullable = false )
    var isActive: Boolean = true,
) {
}