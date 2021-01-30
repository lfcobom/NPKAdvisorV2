var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var cropSchema = new Schema({
    CNombre: String,
    CArea: Number,
    Create_at: {type: Date, required:true, default:Date.now},
});

module.exports = mongoose.model('Crop',cropSchema);