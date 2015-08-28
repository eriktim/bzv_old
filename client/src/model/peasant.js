import Base from 'model/base.js';

var mId = Symbol();

class Peasant extends Base {
  constructor(id) {
    super('peasant', id);
    this[mId] = id;
  }
}

export default Peasant;
