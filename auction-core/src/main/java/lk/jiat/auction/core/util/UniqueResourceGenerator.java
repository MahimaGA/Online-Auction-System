package lk.jiat.auction.core.util;

public class UniqueResourceGenerator {

    private UniqueResourceGenerator(){

    }

    public static String generateIdForBeans(String prefix, int numberLength){
        String number = String.valueOf(System.currentTimeMillis());
        String uniqueId = null;

        if(prefix != null && (number.length() > numberLength)){
            uniqueId = prefix + number.substring((number.length() - 1) - numberLength, (number.length() - 1));
        }

        return uniqueId;
    }

    public static String getJsonTextValue(String bidJsonText, String property){
        String[] baseValues = bidJsonText.split(",");

        String finalValue = null;

        for(String rawSegment : baseValues){

            String[] baseSegmentValues = rawSegment.split(":");

            if(baseSegmentValues[0].contains(property)){
                String text = baseSegmentValues[1];

                if(text.contains("{")){
                    text = text.replace("{", "");
                }

                if(text.contains("}")){
                    text = text.replace("}", "");
                }

                if(text.contains("\"")){
                    text = text.replace("\"", "");
                }

                if(text.contains("\'")){
                    text = text.replace("\'", "");
                }

                if(text.contains(" ")){
                    text = text.replace(" ", "");
                }

                finalValue = text;

                break;
            }
        }

        return finalValue;
    }
}
