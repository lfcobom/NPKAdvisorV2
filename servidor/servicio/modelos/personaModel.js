var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var personaSchema = new Schema({
    Nombre: String,
    Username: String,
    Password: String,
    PasswordC: String,
    create_at: {type: Date, required:true, default:Date.now},
    //Sexo: {type:String, enum: ['Masculino','Femenino']},
    //IsProfesional: Boolean
});

module.exports = mongoose.model('Persona',personaSchema);