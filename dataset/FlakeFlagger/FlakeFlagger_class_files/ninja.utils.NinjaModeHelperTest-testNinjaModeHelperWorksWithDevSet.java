package ninja.utils;

import static org.junit.Assert.assertEquals;

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
public class NinjaModeHelperTest {
    
    @Test public void testNinjaModeHelperWorksWithDevSet(){System.setProperty(NinjaConstant.MODE_KEY_NAME,NinjaConstant.MODE_DEV);assertEquals(NinjaMode.dev,NinjaModeHelper.determineModeFromSystemProperties().get());assertEquals(NinjaMode.dev,NinjaModeHelper.determineModeFromSystemPropertiesOrDevIfNotSet());System.clearProperty(NinjaConstant.MODE_KEY_NAME);}
}
