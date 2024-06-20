package br.com.fiap.techchallenge.kongfood.customer.domain.usecases

import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerRequestModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.presenters.CustomerPresenter
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.repository.CustomerRepository
import br.com.fiap.techchallenge.kongfood.customer.domain.entities.CustomerFactory
import java.util.*

class CustomerUpdateInteractor(
    val customerRepository: CustomerRepository,
    val customerFactory: CustomerFactory,
    val customerPresenter: CustomerPresenter,
    val customerSearchBoundary: CustomerSearchBoundary
) : CustomerUpdateBoundary {
    override fun update(customerRequestModel: CustomerRequestModel, id: String): CustomerResponseModel {
        val customer = customerRepository.findCustomerById(UUID.fromString(id))
        customerSearchBoundary.exisits(customer, UUID.fromString(id))
        if (customerRequestModel.id!! != customer.get().id) {
            customerPresenter.prepareFailureResponse("Customer id cannot be changed")
        }
        customerSearchBoundary.verifyIfCustomerByEmailAlreadyExists(customerRequestModel)
        if (customerRequestModel.cpf != customer.get().cpf.toString()) {
            customerPresenter.prepareFailureResponse("CPF cannot be changed")
        }
        if (!customer.get().isActive) {
            customerPresenter.prepareFailureResponse("Customer is not active")
        }

        val updatedCustomer = customerFactory.create(
            id,
            customerRequestModel.name,
            customerRequestModel.email,
            customerRequestModel.phone,
            customerRequestModel.cpf,
            true
        )
        customerRepository.save(updatedCustomer)
        return customerPresenter.prepareSuccessResponse(updatedCustomer)
    }
}