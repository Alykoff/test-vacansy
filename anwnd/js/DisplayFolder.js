/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define(['jquery', 'DOMClasses'], function($, DOMClasses) {
  var DisplayFolder = function() {
    var 
      classes = new DOMClasses();
      CLASS_TEMPLATE = classes.getTemplate(),
      CLASS_WRAPPER_ITEM = classes.getWrapperItem(),
      CLASS_FOLDER = classes.getFolder(),
      CLASS_FOLD_AREA = classes.getFoldArea(),
      CLASS_FOLDER_TITLE = classes.getFolderTitle(),
      CLASS_ITEM = classes.getItem(),
      templateFolderTitle = $('.' + CLASS_TEMPLATE + '.' + CLASS_FOLDER_TITLE),
      tempateFoldArea = $('.' + CLASS_TEMPLATE + '.' + CLASS_FOLD_AREA),
      templateFolder = $('.' + CLASS_TEMPLATE + '.' + CLASS_FOLDER),
      templateItem = $('.' + CLASS_TEMPLATE + '.'  + CLASS_ITEM),
      templateWrapperItem = $('.' + CLASS_TEMPLATE + '.' + CLASS_WRAPPER_ITEM),
      wrapper = $('.wrapper'),
      createFolder = function(airlineCode) {
        return templateFolder
          .clone()
          .attr('id', airlineCode)
          .removeClass(CLASS_TEMPLATE);
      },
      createFolderTitle = function(text) {
        return templateFolderTitle
          .clone()
          .text(text)
          .removeClass(CLASS_TEMPLATE);
      },
      createItem = function(fare) {
        return templateItem
          .clone()
          .text(
            fare.departure.City + '-' + 
            fare.arrival.City + ' (' + 
            fare.price + ')'
          ).attr('id', fare.flightNumber)
          .removeClass(CLASS_TEMPLATE);
      },
      createWrapperItem = function() {
        return templateWrapperItem
          .clone()
          .removeClass(CLASS_TEMPLATE);
      },
      createFoldArea = function() {
        return tempateFoldArea
          .clone()
          .removeClass(CLASS_TEMPLATE);
      };

    this.displayDataInFolder = function(companyName, companyCode, flights) {
      var 
        buildWrapperWithItems = function() {
          var wrapperItem = createWrapperItem();
          for (var i = 0; i < flights.length; i++) {
            createItem(flights[i]).appendTo(wrapperItem);
          }
          return wrapperItem;
        },
        buildFolderStruct = function() {
          var folder = createFolder(companyCode).appendTo(wrapper);
          createFolderTitle(companyName).appendTo(folder);
          createFoldArea().appendTo(folder);
          console.log('Add new folder.')
        };
      if ($('#' + companyCode).length === 0) {
        buildFolderStruct();
      }
      var folder = $('#' + companyCode);
      var foldersFoldArea = 
        folder
          .find('.' + CLASS_FOLD_AREA)
          .html('');
      buildWrapperWithItems().appendTo(foldersFoldArea);
    };

  };
  return DisplayFolder;
});