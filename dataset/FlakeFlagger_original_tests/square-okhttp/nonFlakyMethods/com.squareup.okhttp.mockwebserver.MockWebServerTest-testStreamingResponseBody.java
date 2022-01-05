public void testStreamingResponseBody() throws Exception {
  InputStream responseBody=new ByteArrayInputStream("ABC".getBytes("UTF-8"));
  server.enqueue(new MockResponse().setBody(responseBody,3));
  server.play();
  InputStream in=server.getUrl("/").openConnection().getInputStream();
  assertEquals('A',in.read());
  assertEquals('B',in.read());
  assertEquals('C',in.read());
  assertEquals(-1,responseBody.read());
}
