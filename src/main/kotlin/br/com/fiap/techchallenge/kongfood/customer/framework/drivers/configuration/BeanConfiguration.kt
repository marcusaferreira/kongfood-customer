package br.com.fiap.techchallenge.kongfood.customer.framework.drivers.configuration

import br.com.fiap.techchallenge.kongfood.customer.CustomerApplication
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.controllers.CustomerService
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.controllers.DomainCustomerService
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.presenters.CustomerResponseFormatter
import br.com.fiap.techchallenge.kongfood.customer.domain.adapters.repository.CustomerRepository
import br.com.fiap.techchallenge.kongfood.customer.domain.entities.CustomerFactoryImpl
import br.com.fiap.techchallenge.kongfood.customer.domain.usecases.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [CustomerApplication::class])
class BeanConfiguration {

    @Bean
    fun customerSearchBoundary(customerRepository: CustomerRepository): CustomerSearchBoundary {
        val customerPresenter = CustomerResponseFormatter()
        return CustomerSearchInteractor(
            customerRepository = customerRepository,
            customerPresenter = customerPresenter
        )
    }

    @Bean
    fun customerChangeStatusBoundary(
        customerRepository: CustomerRepository,
        customerSearchBoundary: CustomerSearchBoundary
    ): CustomerChangeStatusBoundary {
        val customerPresenter = CustomerResponseFormatter()
        return CustomerChangeStatusInteractor(
            customerRepository = customerRepository,
            customerPresenter = customerPresenter,
            customerSearchBoundary = customerSearchBoundary
        )
    }

    @Bean
    fun customerUpdateBoundary(
        customerRepository: CustomerRepository,
        customerSearchBoundary: CustomerSearchBoundary
    ): CustomerUpdateBoundary {
        val customerFactory = CustomerFactoryImpl()
        val customerPresenter = CustomerResponseFormatter()
        return CustomerUpdateInteractor(
            customerRepository = customerRepository,
            customerFactory = customerFactory,
            customerPresenter = customerPresenter,
            customerSearchBoundary = customerSearchBoundary
        )
    }

    @Bean
    fun customerRegisterBoundary(
        customerRepository: CustomerRepository,
        customerSearchBoundary: CustomerSearchBoundary
    ): CustomerRegisterBoundary {
        val customerFactory = CustomerFactoryImpl()
        val customerPresenter = CustomerResponseFormatter()
        return CustomerRegisterInteractor(
            customerRepository = customerRepository,
            customerFactory = customerFactory,
            customerPresenter = customerPresenter,
            customerSearchBoundary = customerSearchBoundary
        )
    }

    @Bean
    fun customerService(
        customerRegisterBoundary: CustomerRegisterBoundary,
        customerUpdateBoundary: CustomerUpdateBoundary,
        customerChangeStatusBoundary: CustomerChangeStatusBoundary,
        customerSearchBoundary: CustomerSearchBoundary
    ): CustomerService {
        return DomainCustomerService(
            customerRegisterBoundary,
            customerUpdateBoundary,
            customerChangeStatusBoundary,
            customerSearchBoundary
        )
    }
}