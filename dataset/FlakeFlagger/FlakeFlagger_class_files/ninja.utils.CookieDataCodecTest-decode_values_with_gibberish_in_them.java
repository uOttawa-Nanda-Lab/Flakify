package ninja.utils;

import static ninja.utils.CookieDataCodec.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * CookieDataCodec and CookieDataCodecTest are imported from Play Framework.
 * 
 * Enables us to use the same sessions as Play Framework if
 * the secret is the same.
 * 
 * Also really important because we want to make sure that our client
 * side session mechanism is widely used and stable.
 * We don't want to reinvent 
 * the wheel of securely encoding / decoding and signing cookie data.
 * 
 * All praise goes to Play Framework and their awesome work.
 * 
 */
public class CookieDataCodecTest {

    private String oldEncoder(final Map<String, String> out) throws UnsupportedEncodingException {
        StringBuilder flash = new StringBuilder();
        for (String key : out.keySet()) {
            if (out.get(key) == null) continue;
            flash.append("\u0000");
            flash.append(key);
            flash.append(":");
            flash.append(out.get(key));
            flash.append("\u0000");
        }
        return URLEncoder.encode(flash.toString(), "utf-8");

    }

    @Test public void decode_values_with_gibberish_in_them() throws UnsupportedEncodingException{final String data="asfjdlkasjdflk";final Map<String, String> outMap=new HashMap<String, String>(1);decode(outMap,data);assertEquals(0,outMap.size());}
}
