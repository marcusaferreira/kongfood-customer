package br.com.fiap.techchallenge.kongfood.customer.domain.adapters.presenters

import br.com.fiap.techchallenge.kongfood.customer.domain.entities.Customer
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel

interface CustomerPresenter {

    fun prepareSuccessResponse(customer: Customer): CustomerResponseModel

    fun prepareFailureResponse(error: String)

}