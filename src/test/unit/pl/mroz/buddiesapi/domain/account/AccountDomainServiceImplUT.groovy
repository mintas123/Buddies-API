package pl.mroz.buddiesapi.domain.account

import pl.mroz.buddiesapi.infrastructure.database.account.AccountRepositoryInMemory
import spock.lang.Specification
import spock.lang.Subject

class AccountDomainServiceImplUT extends Specification {

    def repository = new AccountRepositoryInMemory()

    @Subject
    def provider = AccountDomainServiceFactory.accountDomainService(repository)

    def 'should return list of all accounts'() {
        given:
            def accounts = [
                    new Account(UUID.randomUUID(), 'test@email.com', PasswordHasher.hashPassword(PasswordHasher.generatePassword()), 'John', 'Deer', null),
                    new Account(UUID.randomUUID(), 'mail@gmail.com', PasswordHasher.hashPassword(PasswordHasher.generatePassword()), 'John', 'Kowalski', null),
            ]
            repository.withAccounts(accounts)
        when:
            def result = provider.getAllAccounts()
        then:
            result.size() == 2
            result.get(0).email == 'test@email.com'
            result.get(1).lastName == 'Kowalski'
    }

    def 'should save an account'() {
        given:
            def toStore = new Account(UUID.randomUUID(), 'test@email.com', PasswordHasher.hashPassword(PasswordHasher.generatePassword()), 'John', 'Deer', null)
        when:
            provider.createAccount(toStore)
        then:
            repository.accounts.size() == 1
            with(repository.accounts.first()) {
                it.email == 'test@email.com'
                it.lastName == 'Deer'
            }
    }
}
