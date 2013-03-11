/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

require.config({
   paths: {
        jquery: [
            'lib/jquery-1.8.3'
        ]
    }
});
define(['lib/domReady', 'ApiManager', 'WorkingWithDOM', 'ListenerEventObtainingResult'], 
  function(domReady, ApiManager, WorkingWithDOM, ListenerEventObtainingResult) {
    domReady(function() {
      var listener = new ListenerEventObtainingResult();
      var manager = new ApiManager();
      new WorkingWithDOM().initDOMEvents();
      manager.addObtainingResultListener(listener);
      manager.start();
    });
});