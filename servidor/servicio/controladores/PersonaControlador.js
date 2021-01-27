var mongoose = require('mongoose');
const personaModel = require('../modelos/personaModel.js');
require('../modelos/personaModel.js');
//require('../modelos/publicacionModel.js');
var PersonaModel = mongoose.model('Persona');
//var PublicacionModel = mongoose.model('Publicacion');
var User = require('../modelos/personaModel');
const { count } = require('../modelos/personaModel.js');

//Agregar un nuevo Usuario
exports.addPersona = function(req, callback){

    var objPersona = new PersonaModel();
    objPersona.Nombre = req.body.Nombre;
    objPersona.Username = req.body.Username;
    objPersona.Password = req.body.Password;
    objPersona.PasswordC = req.body.PasswordC;


    objPersona.save(function(err, retorno){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'},persona: retorno});
    });
};

//Actualizar los datos de un usuario(En construcción)
exports.updatePersona = function(req, callback){
    PersonaModel.findById(req.params.id, function(err,personaBuscada){
    personaBuscada.Nombre = req.body.Nombre;
    personaBuscada.Username = req.body.Username

   personaBuscada.save(function(err, resultadoUpdate){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'},persona: resultadoUpdate});

    });

    });

};

//Eliminar un usuario usando el ID
exports.deletePersona = function(req, callback){
    PersonaModel.findById(req.params.id, function(err,personaBuscada){
     
        personaBuscada.remove(function(err){
            if(err) callback({estado: {codigo:2 , respuesta: err.message} });
            callback({estado:{codigo: 1, respuesta:'proceso exitoso'}});
    
        });
    
        });

};

//Buscar Usuario por ID
exports.findByIdPersona = function(req, callback){
    PersonaModel.findById(req.params.id,function(err,personaBuscada){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'}, persona: personaBuscada});
    });
};

//Configuración Inicio de Sesión
exports.loginl = function(req,callback){
    PersonaModel.find({'Username': req.body.Username, 'Password': req.body.Password}, function(err,retorno){
        if(err)callback({estado: {codigo:2 , respuesta:'Incorrect'}});
        if(retorno.length == 1){
            //console.log("obvio funciona");
            callback({estado:{codigo: 1, respuesta:'login succesful'}, persona: retorno.length},);
        }else{
            callback({estado: {codigo:2 , respuesta:'Verifique sus credenciales'}});
        }
    
    });
}
//Configuración Buscar todos los usuarios
exports.finAllPersona = function(req, callback){
    PersonaModel.find({},function(err,personasBuscadas){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'}, persona: personasBuscadas});

    });
};
