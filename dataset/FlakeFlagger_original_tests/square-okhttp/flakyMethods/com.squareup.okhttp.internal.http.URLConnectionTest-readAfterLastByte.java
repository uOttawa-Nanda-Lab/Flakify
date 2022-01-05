/** 
 * http://code.google.com/p/android/issues/detail?id=14562 
 */
@Test public void readAfterLastByte() throws Exception {
  server.enqueue(new MockResponse().setBody("ABC").clearHeaders().addHeader("Connection: close").setSocketPolicy(SocketPolicy.DISCONNECT_AT_END));
  server.play();
  connection=client.open(server.getUrl("/"));
  InputStream in=connection.getInputStream();
  assertEquals("ABC",readAscii(in,3));
  assertEquals(-1,in.read());
  assertEquals(-1,in.read());
}
