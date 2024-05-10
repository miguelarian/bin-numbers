# BIN Numbers

A classifier of payment cards BIN numbers (**training exercise**).

[Source](https://leetcode.com/discuss/interview-question/2012950/Build-a-cache-for-card-types-or-Online-Assessment-Question)

## What is a BIN number

*"Payment card numbers are composed of 8 to 19 digits. The leading six or eight digits are the issuer identification number (IIN) sometimes referred to as the **bank identification number (BIN)**"* - [Wikipedia](https://en.wikipedia.org/wiki/Payment_card_number).

## Problem description

Write a program that that given a list of BIN ranges and card issuers, and an input card number, returns the correspoding card issuer. 

### Example

Input BIN range object:
```java
ranges = [["4444 4444 11", "4444 4444 44", "Visa credit"], ["4500 0000 55", "4999 9999 00", "Visa debit"], ["4999 9999 99", "5555 0000 00", "Master credit"], ["6666 4444 11", "7777 0000 00", "Amex"]];

cardNumber = "4733 6109 7901 2139";
```

Output: 

```java
"Visa debit"
```