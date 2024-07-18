///@beginCtx(Student)
/**
@entity(context = "Student")
@aggregate(context = "Student")
*/
type Student {
    id: long
    name: string
    exams: Exams
}

type Exams {
    f*: string
}
///@endCtx
