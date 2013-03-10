/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define([], function() {
  var ApiErrorAbstract = function() {};
  ApiErrorAbstract.prototype.getIdError = function() {};
  ApiErrorAbstract.prototype.checkRequestStateError = function() {};
  ApiErrorAbstract.prototype.getSearchResultError = function() {};

  return ApiErrorAbstract;
});