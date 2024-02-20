///@ctx(User)
/**
@entity(context = "User")
@aggregate(context = "User")
*/
type Customer {
	customerRef: int
	firstName: string
	lastName: string
	/** @identifier
	    @part
	*/
	username: string
}

type Username {
    username: string
}