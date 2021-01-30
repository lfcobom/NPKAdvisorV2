const express = require('express')
const  controller = require('../controladores/PersonaControlador')
const router = express.Router()
const path = 'persona'
const path2 = 'persona/:id'
const path3 = 'persona/login'



//Buscar todos los usuarios
router.get(`/${path}`, function(req,res){
    controller.finAllPersona(req, function(data){
        res.send(data);
    });
});

//Buscar un usuario
router.get(`/${path2}`, function(req,res){
    controller.findByIdPersona(req, function(data){
        res.send(data);
    });
});

//Agregar usuarios
router.post(`/${path}`, function(req,res){
    controller.addPersona(req,function(data) {
     res.send(data);
    });
});

//Actualizar usuarios
router.put(`/${path2}`, function(req,res){
    controller.updatePersona(req, function(data){
        res.send(data);
    });
});

// Eliminar usuarios
router.delete(`/${path2}`, function(req,res){
    controller.deletePersona(req, function(data){
        res.send(data);
    });
});

//Configuración Inicio de Sesión
router.post(`/${path3}`, function(req,res){
    controller.loginl(req,function(data) {
     res.send(data);
    });
});

module.exports = router