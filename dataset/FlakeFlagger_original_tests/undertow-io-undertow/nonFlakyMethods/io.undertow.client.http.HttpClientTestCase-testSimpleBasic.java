@Test public void testSimpleBasic() throws Exception {
  DefaultServer.setRootHandler(SIMPLE_MESSAGE_HANDLER);
  final UndertowClient client=createClient();
  final List<ClientResponse> responses=new CopyOnWriteArrayList<ClientResponse>();
  final CountDownLatch latch=new CountDownLatch(10);
  final ClientConnection connection=client.connect(ADDRESS,worker,new ByteBufferSlicePool(1024,1024),OptionMap.EMPTY).get();
  try {
    connection.getIoThread().execute(new Runnable(){
      @Override public void run(){
        for (int i=0; i < 10; i++) {
          final ClientRequest request=new ClientRequest().setMethod(Methods.GET).setPath("/");
          connection.sendRequest(request,createClientCallback(responses,latch));
        }
      }
    }
);
    latch.await(10,TimeUnit.MINUTES);
    Assert.assertEquals(10,responses.size());
    for (    final ClientResponse response : responses) {
      Assert.assertEquals(message,response.getAttachment(RESPONSE_BODY));
    }
  }
  finally {
    IoUtils.safeClose(connection);
  }
}
