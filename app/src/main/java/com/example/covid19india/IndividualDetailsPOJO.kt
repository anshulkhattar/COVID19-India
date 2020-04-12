package com.example.covid19india

class IndividualDetailsPOJO {
    var govtId:String=""
    var dateDiagnosed:String=""
    var gender:String=""
    var detectedCity:String=""
    var detectedState:String=""
    var status:String=""
    var coordinates:String=""

    constructor()

    constructor(
        govtId:String,
        dateDiagnosed:String,
        gender:String,
        detectedCity:String,
        detectedState:String,
        status:String,
        coordinates:String

    ) : super() {
        this.govtId=govtId
        this.dateDiagnosed=dateDiagnosed
        this.gender=gender
        this.detectedCity=detectedCity
        this.detectedState=detectedState
        this.status=status
        this.coordinates=coordinates
    }
}