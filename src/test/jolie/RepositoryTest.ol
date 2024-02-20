///@REPOSITORY
type UserLoginRepositoryOK {
    login: UserLoginEntity
    id: long
}

/**
@repository
@entity
*/
type UserLoginRepository2Stereotypes {
    login: UserLoginEntity
    id: long
}

///@ENTITY
type UserLoginEntity {
    ///@IDENTIFIER
    id: long
    authorities: string
    email: string
    password: string
    customerId: CustomerId
}

type CustomerId {
    id: string
}