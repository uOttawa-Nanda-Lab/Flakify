@Test public void canceledBeforeResponseReadIsNeverDelivered() throws Exception {
  client.getDispatcher().setMaxRequests(1);
  server.setDispatcher(new Dispatcher(){
    char nextResponse='A';
    @Override public MockResponse dispatch(    RecordedRequest request){
      client.cancel("request A");
      return new MockResponse().setBody(Character.toString(nextResponse++));
    }
  }
);
  server.play();
  Request requestA=new Request.Builder().url(server.getUrl("/a")).tag("request A").build();
  client.enqueue(requestA,receiver);
  assertEquals("/a",server.takeRequest().getPath());
  Request requestB=new Request.Builder().url(server.getUrl("/b")).tag("request B").build();
  client.enqueue(requestB,receiver);
  assertEquals("/b",server.takeRequest().getPath());
  receiver.await(requestB.url()).assertBody("B");
  receiver.assertNoResponse(requestA.url());
}
