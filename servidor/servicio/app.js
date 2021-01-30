var express = require("express");
var bodyParser = require("body-parser");
var methodOverride = require("method-override");
var moongose = require("mongoose");
var crypto = require('crypto')
var app = express();

//password utilities
var genRandomString = function(lenght){
    return crypto.randomBytes(Math.ceil(lenght/2)).toString('hex').slice(0,lenght);
}

var sha512 = function(password,salt){
    var hash = crypto.createHmac('sha512',salt);
    hash.update(password);
    var value = hash.digest('hex');
    return{salt:salt,passwordHash:value};
}
function saltHashPassword(userPassword){
    var salt = genRandomString(16);
    var passwordData = sha512(userPassword,salt)
    return passwordData;
}
function checkHashPassword(userPassword,salt)
{
    var passwordData = sha512(userPassword,salt)
    return passwordData;
}

//configuración
app.use(bodyParser.json());
app.use(methodOverride());
app.use(bodyParser.urlencoded({ extended: true })); //facilita la lectura de los parámetros de una petición

//definir el puerto por el cual vamos a escuchar
app.listen(3000,function(){
    console.log("nuestro servidor escucha por el puerto 3000");
});

moongose.connect('mongodb://localhost/NPKAdvisor', function(err,res){
    
    if(err){
        console.log('ERROR: connecting to Database' + err);
    }else{
        console.log('Conectado a MongoDB');
    }
})
const cropRouters = require('./routes/cultivo')
const personaRouters = require('./routes/persona')
app.use(personaRouters)
app.use(cropRouters)







