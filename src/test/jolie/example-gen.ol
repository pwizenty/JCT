///@ctx(User)
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

type Username {
    username: string
}
