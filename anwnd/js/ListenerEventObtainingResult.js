/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define(['Utils', 'ListenerAbstract', 'jquery'], function(Utils, ListenerAbstract, $) {
  var ListenerEventObtainingResult = function() {
    var classTemplate = 'template';
    var classWrapperItem = 'wrapper_item';
    var classFolder = 'folder';
    var classFoldArea = 'fold_area';
    var classFolderTitle = 'folder_title';
    var classItem = 'item';
    var templateFolderTitle = $('.' + classTemplate + '.' + classFolderTitle);
    var tempateFoldArea = $('.' + classTemplate + '.' + classFoldArea);
    var templateFolder = $('.' + classTemplate + '.' + classFolder);
    var templateItem = $('.' + classTemplate + '.'  + classItem);
    var templateWrapperItem = $('.' + classTemplate + '.' + classWrapperItem);
    var wrapper = $('.wrapper');
    this.air = [];
    function getFares(airLinesName, air) {
      for (var i = 0; i < air.length; i++) {
        if (air[i].company === airLinesName) {
          return air[i].fares;
        }
      }
      return [];
    };
    var isContainFare = function(fares) {
      for (var i = 0; i < fares.length; i++) {
        if (fares[i].flightNumber === fares) {
          return true;
        }
      }
      return false;
    };
    var createFolder = function(airlineCode) {
      return templateFolder
        .clone()
        .attr('id', airlineCode)
        .removeClass(classTemplate);
    };
    var createFolderTitle = function(text) {
      return templateFolderTitle
        .clone()
        .text(text)
        .removeClass(classTemplate);
    }
    var createItem = function(fare) {
      return templateItem
        .clone()
        .text(fare.departure.City + '-' + fare.arrival.City + fare.price)
        .attr('id', fare.flightNumber)
        .removeClass(classTemplate);
    };
    var createWrapperItem = function() {
      return templateWrapperItem
        .clone()
        .removeClass(classTemplate);
    }
    var createFoldArea = function() {
      return tempateFoldArea
        .clone()
        .removeClass(classTemplate);
    }
    var displayDataInFolder = function(air) {
      function buildWrapperWithItems() {
        var wrapperItem = createWrapperItem();
        for (var i = 0; i < air.fares.length; i++) {
          createItem(air.fares[i]).appendTo(wrapperItem);
        }
        return wrapperItem;
      };
      function buildFolderStruct() {
          var folder = createFolder(air.companyCode).appendTo(wrapper);
          createFolderTitle(air.companyName).appendTo(folder);
          createFoldArea().appendTo(folder);
          console.log('Add new folder.')
      };
      if ($('#' + air.companyCode).length === 0) {
        buildFolderStruct();
      }
      var folder = $('#' + air.companyCode);
      var foldersFoldArea = 
        folder
          .find('.' + classFoldArea)
          .html('');
      buildWrapperWithItems().appendTo(foldersFoldArea);
    }
    this.launch = function(data) {
      var decorateData = grubData(data);
      for (var i = 0; i < decorateData.length; i++) {
        var fares = getFares(decorateData[i], this.air);
        if (fares.length !== decorateData[i].length) {
          displayDataInFolder(decorateData[i]);
        }
      }
      this.air = decorateData;
    }
    var grubData = function(data) {
      var result = [];
      var airlines = data.Airlines;
      var airlinesLength = airlines.length;
      for (var i = 0; i < airlinesLength; i++) {
        var companyName = airlines[i].Name;
        var companyCode = airlines[i].Code;
        var fares = [];
        var faresFull = airlines[i].FaresFull;
        for (var j = 0; j < faresFull.length; j++) {
          var price = faresFull[j].Pricing.ADTTotal;
          var directions = faresFull[j].Directions;
          for (var k = 0; k < directions.length; k++) {
            var segments = directions[k].Segments;
            for (var m = 0; m < segments.length; m++) {
              var trips = segments[m].Trips;
              for (var n = 0; n < trips.length; n++) {
                fares.push({
                  flightNumber: trips[n].FlightNumber,
                  departure: trips[n].Departure,
                  arrival: trips[n].Arrival,
                  price: price    
                });
              }
            }
          }
        }
        result.push({
          companyName: companyName,
          companyCode: companyCode,
          fares: fares
        });
      }
      return result;
    }

  };
  new Utils().extend(ListenerEventObtainingResult, ListenerAbstract);
  return ListenerEventObtainingResult;
});