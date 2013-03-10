/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define([], function() {
  var Utils = function() {
    this.extend = function(Child, Parent) {
      var F = function() {};
      F.prototype = Parent.prototype;
      Child.prototype = new F();
      Child.prototype.constructor = Child;
      Child.superclass = Parent.prototype;
    };
  };
  return Utils;
});