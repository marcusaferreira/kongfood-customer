package br.com.fiap.techchallenge.kongfood.customer.domain.adapters.controllers

import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerRequestModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel

interface CustomerService {

    fun createCustomer(customerRequestModel: CustomerRequestModel): String

    fun updateCustomer(customerRequestModel: CustomerRequestModel, id: String): CustomerResponseModel

    fun deActivateCustomer(id: String)

    fun findCustomerByCpf(cpf: String): CustomerResponseModel

    fun findCustomerById(id: String): CustomerResponseModel

    fun findCustomerByEmail(email: String): CustomerResponseModel

    fun activateCustomer(id: String)
}