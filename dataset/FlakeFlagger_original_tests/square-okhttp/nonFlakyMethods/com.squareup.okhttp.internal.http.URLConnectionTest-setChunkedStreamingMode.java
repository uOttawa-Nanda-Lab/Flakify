@Test public void setChunkedStreamingMode() throws IOException, InterruptedException {
  server.enqueue(new MockResponse());
  server.play();
  String body="ABCDEFGHIJKLMNOPQ";
  connection=client.open(server.getUrl("/"));
  connection.setChunkedStreamingMode(0);
  connection.setDoOutput(true);
  OutputStream outputStream=connection.getOutputStream();
  outputStream.write(body.getBytes("US-ASCII"));
  assertEquals(200,connection.getResponseCode());
  RecordedRequest request=server.takeRequest();
  assertEquals(body,new String(request.getBody(),"US-ASCII"));
  assertEquals(Arrays.asList(body.length()),request.getChunkSizes());
}
