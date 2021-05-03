# bank

## About the api

An Api for a bank service, able to transfer money, deposit, create, update, cancel and get an account and get the statement of it.

### Features
This API provides HTTP endpoint's for the following:
- Create an account: POST /v1/account
- Cancel an account: DELETE /v1/account
- Get an account: GET /v1/account/{identifier}
- Update an account: PATCH /v1/account/{identifier}
- Get amount of an account: GET /v1/account/{identifier}/amount
- Deposit amount in an account: v1/operation/deposit/{identifier}
- Transfer amount between accounts: v1/operation/transfer/{identifier}
- List all statements of an account: v1/statement/{identifier}

# Details
POST /v1/account

Body

```
{ 
  "identifier": "12345",
  "name": "José Vieira de Lima",
  "description": "A description for this account",
  "status": [ACTIVE, INACTIVE]
}
```

POST /v1/operation/deposit/{identifier}

Body

```
  "transactionAmount": 35.0
```

POST /v1/operation/transfer/{identifier}

Body

```
  "transactionAmount": 35.0,
  "targetIdentifier": "12345",
  "description": "A description for the transfer"
```
