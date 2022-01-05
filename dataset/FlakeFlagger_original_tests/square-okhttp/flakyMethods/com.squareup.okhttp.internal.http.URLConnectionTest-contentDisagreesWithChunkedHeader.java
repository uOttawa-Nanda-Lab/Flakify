@Test public void contentDisagreesWithChunkedHeader() throws IOException {
  MockResponse mockResponse=new MockResponse();
  mockResponse.setChunkedBody("abc",3);
  ByteArrayOutputStream bytesOut=new ByteArrayOutputStream();
  bytesOut.write(mockResponse.getBody());
  bytesOut.write("\r\nYOU SHOULD NOT SEE THIS".getBytes("UTF-8"));
  mockResponse.setBody(bytesOut.toByteArray());
  mockResponse.clearHeaders();
  mockResponse.addHeader("Transfer-encoding: chunked");
  server.enqueue(mockResponse);
  server.play();
  assertContent("abc",client.open(server.getUrl("/")));
}
