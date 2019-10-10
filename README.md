# ISOBar Wallet
Important Info:
* Uses Java 11, H2 In Memory Database and Maven:

* Standard Spring Boot 2 Application with Maven, So run 
    class com.github.ankurpathak.isobarwallet.IsobarWalletApplication with main
    method.

* Junit Test cases are provided to test all major functionality, So run 
    class com.github.ankurpathak.isobarwallet.AccountControllerTests with
    test cases.

* Rest Application containing following endpoints with base path /api:
```
        POST /account - to create new wallet account
        GET /account/{id} - to fetch wallet account details including account balance
        PUT /account/{id} - to update wallet account details
        POST /account/{id}/funds/{amount} - to add (recharge) funds into specified wallet account
        DELETE /account/{id}/funds/{amount} - to use (consume) funds from specified wallet account with optional note
        GET /account/{id}/statement - should return last 30 days account statement
        GET /account/{id}/statement/{fromDate}/{toDate} - should return account statement for given timeframe
```