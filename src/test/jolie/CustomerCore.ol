///@beginCtx(Customer)
/**
@entity(context = "Customer")
@aggregate(context = "Customer")
*/
type Address {
    id: long
    streetAddress: string
    postalCode: string
    city: string
}

type CustomerId {
    serialVersionUID: long
    id: string
}

type CustomerProfileEntity {
    serialVersionUID: long
    firstname: string
    lastname: string
    birthday: string
    currentAddress: Address
    email: string
    phoneNumber: string
    moveHistory: MoveHistory
}

/**
@entity(context = "Customer")
@aggregate(context = "Customer")
*/
type CustomerAggregateRoot {
    id: CustomerId
    customerProfile: CustomerProfileEntity
}

type CustomerResponseDto {
    customerId: string
    firstname: string
    lastname: string
    birthday: string
    streetAddress: string
    postalCode: string
    city: string
    email: string
    phoneNumber: string
    moveHistory: MoveHistory
}

type PaginatedCustomerResponseDto {
    filter: string
    limit: int
    offset: int
    size: int
    customers: Customers
}

type CustomerResponseDtoListStructure {
    CustomerResponseDtoList: CustomerResponseDtoList
}

type CustomersResponseDto {
    customers: Customers
}

type CustomerProfileUpdateRequestDto {
    firstname: string
    lastname: string
    birthday: string
    streetAddress: string
    postalCode: string
    city: string
    email: string
    phoneNumber: string
}

type AddressDto {
    streetAddress: string
    postalCode: string
    city: string
}

type CitiesResponseDto {
    cities: Cities
}

type MoveHistory {
    moveHistory*: Address
}

type Customers {
    customers*: CustomerResponseDto
}

type CustomerResponseDtoList {
    CustomerResponseDtoList*: CustomerResponseDto
}

type Cities {
    f*: string
}
///@endCtx
