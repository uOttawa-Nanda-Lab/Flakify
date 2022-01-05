/** 
 * In NAT network scenario, server's channel.getRemoteAddress() possibly get the address of network gateway, or the address converted by NAT. In this case, check port only.
 */
@Test public void testListClient() throws Exception {
  ExchangeClient client1=Exchangers.connect("dubbo://127.0.0.1:20887/demo");
  ExchangeClient client2=Exchangers.connect("dubbo://127.0.0.1:20887/demo");
  Thread.sleep(5000);
  String result=port.telnet(null,"-l 20887");
  String client1Addr=client1.getLocalAddress().toString();
  String client2Addr=client2.getLocalAddress().toString();
  System.out.printf("Result: %s %n",result);
  System.out.printf("Client 1 Address %s %n",client1Addr);
  System.out.printf("Client 2 Address %s %n",client2Addr);
  assertTrue(result.contains(String.valueOf(client1.getLocalAddress().getPort())));
  assertTrue(result.contains(String.valueOf(client2.getLocalAddress().getPort())));
}
