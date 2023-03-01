package ru.clevertec.ObjSimpleWriterAndReader;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjReader {
    private Map<String,String> mapKeyValue;
    private final String matcherLogic =
            "[:]+((?=\\[)\\[[^]]*\\]|(?=\\{)\\{[^\\}]*\\}|\\\"[^\\\"]*\\\"|(?=\\d)\\d*.\\d*|(?=\\w)\\w*)";
    private final String matchArrayNumeric = "\\s?+:\\s?+\"?([\\-[\\w]\\s\\[\\,\\]\\.]+)?";

    private String jSonString;

    public ObjReader() {
        this.mapKeyValue = new HashMap<>();
    }
    public ObjReader(String jSonString) {
        this.mapKeyValue = new HashMap<>();
        this.jSonString = jSonString;
    }

    public void writeToMapKeyValue(String fieldName, String fieldValue){
        mapKeyValue.put(fieldName,fieldValue);
    }
    public Map<String,String> getMapKeyValue(){
        return mapKeyValue;
    }

    public String resultMatcherFieldValue(String fieldName){
        Pattern pattern = Pattern.compile(jSonFormatNameField(fieldName)+matcherLogic);
        Matcher matcher = pattern.matcher(jSonString);
        if(matcher.find()){
            return replaceComma(matcher.group(1));
        }else return null;
    }

    public String resultMatcherArrayField(String fieldArrayName){
        Pattern pattern = Pattern.compile(jSonFormatNameField(fieldArrayName)+matchArrayNumeric);
        Matcher matcher = pattern.matcher(jSonString);
        if(matcher.find()){
            return replaceComma(replaceSymbols(matcher.group(1)));
        }else return null;
    }

    public String replaceSymbols(String s){
        return s.replaceAll("\\[","").replaceAll("\\]","")
                .replaceAll("\\{","").replaceAll("\\}","");
    }
    private String jSonFormatNameField(String fieldName){
        return "\"" + fieldName + "\"";
    }

    private String replaceComma(String s){
        if(s!=null && s.length()>0 && s.toCharArray()[s.toCharArray().length-1] == ','){
            return s.substring(0,s.length()-1);
        }
        return s;
    }

    public String[] parseListArray(String valueToSet,String fieldName){
        return new String[]{"s"};
    }

    public String resultMatchListObject(boolean hasNext,String needField, String nextField){
        Pattern pattern = Pattern.compile("\""+needField+"\"[:]");
        Matcher matcher = pattern.matcher(jSonString);
        matcher.find();
        Pattern pattern1 = Pattern.compile("\""+nextField+"\"[:]");
        Matcher matcher1 = pattern1.matcher(jSonString);
        if (hasNext){
            matcher1.find();
            return replaceComma(jSonString.substring(matcher.start(),matcher1.start()));
        }else return replaceComma(jSonString.substring(matcher.start(),jSonString.length()-1));
    }
    public void setJSonString(String s){
        this.jSonString = s;
    }

}
