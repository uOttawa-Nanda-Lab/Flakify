@Test public void testIPv6SlashMatch() throws UnknownHostException {
  IPAddressAccessControlHandler handler=new IPAddressAccessControlHandler().setDefaultAllow(true).addAllow("FE45:00:00:000:0:AAA:FFFF:0045").addAllow("FE45:00:00:000:0:AAA:FFFF:01F4/127").addDeny("FE45:00:00:000:0:AAA:FFFF:0/112");
  Assert.assertTrue(handler.isAllowed(InetAddress.getByName("FE45:0:0:0:0:AAA:FFFF:45")));
  Assert.assertTrue(handler.isAllowed(InetAddress.getByName("127.0.0.2")));
  Assert.assertFalse(handler.isAllowed(InetAddress.getByName("FE45:0:0:0:0:AAA:FFFF:46")));
  Assert.assertTrue(handler.isAllowed(InetAddress.getByName("FE45:0:0:0:0:AAA:FFFb:46")));
  Assert.assertFalse(handler.isAllowed(InetAddress.getByName("fe45:0000:0000:0000:0000:0aaa:ffff:01f3")));
  Assert.assertTrue(handler.isAllowed(InetAddress.getByName("fe45:0000:0000:0000:0000:0aaa:ffff:01f4")));
  Assert.assertTrue(handler.isAllowed(InetAddress.getByName("fe45:0000:0000:0000:0000:0aaa:ffff:01f5")));
  Assert.assertFalse(handler.isAllowed(InetAddress.getByName("fe45:0000:0000:0000:0000:0aaa:ffff:01f6")));
}
