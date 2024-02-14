/// @interface comment
interface MyInterface {
	oneWay: ow_1( string ), ow_2( MyType )
	requestResponse: rr_1( string )( void ), rr_2( MyOtherType )( string )
}

type TimeSlot: string

/**
@aggregate
@entity
*/
type PSB {
    ///@identifier
    bookingID: long
    ///@part
    timeSlot: TimeSlot
    priceInEuro: double
}

/// @Entity
type MyType: void {
	a[1,*]: string {
		b?: string
			| int { c*: bool }
	}
	/// @Identifier
	d: int
}

type MyOtherType: void {
	/// @type node comment
	e[2,15]: int {
        /// @type leaf comment
		f: int | string
	}
}