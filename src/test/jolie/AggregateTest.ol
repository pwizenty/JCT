
/**
@aggregate
@entity
*/
type PSBEntity {
    ///@identifier
    bookingID: long
    ///@part
    timeSlot: TimeSlot
    ///@part
    priceInEuro: double
}

type TimeSlot: string

///@aggregate
type PSBNoEntity {
    ///@identifier
    bookingID: long
    ///@part
    timeSlot: TimeSlot
    priceInEuro: double
}