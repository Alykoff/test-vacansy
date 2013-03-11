/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define([], function() {
  var ApiManagerAbstract = function() {};
  ApiManagerAbstract.prototype.data = null;
  ApiManagerAbstract.prototype.start = function() {};
  ApiManagerAbstract.prototype.api = function() {};
  ApiManagerAbstract.prototype.error = function() {};

  return ApiManagerAbstract;
});