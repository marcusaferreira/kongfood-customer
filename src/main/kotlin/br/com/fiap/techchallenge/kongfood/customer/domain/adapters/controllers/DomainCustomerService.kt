package br.com.fiap.techchallenge.kongfood.customer.domain.adapters.controllers

import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerRequestModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel
import br.com.fiap.techchallenge.kongfood.customer.domain.usecases.CustomerChangeStatusBoundary
import br.com.fiap.techchallenge.kongfood.customer.domain.usecases.CustomerRegisterBoundary
import br.com.fiap.techchallenge.kongfood.customer.domain.usecases.CustomerSearchBoundary
import br.com.fiap.techchallenge.kongfood.customer.domain.usecases.CustomerUpdateBoundary
import java.util.*

class DomainCustomerService(
    private val customerRegisterBoundary: CustomerRegisterBoundary,
    private val customerUpdateBoundary: CustomerUpdateBoundary,
    private val customerChangeStatusBoundary: CustomerChangeStatusBoundary,
    private val customerSearchBoundary: CustomerSearchBoundary
) : CustomerService {
    override fun createCustomer(customerRequestModel: CustomerRequestModel): String {
        return customerRegisterBoundary.register(customerRequestModel).id
    }

    override fun updateCustomer(customerRequestModel: CustomerRequestModel, id: String): CustomerResponseModel {
        return customerUpdateBoundary.update(customerRequestModel, id)
    }

    override fun deActivateCustomer(id: String) {
        customerChangeStatusBoundary.deactivate(UUID.fromString(id))
    }

    override fun findCustomerByCpf(cpf: String): CustomerResponseModel {
        return customerSearchBoundary.findCustomerByCpf(cpf)
    }

    override fun findCustomerById(id: String): CustomerResponseModel {
        return customerSearchBoundary.findCustomerById(id)
    }

    override fun findCustomerByEmail(email: String): CustomerResponseModel {
        return customerSearchBoundary.findCustomerByEmail(email)
    }

    override fun activateCustomer(id: String) {
        customerChangeStatusBoundary.activate(UUID.fromString(id))
    }


}