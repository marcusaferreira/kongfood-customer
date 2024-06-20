package br.com.fiap.techchallenge.kongfood.customer.domain.usecases

import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel
import java.util.*

interface CustomerChangeStatusBoundary {
    fun activate(customerID: UUID) : CustomerResponseModel

    fun deactivate(customerID: UUID) : CustomerResponseModel
}
