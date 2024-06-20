package br.com.fiap.techchallenge.kongfood.customer.framework.drivers.db.repository

import br.com.fiap.techchallenge.kongfood.customer.framework.drivers.db.orm.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CustomerEntityRepository: JpaRepository<CustomerEntity, String>{
    fun findByCpf(cpf: String): Optional<CustomerEntity>
    fun findByEmail(email: String): Optional<CustomerEntity>
}