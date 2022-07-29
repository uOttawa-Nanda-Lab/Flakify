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

    @Test public void testIPv4WildcardMatch() throws UnknownHostException{IPAddressAccessControlHandler handler=new IPAddressAccessControlHandler().setDefaultAllow(true).addAllow("127.0.0.1").addDeny("127.0.*.*");Assert.assertTrue(handler.isAllowed(InetAddress.getByName("127.0.0.1")));Assert.assertFalse(handler.isAllowed(InetAddress.getByName("127.0.0.2")));Assert.assertTrue(handler.isAllowed(InetAddress.getByName("127.1.0.2")));}
}
