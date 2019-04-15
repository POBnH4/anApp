package com.example.peterboncheff.timetogo;


public class SkyscannerAPI {

//    Skyscanner

    //    HttpResponse<JsonNode> response = Unirest.post("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0")
//            .header("X-RapidAPI-Key", "49f5b3a2d0mshd3b97efe440fdf6p1d9247jsnc7f3da915b02")
//            .header("Content-Type", "application/x-www-form-urlencoded")
//            .field("inboundDate", "2019-01-10")
//            .field("cabinClass", "business")
//            .field("children", 0)
//            .field("infants", 0)
//            .field("country", "US")
//            .field("currency", "USD")
//            .field("locale", "en-US")
//            .field("originPlace", "SFO-sky")
//            .field("destinationPlace", "LHR-sky")
//            .field("outboundDate", "2019-01-01")
//            .field("adults", 1)
//            .asJson();
//
//

//    Terminal Information URI Method

//    HttpResponse<JsonNode> response = Unirest.get("https://flightlookup-terminalinfo-uri.p.rapidapi.com/TerminalInfo/?To=LAX&Date=12%2F17%2F2012&Airline=AA&From=BOS")
//            .header("X-RapidAPI-Key", "49f5b3a2d0mshd3b97efe440fdf6p1d9247jsnc7f3da915b02")
//            .asJson();


    private String getAPIJSON(){
        String JSON = "";
        final String FLIGHT_TERMINAL_API = "49f5b3a2d0mshd3b97efe440fdf6p1d9247jsnc7f3da915b02";
        final String WEBSITE_LINK = "https://flightlookup-terminalinfo-uri.p.rapidapi.com/TerminalInfo/?To=LAX&Date=12%2F17%2F2012&Airline=AA&From=BOS";
        final String RAPID_KEY_HEADER = "X-RapidAPI-Key";
        try {
//            HttpResponse<JsonNode> response = Unirest.get(WEBSITE_LINK)
//                    .header(RAPID_KEY_HEADER, FLIGHT_TERMINAL_API)
//                    .asJson();

            //System.out.println();
        }catch(Exception e){
            e.printStackTrace();
        }
        return JSON;
    }


}
