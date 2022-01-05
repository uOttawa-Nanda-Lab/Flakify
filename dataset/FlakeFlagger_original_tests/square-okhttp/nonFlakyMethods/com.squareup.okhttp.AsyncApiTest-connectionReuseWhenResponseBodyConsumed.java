@Test public void connectionReuseWhenResponseBodyConsumed() throws Exception {
  server.enqueue(new MockResponse().setBody("abc"));
  server.enqueue(new MockResponse().setBody("def"));
  server.play();
  Request request=new Request.Builder().url(server.getUrl("/a")).build();
  client.enqueue(request,new Response.Receiver(){
    @Override public void onFailure(    Failure failure){
      throw new AssertionError();
    }
    @Override public boolean onResponse(    Response response) throws IOException {
      InputStream bytes=response.body().byteStream();
      assertEquals('a',bytes.read());
      assertEquals('b',bytes.read());
      assertEquals('c',bytes.read());
      client.enqueue(new Request.Builder().url(server.getUrl("/b")).build(),receiver);
      return true;
    }
  }
);
  receiver.await(server.getUrl("/b")).assertCode(200).assertBody("def");
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals(1,server.takeRequest().getSequenceNumber());
}
