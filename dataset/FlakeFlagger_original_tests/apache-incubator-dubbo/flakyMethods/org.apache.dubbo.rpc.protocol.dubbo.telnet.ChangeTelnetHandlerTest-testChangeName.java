@Test public void testChangeName() throws RemotingException {
  DubboProtocol.getDubboProtocol().export(mockInvoker);
  String result=change.telnet(mockChannel,"org.apache.dubbo.rpc.protocol.dubbo.support.DemoService");
  assertEquals("Used the org.apache.dubbo.rpc.protocol.dubbo.support.DemoService as default.\r\nYou can cancel default service by command: cd /",result);
}
