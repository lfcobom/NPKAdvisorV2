var mongoose = require('mongoose');
const cropModel = require('../modelos/cropModel.js');
require('../modelos/cropModel.js');
var CropModel = mongoose.model('Crop');

//Agregar cultivos
exports.addCrop = function(req, callback){

    var objCrop = new CropModel();
    objCrop.CNombre = req.body.CNombre;
    objCrop.CArea = req.body.CArea;
    objCrop.save(function(err, retorno){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'},cultivo: retorno});
    });
};

//Actualizar los datos de un cultivo(En construcción)
exports.updateCrop = function(req, callback){
    CropModel.findById(req.params.id, function(err,cropBuscado){
    cropBuscado.CNombre = req.body.CNombre;
    cropBuscado.CArea = req.body.CArea

   cropBuscado.save(function(err, resultadoUpdate){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'},cultivo: resultadoUpdate});

    });

    });

};

//Configuración Buscar todos los cultivos
exports.finAllCrop = function(req, callback){
    CropModel.find({},function(err,cultivosBuscados){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'}, cultivos: cultivosBuscados});

    });
};

//Eliminar un cultivo usando ID
exports.deleteCrop = function(req, callback){
    CropModel.findById(req.params.id, function(err,cropBuscado){
     
        cropBuscado.remove(function(err){
            if(err) callback({estado: {codigo:2 , respuesta: err.message} });
            callback({estado:{codigo: 1, respuesta:'proceso exitoso'}});
    
        });
    
        });

};

//Buscar cultivo por ID
exports.findByIdCrop = function(req, callback){
    CropModel.findById(req.params.id,function(err,cropBuscado){
        if(err) callback({estado: {codigo:2 , respuesta: err.message} });
        callback({estado:{codigo: 1, respuesta:'proceso exitoso'}, cultivo: cropBuscado});
    });
};



