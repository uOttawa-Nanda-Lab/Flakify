@Test public void responseCachedWithoutConsumingFullBody() throws IOException {
  client.setOkResponseCache(cache);
  server.enqueue(new MockResponse().addHeader("cache-control: max-age=60").setBody("ABCD"));
  server.enqueue(new MockResponse().addHeader("cache-control: max-age=60").setBody("EFGH"));
  server.play();
  HttpURLConnection connection1=client.open(server.getUrl("/"));
  InputStream in1=connection1.getInputStream();
  assertEquals("AB",readAscii(in1,2));
  in1.close();
  HttpURLConnection connection2=client.open(server.getUrl("/"));
  InputStream in2=connection2.getInputStream();
  assertEquals("ABCD",readAscii(in2,Integer.MAX_VALUE));
  in2.close();
}
