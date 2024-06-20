package br.com.fiap.techchallenge.kongfood.customer.framework.drivers.db.repository

import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.models.CustomerRequestModel
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.repository.CustomerRepository
import br.com.fiap.techchallenge.kongfood.customer.domain.entities.CPF
import br.com.fiap.techchallenge.kongfood.customer.domain.entities.Customer
import br.com.fiap.techchallenge.kongfood.customer.framework.drivers.db.orm.CustomerEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerDBRepository(
    val customerEntityRepository: CustomerEntityRepository
) : CustomerRepository{
    override fun save(customer: Customer) {
        val customerEntity = CustomerEntity(
            id = customer.id.toString(),
            name = customer.name,
            email = customer.email,
            phone = customer.phone,
            cpf = customer.cpf?.cpf,
            isActive = customer.isActive
        )
        customerEntityRepository.save(customerEntity)
    }

    override fun findCustomerByCpf(cpf: String): Optional<Customer> {
        return verifyPresentAndReturn(customerEntityRepository.findByCpf(cpf))
    }

    override fun findCustomerById(id: UUID): Optional<Customer> {
        return verifyPresentAndReturn(customerEntityRepository.findById(id.toString()))
    }

    override fun findCustomerByEmail(email: String): Optional<Customer> {
        val customerEntity = customerEntityRepository.findByEmail(email)
        return verifyPresentAndReturn(customerEntity)
    }

    override fun verifyIfCustomerByEmailAlreadyExists(customerRequestModel: CustomerRequestModel): Boolean {
        return customerEntityRepository.findByEmail(customerRequestModel.email).isPresent
    }

    private fun verifyPresentAndReturn(customerEntity: Optional<CustomerEntity>): Optional<Customer> {
        return if (customerEntity.isPresent) {
            val entity = customerEntity.get()
            Optional.of(
                Customer(
                    id = UUID.fromString(entity.id),
                    name = entity.name,
                    email = entity.email,
                    phone = entity.phone,
                    cpf = entity.cpf?.let { CPF(it) },
                    isActive = entity.isActive
                )
            )
        } else {
            Optional.empty()
        }
    }
}