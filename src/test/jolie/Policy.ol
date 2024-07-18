///@beginCtx(Policy)
type InsuranceType {
    name: string
}

type MoneyAmount {
    amount: double
    currency: Currency
}

/**
@entity(context = "Policy")
@aggregate(context = "Policy")
*/
type InsuranceOptionsEntity {
    id: long
    startDate: string
    insuranceType: InsuranceType
    deductible: MoneyAmount
}

/**
@entity(context = "Policy")
@aggregate(context = "Policy")
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
@entity(context = "Policy")
@aggregate(context = "Policy")
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

type PolicyId {
    serialVersionUID: long
    id: string
}

type PolicyPeriod {
    startDate: string
    endDate: string
}

type PolicyType {
    name: string
}

type InsuringAgreementEntity {
    id: long
    agreementItems: AgreementItems
}

/**
@entity(context = "Policy")
@aggregate(context = "Policy")
*/
type PolicyAggregateRoot {
    id: PolicyId
    customerId: CustomerId
    FIELD_CREATION_DATE: string
    creationDate: string
    policyPeriod: PolicyPeriod
    policyType: PolicyType
    deductible: MoneyAmount
    policyLimit: MoneyAmount
    insurancePremium: MoneyAmount
    insuringAgreement: InsuringAgreementEntity
}

/**
@entity(context = "Policy")
@aggregate(context = "Policy")
*/
type InsuringAgreementItem {
    id: long
    title: string
    description: string
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

type RiskFactorResponseDto {
    riskFactor: int
}

type RiskFactorRequestDto {
    birthday: string
    postalCode: string
}

type PaginatedCustomerResponseDto {
    filter: string
    limit: int
    offset: int
    size: int
    customers: Customers
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

type CustomerDtoListStructure {
    CustomerDtoList: CustomerDtoList
}

type CustomerIdDto {
    id: string
}

type PolicyPeriodDto {
    startDate: string
    endDate: string
}

type InsuringAgreementDto {
    agreementItems: AgreementItems
}

type PolicyDto {
    policyId: string
    customer: Customer
    creationDate: string
    policyPeriod: PolicyPeriodDto
    policyType: string
    deductible: MoneyAmountDto
    policyLimit: MoneyAmountDto
    insurancePremium: MoneyAmountDto
    insuringAgreement: InsuringAgreementDto
    expandable: Expandable
}

type PolicyDtoListStructure {
    PolicyDtoList: PolicyDtoList
}

type CreatePolicyRequestDto {
    customerId: string
    policyPeriod: PolicyPeriodDto
    policyType: string
    deductible: MoneyAmountDto
    policyLimit: MoneyAmountDto
    insurancePremium: MoneyAmountDto
    insuringAgreement: InsuringAgreementDto
}

type PolicyAggregateRootListStructure {
    PolicyAggregateRootList: PolicyAggregateRootList
}

type PaginatedPolicyResponseDto {
    limit: int
    offset: int
    size: int
    policies: Policies
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

type AgreementItems {
    agreementItems*: InsuringAgreementItem
}

type InsuranceQuoteRequestDtoList {
    InsuranceQuoteRequestDtoList*: InsuranceQuoteRequestDto
}

type Customers {
    customers*: CustomerDto
}

type MoveHistory {
    moveHistory*: AddressDto
}

type CustomerDtoList {
    CustomerDtoList*: CustomerDto
}

type Customer {
    customer: undefined
}

type Expandable {
    expandable: undefined
}

type PolicyDtoList {
    PolicyDtoList*: PolicyDto
}

type PolicyAggregateRootList {
    PolicyAggregateRootList*: PolicyAggregateRoot
}

type Policies {
    policies*: PolicyDto
}
///@endCtx
