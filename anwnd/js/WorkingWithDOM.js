/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define(['jquery', 'DisplayFolder', 'DOMClasses'], function($, DisplayFolder, DOMClasses) {
  var WorkingWithDOM = function() {
    var 
      displayFolder = new DisplayFolder(),
      classes = new DOMClasses(),
      CLASS_FOLDER = classes.getFolder(),
      CLASS_FOLDER_TITLE = classes.getFolderTitle(),
      CLASS_FOLD_FLAG = classes.getFoldFlag(),
      initClickEventOnFolderTitle = function() {
        $('.' + CLASS_FOLDER_TITLE).live('click', function(e) {
          e.preventDefault();
          var $this = $(this);
          var folder = $this.parent('.' + CLASS_FOLDER);
          if (folder.is('.' + CLASS_FOLD_FLAG)) {
            $('.' + CLASS_FOLDER).addClass(CLASS_FOLD_FLAG);
            folder.removeClass(CLASS_FOLD_FLAG);
          } else {
            folder.addClass(CLASS_FOLD_FLAG);
          }
        });
      };
    this.updateDataInDOM = function(decorateData, air) {
      for (var i = 0; i < decorateData.length; i++) {
        var flights = [];
        for (var j = 0; j < air.length; j++) {
          if (air[j].companyCode === decorateData[i].companyCode) {
            flights = air[j].flights;
          }
        }
        if (flights.length !== decorateData[i].length) {
          displayFolder.displayDataInFolder(
            decorateData[i].companyName,
            decorateData[i].companyCode,
            decorateData[i].flights);
        }
      }
    };
    this.initDOMEvents = function() {
      initClickEventOnFolderTitle();
    };
  };
  return WorkingWithDOM;
});