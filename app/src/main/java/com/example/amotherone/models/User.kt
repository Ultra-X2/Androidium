package com.example.amotherone.models

class User{
    var fname:String=""
    var lname:String=""
var email:String=""
var pass: String=""
var userid:String=""
    constructor(fname:String ,lname:String ,email:String,pass:String, userid:String) {
        this.fname = fname
        this.userid=userid
        this.pass=pass
        this.email=email
        this.lname=lname
    }
}
