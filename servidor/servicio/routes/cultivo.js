const express = require('express')
const  controllerc = require('../controladores/cropControlador')
const routerc = express.Router()
const pathc = 'cultivo'
const pathc2 = 'cultivo/:id'


//Agregar cultivos
routerc.post(`/${pathc}`,function(req,res){
    controllerc.addCrop(req,function(data){
        res.send(data);
    });

});

//Buscar todos los usuarios
routerc.get(`/${pathc}`, function(req,res){
    controllerc.finAllCrop(req, function(data){
        res.send(data);
    });
});

//Buscar un usuario :)
routerc.get(`/${pathc2}`, function(req,res){
    controllerc.findByIdCrop(req, function(data){
        res.send(data);
    });
});


//Actualizar usuarios
routerc.put(`/${pathc2}`, function(req,res){
    controllerc.updateCrop(req, function(data){
        res.send(data);
    });
});

// Eliminar usuarios
routerc.delete(`/${pathc2}`, function(req,res){
    controllerc.deleteCrop(req, function(data){
        res.send(data);
    });
});



module.exports = routerc