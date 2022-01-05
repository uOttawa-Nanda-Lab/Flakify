@Test public void clientSendsContentLength() throws Exception {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.setDoOutput(true);
  OutputStream out=connection.getOutputStream();
  out.write(new byte[]{'A','B','C'});
  out.close();
  assertEquals("A",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  RecordedRequest request=server.takeRequest();
  assertContains(request.getHeaders(),"Content-Length: 3");
}
