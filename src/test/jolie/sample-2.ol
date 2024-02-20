/**
@beginCtx(Common)
@VALUE_OBJECT
*/
type Money {
    amount: double
}
type add_type {
    delta: Money
    self?: Money
}
type isGreaterThanOrEqual_type {
    other: Money
    self?: Money
}
type multiply_type {
    x: int
    self?: Money
}
interface Money_interface {
    RequestResponse:
        ///@SIDE_EFFECT_FREE
        add(add_type)(Money),
        ///@SIDE_EFFECT_FREE
        isGreaterThanOrEqual(isGreaterThanOrEqual_type)(bool),
        ///@SIDE_EFFECT_FREE
        multiply(multiply_type)(Money)
}

///@VALUE_OBJECT
type PersonName {
    firstName: string
    lastName: string
}

///@VALUE_OBJECT
type Address {
    street1: string
    street2: string
    city: string
    state: string
    zip: string
}