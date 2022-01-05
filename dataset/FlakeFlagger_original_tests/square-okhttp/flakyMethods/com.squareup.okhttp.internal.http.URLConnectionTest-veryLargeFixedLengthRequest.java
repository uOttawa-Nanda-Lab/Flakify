@Test public void veryLargeFixedLengthRequest() throws Exception {
  server.setBodyLimit(0);
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.setDoOutput(true);
  long contentLength=Integer.MAX_VALUE + 1L;
  connection.setFixedLengthStreamingMode(contentLength);
  OutputStream out=connection.getOutputStream();
  byte[] buffer=new byte[1024 * 1024];
  for (long bytesWritten=0; bytesWritten < contentLength; ) {
    int byteCount=(int)Math.min(buffer.length,contentLength - bytesWritten);
    out.write(buffer,0,byteCount);
    bytesWritten+=byteCount;
  }
  assertContent("",connection);
  RecordedRequest request=server.takeRequest();
  assertEquals(Long.toString(contentLength),request.getHeader("Content-Length"));
}
