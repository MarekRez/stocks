# REST API Documentation

This document provides an overview of the available REST endpoints in the Stock Trading API.

## Table of Contents
- [Stocks API](#stocks-api)
- [Transactions API](#transactions-api)
- [Users API](#users-api)

## Stocks API

### GET /stocks
**Description**: Retrieves a list of all stocks.  
**Authorization**: No specific role required  
**Response Codes**:
- `200 OK`: Successfully retrieved the list of stocks
- `204 No Content`: No stocks available

**Response Body**: Array of StockDto objects

### GET /stocks/{symbol}
**Description**: Retrieves a specific stock by its symbol.  
**Authorization**: No specific role required  
**Parameters**:
- `symbol` (path): The stock symbol (must match a valid StockSymbol enum value)

**Response Codes**:
- `200 OK`: Successfully retrieved the stock
- `400 Bad Request`: Invalid stock symbol
- `404 Not Found`: Stock with the given symbol not found

**Response Body**: StockDto object

### POST /stocks
**Description**: Creates a new stock.  
**Authorization**: Requires `ROLE_ADMIN`  
**Request Body**: StockDto object  
**Response Codes**:
- `201 Created`: Successfully created the stock

**Response Body**: StockDto object of the created stock

## Transactions API

### GET /transactions
**Description**: Retrieves all transactions for the current user.  
**Authorization**: Requires `ROLE_ADMIN`  
**Response Codes**:
- `200 OK`: Successfully retrieved the transactions (empty list if no user found)

**Response Body**: Array of TransactionDto objects

## Users API

### POST /users
**Description**: Creates a new user.  
**Authorization**: No specific role required  
**Request Body**: CreateUserRequestDto object  
**Response Codes**:
- `201 Created`: Successfully created the user

**Response Body**: UserDto object of the created user  
**Headers**: `Location` header with the URI of the created user

### GET /users/{id}
**Description**: Retrieves a user by ID.  
**Authorization**: No specific role required  
**Parameters**:
- `id` (path): The user ID

**Response Codes**:
- `200 OK`: Successfully retrieved the user
- `404 Not Found`: User with the given ID not found

**Response Body**: UserDto object

## Data Transfer Objects

### StockDto
Contains information about a stock.

### TransactionDto
Contains information about a transaction.

### UserDto
Contains information about a user.

### CreateUserRequestDto
Contains information needed to create a new user.