/// @Entity
type EntityOK: void {
	a[1,*]: string {
		b?: string
			| int { c*: bool }
	}
	/// @Identifier
	d: int
}

/// @Entity
type EntityNot: void {
	a[1,*]: string {
		b?: string
			| int { c*: bool }
	}
	d: int
}