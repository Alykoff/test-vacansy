/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define(['ApiErrorAbstract', 'Utils'], function(ApiErrorAbstract, Utils) {
  var ApiError = function() {
    this.getIdError = function() {
      console.log('err');
    };
    this.checkRequestStateError = function() {
      console.log('err');
    };
    this.getSearchResultError = function() {
      console.log('err');
    };
  };
  new Utils().extend(ApiError, ApiErrorAbstract);

  return ApiError;
});