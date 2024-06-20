package br.com.fiap.techchallenge.kongfood.customer.domain.usecases

import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerRequestModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel

fun interface CustomerUpdateBoundary {
    fun update(customerRequestModel: CustomerRequestModel, id: String): CustomerResponseModel
}