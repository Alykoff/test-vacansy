/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

var classes = [
  'api/ApiManagerAbstract', 
  'utils/Utils', 
  'api/ApiInteraction', 
  'api/ApiError',
  'listener/ListenerAbstract',
  'dom/WorkingWithDOM'];

define(classes, function(ApiManagerAbstract, Utils, ApiInteraction, ApiError, ListenerAbstract, WorkingWithDOM) {
    var ApiManager = function() {
      var listenersObtainingResult = [];
      var applyListnersEventObtainingResult = function(data) {
        var length = listenersObtainingResult.length;
        for (var i = 0; i < length; i++) {
          listenersObtainingResult[i].launch(data);
        }
      }

      this.addObtainingResultListener = function(listener) {
        if (listener instanceof ListenerAbstract) {
          listenersObtainingResult.push(listener);
          console.log('listener add in ApiManager');
        } else {
          throw new Error('listener has bad type');
        }
      }

      this.start = function() {
        var api = new ApiInteraction();
        var error = new ApiError();
        var workingWithDOM = new WorkingWithDOM();
        var getIdCallback = function() {
          api.checkRequestState(
            error.checkRequestStateError, 
            checkRequestStateCallback
          );
        };
        var checkRequestStateCallback = function() {
          var callGetSearchResult = function() {
            api.getSearchResult(
              error.getSearchResultError, 
              getSearchResultCallback
            );
          };
          workingWithDOM.progressResult(api.state, api.END_REQUEST_STATE);
          console.log('state: ' + api.state);
          if (api.isComplete()) {
            callGetSearchResult();
            setInterval(
              callGetSearchResult,
              api.TIME_REFRESH_RESULT
            );
          }
        };
        var getSearchResultCallback = function() {
          applyListnersEventObtainingResult(api.dataBuffer);          
        };
        api.getId(error.getIdError, getIdCallback);
      };
    };
    new Utils().extend(ApiManager, ApiManagerAbstract);

    return ApiManager;
});