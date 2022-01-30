package com.victorlobato.bankapi.repository

import com.victorlobato.bankapi.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long>{
}