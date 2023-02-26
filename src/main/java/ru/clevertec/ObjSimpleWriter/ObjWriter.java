package ru.clevertec.ObjSimpleWriter;

import java.io.IOException;

public class ObjWriter {
    private boolean htmlSafe;
    private static final String[] REPLACEMENT_CHARS;
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    static {
        REPLACEMENT_CHARS = new String[128];
        for (int i = 0; i <= 0x1f; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", (int) i);
        }
        REPLACEMENT_CHARS['"'] = "\\\"";
        REPLACEMENT_CHARS['\\'] = "\\\\";
        REPLACEMENT_CHARS['\t'] = "\\t";
        REPLACEMENT_CHARS['\b'] = "\\b";
        REPLACEMENT_CHARS['\n'] = "\\n";
        REPLACEMENT_CHARS['\r'] = "\\r";
        REPLACEMENT_CHARS['\f'] = "\\f";
        HTML_SAFE_REPLACEMENT_CHARS = REPLACEMENT_CHARS.clone();
        HTML_SAFE_REPLACEMENT_CHARS['<'] = "\\u003c";
        HTML_SAFE_REPLACEMENT_CHARS['>'] = "\\u003e";
        HTML_SAFE_REPLACEMENT_CHARS['&'] = "\\u0026";
        HTML_SAFE_REPLACEMENT_CHARS['='] = "\\u003d";
        HTML_SAFE_REPLACEMENT_CHARS['\''] = "\\u0027";
    }
    private final StringBuilder out;

    private void string(String value) throws IOException {
        String[] replacements = htmlSafe ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
        out.append('\"');
        int last = 0;
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            String replacement;
            if (c < 128) {
                replacement = replacements[c];
                if (replacement == null) {
                    continue;
                }
            } else if (c == '\u2028') {
                replacement = "\\u2028";
            } else if (c == '\u2029') {
                replacement = "\\u2029";
            } else {
                continue;
            }
            if (last < i) {
                out.append(value, last, i - last);
            }
            out.append(replacement);
            last = i + 1;
        }
        if (last < length) {
            out.append(value, last, length - last);
        }
        out.append('\"');
    }
    public ObjWriter() {
        out = new StringBuilder();
    }

    public void beginObject(){
        out.append("{");
    }
    public void endObject(){
        out.append("}");
    }
    public void beginArray(){
        out.append("[");
    }
    public void afterArray(){
        out.append("]");
    }
    public void writeValueField(String s, boolean b) throws IOException {
        if(b){
            string(s);
        }else out.append(s);
    }
    public void writeFieldName(String s) throws IOException {
        string(s);
    }

    public void separator(){
        out.append(":");
    }

    public void comma(){
        out.append(",");
    }


    public String getResult(){
        return out.toString();
    }

    public void write(String s){
        out.append(s);
    }



}
