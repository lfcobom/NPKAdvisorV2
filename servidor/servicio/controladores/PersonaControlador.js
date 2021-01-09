var mongoose = require('mongoose');
require('../modelos/personaModel.js');
//require('../modelos/publicacionModel.js');
var PersonaModel = mongoose.model('Persona');
//var PublicacionModel = mongoose.model('Publicacion');

exports.addPersona = function(req, callback){

    var objPersona = new PersonaModel();
    objPersona.Nombre = req.body.Nombre;
    objPersona.Username = req.body.Username;
    objPersona.Password = req.body.Password;
    objPersona.PasswordC = req.body.PasswordC
   

    objPersona.save(function(err, retorno){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'},persona: retorno});

    });
};

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

exports.deletePersona = function(req, callback){
    PersonaModel.findById(req.params.id, function(err,personaBuscada){
     
        personaBuscada.remove(function(err){
            if(err) callback({estado: {codigo:2 , respuesta: err.message} });
            callback({estado:{codigo: 1, respuesta:'proceso exitoso'}});
    
        });
    
        });

};

exports.findByIdPersona = function(req, callback){
    PersonaModel.findById(req.params.id,function(err,personaBuscada){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'}, persona: personaBuscada});

    });

};

exports.finAllPersona = function(req, callback){
    PersonaModel.find({},function(err,personasBuscadas){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'}, persona: personasBuscadas});

    });
};