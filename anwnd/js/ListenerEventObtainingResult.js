/**
 * @author: Alykoff Gali
 * @date: may 2013
 */

define(['Utils', 'ListenerAbstract', 'WorkingWithDOM'], 
  function(Utils, ListenerAbstract, WorkingWithDOM) {
   
    var ListenerEventObtainingResult = function() {
      var 
        workingWithDom = new WorkingWithDOM(),
        grubData = function(data) {
          var result = [];
          var airlines = data.Airlines;
          var airlinesLength = airlines.length;
          for (var i = 0; i < airlinesLength; i++) {
            var companyName = airlines[i].Name;
            var companyCode = airlines[i].Code;
            var flights = [];
            var faresFull = airlines[i].FaresFull;
            for (var j = 0; j < faresFull.length; j++) {
              var price = faresFull[j].Pricing.ADTTotal;
              var directions = faresFull[j].Directions;
              for (var k = 0; k < directions.length; k++) {
                var segments = directions[k].Segments;
                for (var m = 0; m < segments.length; m++) {
                  var trips = segments[m].Trips;
                  for (var n = 0; n < trips.length; n++) {
                    flights.push({
                      flightNumber: trips[n].FlightNumber,
                      departure: trips[n].Departure,
                      arrival: trips[n].Arrival,
                      price: price    
                    });
                  } // end loop n
                } // end loop m
              } // end loop k
            } // end loop j
            flights.sort(function(flight1, flight2) {
              return flight1.price - flight2.price;
            });
            result.push({
              companyName: companyName,
              companyCode: companyCode,
              flights: flights
            });
          }// end loop i
          return result;
        };
      this.airlines = [];
      this.launch = function(data) {
        var decorateData = grubData(data);
        workingWithDom.updateDataInDOM(decorateData, this.airlines);
        this.airlines = decorateData;
      };

    };
    new Utils().extend(ListenerEventObtainingResult, ListenerAbstract);
    return ListenerEventObtainingResult;
});