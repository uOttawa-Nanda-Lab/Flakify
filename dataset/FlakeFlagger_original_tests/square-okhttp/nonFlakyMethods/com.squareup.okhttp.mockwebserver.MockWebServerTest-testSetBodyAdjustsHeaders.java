public void testSetBodyAdjustsHeaders() throws IOException {
  MockResponse response=new MockResponse().setBody("ABC");
  assertEquals(Arrays.asList("Content-Length: 3"),response.getHeaders());
  InputStream in=response.getBodyStream();
  assertEquals('A',in.read());
  assertEquals('B',in.read());
  assertEquals('C',in.read());
  assertEquals(-1,in.read());
  assertEquals("HTTP/1.1 200 OK",response.getStatus());
}
