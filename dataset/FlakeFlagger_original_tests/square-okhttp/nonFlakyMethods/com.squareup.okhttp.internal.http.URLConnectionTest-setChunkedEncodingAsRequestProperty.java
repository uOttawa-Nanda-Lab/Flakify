@Test public void setChunkedEncodingAsRequestProperty() throws IOException, InterruptedException {
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.setRequestProperty("Transfer-encoding","chunked");
  connection.setDoOutput(true);
  connection.getOutputStream().write("ABC".getBytes("UTF-8"));
  assertEquals(200,connection.getResponseCode());
  RecordedRequest request=server.takeRequest();
  assertEquals("ABC",new String(request.getBody(),"UTF-8"));
}
