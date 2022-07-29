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

    @Test public void testIPv4ExactMatch() throws UnknownHostException{IPAddressAccessControlHandler handler=new IPAddressAccessControlHandler().setDefaultAllow(false).addAllow("127.0.0.1");Assert.assertTrue(handler.isAllowed(InetAddress.getByName("127.0.0.1")));Assert.assertFalse(handler.isAllowed(InetAddress.getByName("127.0.0.2")));}
}
