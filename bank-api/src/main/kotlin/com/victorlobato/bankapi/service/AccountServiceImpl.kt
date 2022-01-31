package com.victorlobato.bankapi.service

import com.victorlobato.bankapi.model.Account
import com.victorlobato.bankapi.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.util.*

@Service
class AccountServiceImpl (private val repository: AccountRepository) : AccountService {
    override fun create(account: Account): Account {
        Assert.hasLength(account.name, "[Name] cannot be empty")
        Assert.isTrue(account.name.length >= 5, "[Name] should be 5 characters")

        Assert.hasLength(account.document, "[Document] cannot be empty")
        Assert.isTrue(account.document.length >= 11, "[Document] should be 11 characters")

        Assert.hasLength(account.phone, "[Phone] cannot be empty")
        Assert.isTrue(account.phone.length >= 11, "[Phone] should be 11 characters")
        return repository.save(account)
    }

    override fun getAll(): List<Account> {
        return repository.findAll()
    }

    override fun getById(id: Long): Optional<Account> {
        return repository.findById(id)
    }

    override fun update(id: Long, account: Account): Optional<Account> {
        val optional = getById(id)
        if(optional.isEmpty)  Optional.empty<Account>()

        return optional.map {
            val accountToUpdate = it.copy(
                name = account.name,
                document = account.document,
                phone = account.phone
            )
            repository.save(accountToUpdate)
        }
    }

    override fun delete(id: Long) {
        repository.findById(id).map{
            repository.delete(it)
        }.orElseThrow{ throw RuntimeException("Id not found $id") }
    }
}