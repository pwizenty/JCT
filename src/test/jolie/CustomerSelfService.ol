///@beginCtx(Customer)
type InsuranceType {
    name: string
}

type MoneyAmount {
    amount: double
    currency: Currency
}

/**
@entity(context = "Customer")
@aggregate(context = "Customer")
*/
type InsuranceOptionsEntity {
    id: long
    startDate: string
    insuranceType: InsuranceType
    deductible: MoneyAmount
}

/**
@entity(context = "Customer")
@aggregate(context = "Customer")
*/
type RequestStatusChange {
    id: long
    date: string
    status: RequestStatus
}

type CustomerId {
    serialVersionUID: long
    id: string
}

type Address {
    id: long
    streetAddress: string
    postalCode: string
    city: string
}

type CustomerInfoEntity {
    id: long
    customerId: CustomerId
    firstname: string
    lastname: string
    contactAddress: Address
    billingAddress: Address
}

type InsuranceQuoteEntity {
    id: long
    expirationDate: string
    insurancePremium: MoneyAmount
    policyLimit: MoneyAmount
}

/**
@entity(context = "Customer")
@aggregate(context = "Customer")
*/
type InsuranceQuoteRequestAggregateRoot {
    id: long
    date: string
    statusHistory: StatusHistory
    customerInfo: CustomerInfoEntity
    insuranceOptions: InsuranceOptionsEntity
    insuranceQuote: InsuranceQuoteEntity
    policyId: string
}

/**
@entity(context = "Customer")
@aggregate(context = "Customer")
*/
type UserLoginEntity {
    id: long
    authorities: string
    email: string
    password: string
    customerId: CustomerId
}

type UserResponseDto {
    email: string
    customerId: string
}

type AuthenticationResponseDto {
    email: string
    token: string
}

type AuthenticationRequestDto {
    email: string
    password: string
}

type SignupRequestDto {
    email: string
    password: string
}

type AddressDto {
    streetAddress: string
    postalCode: string
    city: string
}

type CustomerInfoDto {
    customerId: string
    firstname: string
    lastname: string
    contactAddress: AddressDto
    billingAddress: AddressDto
}

type MoneyAmountDto {
    amount: double
    currency: string
}

type InsuranceOptionsDto {
    startDate: string
    insuranceType: string
    deductible: MoneyAmountDto
}

type InsuranceQuoteDto {
    expirationDate: string
    insurancePremium: MoneyAmountDto
    policyLimit: MoneyAmountDto
}

type InsuranceQuoteRequestDto {
    id: long
    date: string
    statusHistory: StatusHistory
    customerInfo: CustomerInfoDto
    insuranceOptions: InsuranceOptionsDto
    insuranceQuote: InsuranceQuoteDto
    policyId: string
}

type InsuranceQuoteRequestDtoListStructure {
    InsuranceQuoteRequestDtoList: InsuranceQuoteRequestDtoList
}

type InsuranceQuoteResponseDto {
    status: string
    expirationDate: string
    insurancePremium: MoneyAmountDto
    policyLimit: MoneyAmountDto
}

type CustomerProfileDto {
    firstname: string
    lastname: string
    birthday: string
    currentAddress: AddressDto
    email: string
    phoneNumber: string
    moveHistory: MoveHistory
}

type CustomerDto {
    customerId: string
    customerProfile: CustomerProfileDto
}

type CustomerRegistrationRequestDto {
    firstname: string
    lastname: string
    birthday: string
    city: string
    streetAddress: string
    postalCode: string
    phoneNumber: string
}

type CitiesResponseDto {
    cities: Cities
}

type RequestStatus {
    literal: string(enum(["REQUEST_SUBMITTED", "REQUEST_REJECTED", "QUOTE_RECEIVED", "QUOTE_ACCEPTED", "QUOTE_REJECTED", "QUOTE_EXPIRED", "POLICY_CREATED"]))
}

type Currency {
    currency: undefined
}

type StatusHistory {
    statusHistory*: RequestStatusChange
}

type InsuranceQuoteRequestDtoList {
    InsuranceQuoteRequestDtoList*: InsuranceQuoteRequestDto
}

type MoveHistory {
    moveHistory*: AddressDto
}

type Cities {
    f*: string
}
///@endCtx
