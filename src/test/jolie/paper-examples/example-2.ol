///@beginCtx(User)
/**
@entity(context = "User")
@aggregate(context = "User")
*/
type Customer {
    customerRef: int
    firstName: string
    lastName: string
    /** @IDENTIFIER
    @PART */
    username: Username
}

///@VALUE_OBJECT(context = "User")
type Username {
    username: string
}
