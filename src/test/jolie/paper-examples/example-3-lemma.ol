///@beginCtx(User)
/**
@entity(context = "User")
@aggregate(context = "User")
*/
type Customer {
    ///@IDENTIFIER
    customerRef: int
    firstName: string
    lastName: string
    ///@PART
    username: Username
}

///@VALUE_OBJECT(context = "User")
type Username {
    username: string
}