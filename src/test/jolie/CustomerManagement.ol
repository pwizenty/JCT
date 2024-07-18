///@beginCtx(Customer)
/**
@entity(context = "Customer")
@aggregate(context = "Customer")
*/
type InteractionLogAggregateRoot {
    customerId: string
    username: string
    lastAcknowledgedInteractionId: string
    interactions: Interactions
}

/**
@entity(context = "Customer")
@aggregate(context = "Customer")
*/
type InteractionEntity {
    id: string
    date: string
    content: string
    sentByOperator: bool
}

type NotificationDto {
    customerId: string
    username: string
    count: int
}

type NotificationDtoListStructure {
    NotificationDtoList: NotificationDtoList
}

type CustomerId {
    serialVersionUID: long
    id: string
}

type InteractionAcknowledgementDto {
    lastAcknowledgedInteractionId: string
}

type PaginatedCustomerResponseDto {
    filter: string
    limit: int
    offset: int
    size: int
    customers: Customers
}

type AddressDto {
    streetAddress: string
    postalCode: string
    city: string
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

type Interactions {
    interactions*: InteractionEntity
}

type NotificationDtoList {
    NotificationDtoList*: NotificationDto
}

type Customers {
    customers*: CustomerDto
}

type MoveHistory {
    moveHistory*: AddressDto
}
///@endCtx
