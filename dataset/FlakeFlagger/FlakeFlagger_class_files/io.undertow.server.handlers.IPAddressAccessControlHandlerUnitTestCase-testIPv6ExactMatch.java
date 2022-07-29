package io.undertow.server.handlers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for peer security handler
 *
 * @author Stuart Douglas
 */
public class IPAddressAccessControlHandlerUnitTestCase {

    @Test public void testIPv6ExactMatch() throws UnknownHostException{IPAddressAccessControlHandler handler=new IPAddressAccessControlHandler().setDefaultAllow(false).addAllow("FE45:00:00:000:0:AAA:FFFF:0045");Assert.assertTrue(handler.isAllowed(InetAddress.getByName("FE45:0:0:0:0:AAA:FFFF:45")));Assert.assertFalse(handler.isAllowed(InetAddress.getByName("127.0.0.2")));Assert.assertFalse(handler.isAllowed(InetAddress.getByName("FE45:0:0:0:0:AAA:FFFF:46")));}
}
