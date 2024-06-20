package br.com.fiap.techchallenge.kongfood.customer.domain.entities

import java.util.*

class CustomerFactoryImpl: CustomerFactory {
    override fun create(
        id: String?,
        name: String,
        email: String,
        phone: String?,
        cpf: String?,
        isActive: Boolean
    ): Customer {
        return Customer(
            id = if (id != null) UUID.fromString(id) else UUID.randomUUID(),
            name = name,
            email = email,
            phone = phone,
            cpf = if (cpf != null) CPF(cpf) else null,
            isActive = isActive)
    }
}