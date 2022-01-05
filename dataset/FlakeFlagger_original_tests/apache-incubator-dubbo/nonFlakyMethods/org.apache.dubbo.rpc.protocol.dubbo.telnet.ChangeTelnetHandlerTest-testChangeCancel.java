@Test public void testChangeCancel() throws RemotingException {
  String result=change.telnet(mockChannel,"..");
  assertEquals("Cancelled default service org.apache.dubbo.rpc.protocol.dubbo.support.DemoService.",result);
}
