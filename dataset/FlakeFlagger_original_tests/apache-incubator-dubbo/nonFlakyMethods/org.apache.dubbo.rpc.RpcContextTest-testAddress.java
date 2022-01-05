@Test public void testAddress(){
  RpcContext context=RpcContext.getContext();
  context.setLocalAddress("127.0.0.1",20880);
  Assert.assertTrue(context.getLocalAddress().getPort() == 20880);
  Assert.assertEquals("127.0.0.1:20880",context.getLocalAddressString());
  context.setRemoteAddress("127.0.0.1",20880);
  Assert.assertTrue(context.getRemoteAddress().getPort() == 20880);
  Assert.assertEquals("127.0.0.1:20880",context.getRemoteAddressString());
  context.setRemoteAddress("127.0.0.1",-1);
  context.setLocalAddress("127.0.0.1",-1);
  Assert.assertTrue(context.getRemoteAddress().getPort() == 0);
  Assert.assertTrue(context.getLocalAddress().getPort() == 0);
  Assert.assertEquals("127.0.0.1",context.getRemoteHostName());
  Assert.assertEquals("127.0.0.1",context.getLocalHostName());
}
