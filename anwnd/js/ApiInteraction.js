/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define(['ApiInteractionAbstract', 'Utils', 'jquery'], 
  function(ApiInteractionAbstract, Utils, $) {
  var ApiInteraction = function() {
    var MINUTE_UNDER_REFRESH = 1; // N
    this.TIME_BETWEEN_CHECKING_STATE = 1000;
    this.END_REQUEST_STATE = '100';
    this.TIME_REFRESH_RESULT = MINUTE_UNDER_REFRESH * 60 * 1000;
    this.state = '';
    this.dataBuffer = null;
    this.urls = {
      URL_NEW_REQUEST: 'http://api.anywayanyday.com/api/NewRequest/?Route=2406MOWLON&AD=1&CN=0&CS=E&Partner=testapic&_Serialize=JSON',
      URL_REQUEST_STATE: 'http://api.anywayanyday.com/api/RequestState/?_Serialize=JSON&R=',
      URL_FARES: 'http://api.anywayanyday.com/api/Fares/?V=Matrix&VB=true&L=ru&_Serialize=JSON&R='
    };

    this.isComplete = function() {
      return this.state === this.END_REQUEST_STATE;
    };
    this.getId = function(error, callback) {
      var _this = this;
      $.ajax({
        url: _this.urls.URL_NEW_REQUEST,
        type: 'GET',
        dataType:  'jsonp',
        success: function(data) {
          if (data === undefined || 
              data.Id === undefined || 
              data.Error === undefined ||
              data.Error !== null) {
            error();
            return;
          } 
          _this.idRequest = data.Id;
          callback.apply();
        },
        error: function() {
          error();
        }
      });
    };
    this.checkRequestState = function(error, callback) {
      var _this = this;
      if (_this.isComplete()) return;
      
      $.ajax({
        url: _this.urls.URL_REQUEST_STATE + _this.idRequest,
        type: 'GET',
        dataType: 'jsonp',
        success: function(data) {
          if (data === undefined ||
              data.Completed === undefined ||
              data.Error === undefined ||
              data.Error !== null) {
            error();
            return;
          }
          _this.state = data.Completed;
          callback.apply();
          setTimeout(function() {
              _this.checkRequestState(error, callback);
            }, _this.TIME_BETWEEN_CHECKING_STATE);
        },
        error: function() {
          error();
        }
      });
    };
    this.getSearchResult = function(error, callback) {
      var _this = this;
      if (!_this.isComplete) return;    

      $.ajax({
        url: _this.urls.URL_FARES + _this.idRequest,
        type: 'GET',
        dataType: 'jsonp',
        success: function(data) {
          _this.dataBuffer = data;
          callback.apply();
        },
        error: function() {
          error();
        }
      });
    };
  };
  new Utils().extend(ApiInteraction, ApiInteractionAbstract);

  return ApiInteraction;
});