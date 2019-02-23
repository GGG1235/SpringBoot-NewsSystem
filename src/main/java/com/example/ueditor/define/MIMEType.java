package com.example.ueditor.define;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MIMEType {

    public static final Map<String, String> types = new ConcurrentHashMap<String, String>(){{
        put( "image/gif", ".gif" );
        put( "image/jpeg", ".jpg" );
        put( "image/jpg", ".jpg" );
        put( "image/png", ".png" );
        put( "image/bmp", ".bmp" );
    }};

    public static String getSuffix ( String mime ) {
        return MIMEType.types.get( mime );
    }

}
