package buddiesapi.infrastructure.database.account

import buddiesapi.domain.account.Account
import buddiesapi.domain.account.AccountRepository


class AccountRepositoryInMemory implements AccountRepository {

    def accounts = [] as List<Account>

    def withAccounts(List<Account> entities) {
        accounts = entities
    }

    @Override
    List<Account> findAll() {
        return accounts
    }

    @Override
    Account save(Account account) {
        if (accounts.contains(account)) {
            throw new IllegalStateException("Cannot save, account with " + account.getAccountId() + " already exists");
        }
        accounts += account
        return account
    }

    @Override
    void delete(Account account) {
        accounts.remove(account)
    }

    @Override
    Account getById(UUID accountUUID) {
        return accounts.find {
            it.accountId == accountUUID
        }
    }
}
