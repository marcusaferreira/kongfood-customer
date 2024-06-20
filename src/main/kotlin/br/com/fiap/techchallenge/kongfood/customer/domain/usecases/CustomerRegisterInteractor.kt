package br.com.fiap.techchallenge.kongfood.customer.domain.usecases

import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerRequestModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerResponseModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.presenters.CustomerPresenter
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.repository.CustomerRepository
import br.com.fiap.techchallenge.kongfood.customer.domain.entities.CustomerFactory

class CustomerRegisterInteractor(
    val customerRepository: CustomerRepository,
    val customerFactory: CustomerFactory,
    val customerPresenter: CustomerPresenter,
    val customerSearchBoundary: CustomerSearchBoundary
) : CustomerRegisterBoundary {
    override fun register(customerRequestModel: CustomerRequestModel): CustomerResponseModel {
        verifyIfCustomerAlreadyExists(customerRequestModel)
        val newCustomer = customerFactory.create(
            null,
            customerRequestModel.name,
            customerRequestModel.email,
            customerRequestModel.phone,
            customerRequestModel.cpf,
            true
        )
        customerRepository.save(newCustomer)
        return customerPresenter.prepareSuccessResponse(newCustomer)
    }

    private fun verifyIfCustomerAlreadyExists(customerRequestModel: CustomerRequestModel) {
        verifyIfCustomerByCpfAlreadyExists(customerRequestModel)
        customerSearchBoundary.verifyIfCustomerByEmailAlreadyExists(customerRequestModel)
    }

    private fun verifyIfCustomerByCpfAlreadyExists(customerRequestModel: CustomerRequestModel) {
        val customer = customerRepository.findCustomerByCpf(customerRequestModel.cpf!!)
        if (customer.isPresent && (customerRequestModel.id == null || customerRequestModel.id != customer.get().id)) {
            customerPresenter.prepareFailureResponse("Customer with CPF ${customerRequestModel.cpf} already exists")
        }
    }
}