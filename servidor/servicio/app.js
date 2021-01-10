var express = require("express");
var bodyParser = require("body-parser");
var methodOverride = require("method-override");
var moongose = require("mongoose");
var app = express();

//configuración
app.use(bodyParser.json());
app.use(methodOverride());

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

//definir el enrutamiento de las solicitudes

var controladorPersona = require('./controladores/PersonaControlador')
var router = express.Router();
router.get('/', function(req,res){
    res.send("hola mundo de el servicio node js");
})

//agregar personas
router.post('/API/persona/AddPersona', function(req,res){
    controladorPersona.addPersona(req, function(data){
        res.send(data);
    });
});

//actualizar personas
router.put('/API/persona/UpdatePersona/:id', function(req,res){
    controladorPersona.updatePersona(req, function(data){
        res.send(data);
    });
});

//eliminar persona http://localhost:3000/API/persona/finAllPersona/2333
router.delete('/API/persona/DeletePersona/:id', function(req,res){
    controladorPersona.deletePersona(req, function(data){
        res.send(data);
    });
});

// buscar todas las personas 
router.get('/API/persona/finAllPersona', function(req,res){
    controladorPersona.finAllPersona(req, function(data){
        res.send(data);
    });
});

//buscar una persona
router.get('/API/persona/findPersona/:id', function(req,res){
    controladorPersona.findByIdPersona(req, function(data){
        res.send(data);
    });
});


app.use(router);

