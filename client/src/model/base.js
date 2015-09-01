import xr from '../../lib/xr/xr.js';

var host = 'http://localhost:8080/';
var mType = Symbol();
var mId = Symbol();

class Base {
  constructor(type, id) {
    this[mType] = type;
    this[mId] = id;
    this.load();
  }

  load() {
    xr.get(host + this[mType] + '/' + this[mId])
      .then(function(data) {
        console.log(data);
      });
  }
}

export default Base;
