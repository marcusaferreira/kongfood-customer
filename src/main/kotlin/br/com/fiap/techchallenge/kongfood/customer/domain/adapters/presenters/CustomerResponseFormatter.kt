package br.com.fiap.techchallenge.kongfood.customer.domain.adapters.presenters

import br.com.fiap.techchallenge.kongfood.customer.domain.DomainException
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel
import br.com.fiap.techchallenge.kongfood.customer.domain.entities.Customer

class CustomerResponseFormatter: CustomerPresenter {
    override fun prepareSuccessResponse(customer: Customer): CustomerResponseModel {
        return CustomerResponseModel(
            id = customer.id.toString(),
            name = customer.name,
            email = customer.email,
            phone = customer.phone,
            cpf = customer.cpf?.cpf)
    }

    override fun prepareFailureResponse(error: String) {
        throw DomainException(error)
    }
}