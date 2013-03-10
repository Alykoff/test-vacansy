/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define([], function() {
  var ApiInteractionAbstract = function() {};
  ApiInteractionAbstract.prototype.getId = function(error, callback) {};
  ApiInteractionAbstract.prototype.checkRequestState = function(error, callback) {};
  ApiInteractionAbstract.prototype.getSearchResult = function(error, callback) {};
  ApiInteractionAbstract.prototype.isComplete = function() {};
  ApiInteractionAbstract.prototype.END_REQUEST_STATE = null;
  ApiInteractionAbstract.prototype.TIME_REFRESH_RESULT = null;
  ApiInteractionAbstract.prototype.TIME_BETWEEN_CHECKING_STATE = null;
  ApiInteractionAbstract.prototype.idRequest = null;
  ApiInteractionAbstract.prototype.state = null;
  ApiInteractionAbstract.prototype.urls = {
      URL_NEW_REQUEST: null,
      URL_REQUEST_STATE: null,
      URL_FARES: null    
  };

  return ApiInteractionAbstract;
});