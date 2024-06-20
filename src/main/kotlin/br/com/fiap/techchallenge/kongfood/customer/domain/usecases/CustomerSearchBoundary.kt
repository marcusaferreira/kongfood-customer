package br.com.fiap.techchallenge.kongfood.customer.domain.usecases

import br.com.fiap.techchallenge.kongfood.customer.domain.entities.Customer
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerRequestModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel
import java.util.*

interface CustomerSearchBoundary {

    fun findCustomerByCpf(cpf: String): CustomerResponseModel

    fun findCustomerById(id: String): CustomerResponseModel

    fun findCustomerByEmail(email: String): CustomerResponseModel
    fun exisits(customer: Optional<Customer>, customerID: UUID)
    fun verifyIfCustomerByEmailAlreadyExists(customerRequestModel: CustomerRequestModel)
}