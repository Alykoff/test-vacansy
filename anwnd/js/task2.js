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
define(['lib/domReady', 'ApiManager', 'Display', 'jquery'], 
  function(domReady, ApiManager, Display) {
    domReady(function() {
      var display = new Display();
      var manager = new ApiManager();
      manager.addObtainingResultListener(display.listnerObtainingResult);
      manager.start();

      $('.folder_title').live('click', function(e) {
        e.preventDefault();
        var $this = $(this);
        var folder = $this.parent('.folder');
        if (folder.is('.fold')) {
          folder.removeClass('fold');
        } else {
          folder.addClass('fold');
        }
      });      
    });
});