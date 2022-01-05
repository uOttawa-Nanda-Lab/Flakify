@Test public void testConnectionClose() throws Exception {
  DefaultServer.setRootHandler(SIMPLE_MESSAGE_HANDLER);
  final UndertowClient client=createClient();
  final CountDownLatch latch=new CountDownLatch(1);
  final ClientConnection connection=client.connect(ADDRESS,worker,new ByteBufferSlicePool(1024,1024),OptionMap.EMPTY).get();
  try {
    ClientRequest request=new ClientRequest().setPath("/1324").setMethod(Methods.GET);
    final List<ClientResponse> responses=new CopyOnWriteArrayList<ClientResponse>();
    request.getRequestHeaders().add(Headers.CONNECTION,Headers.CLOSE.toString());
    connection.sendRequest(request,createClientCallback(responses,latch));
    latch.await();
    final ClientResponse response=responses.iterator().next();
    Assert.assertEquals(message,response.getAttachment(RESPONSE_BODY));
    try {
      request=new ClientRequest().setPath("/1324").setMethod(Methods.GET);
      connection.sendRequest(request,createClientCallback(responses,latch));
      Assert.fail();
    }
 catch (    IllegalStateException e) {
    }
  }
  finally {
    IoUtils.safeClose(connection);
  }
}
