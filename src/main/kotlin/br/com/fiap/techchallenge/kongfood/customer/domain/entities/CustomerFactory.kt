package br.com.fiap.techchallenge.kongfood.customer.domain.entities

import br.com.fiap.techchallenge.kongfood.customer.domain.entities.Customer

fun interface CustomerFactory {

    fun create(
        id: String?, name: String,
        email: String,
        phone: String?,
        cpf: String?,
        isActive: Boolean
    ): Customer
}