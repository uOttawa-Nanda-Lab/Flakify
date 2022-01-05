@Test public void canceledAfterResponseIsDeliveredDoesNothing() throws Exception {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  final CountDownLatch latch=new CountDownLatch(1);
  final AtomicReference<String> bodyRef=new AtomicReference<String>();
  Request request=new Request.Builder().url(server.getUrl("/a")).tag("request A").build();
  client.enqueue(request,new Response.Receiver(){
    @Override public void onFailure(    Failure failure){
      throw new AssertionError();
    }
    @Override public boolean onResponse(    Response response) throws IOException {
      client.cancel("request A");
      bodyRef.set(response.body().string());
      latch.countDown();
      return true;
    }
  }
);
  latch.await();
  assertEquals("A",bodyRef.get());
}
