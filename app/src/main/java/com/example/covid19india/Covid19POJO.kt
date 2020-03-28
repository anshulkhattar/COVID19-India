package com.example.covid19india

class Covid19POJO {
    var sno:String=""
    var date:String=""
    var state:String=""
    var confIndian:String=""
    var confForeign:String=""
    var cured:String=""
    var deaths:String=""

    constructor()

    constructor(
        sno:String,
        date:String,
        state:String,
        confIndian:String,
        confForeign: String,
        cured:String,
        deaths:String

    ) : super() {
        this.sno=sno
        this.date=date
        this.state=state
        this.confIndian=confIndian
        this.confForeign=confForeign
        this.cured=cured
        this.deaths=deaths
    }
}