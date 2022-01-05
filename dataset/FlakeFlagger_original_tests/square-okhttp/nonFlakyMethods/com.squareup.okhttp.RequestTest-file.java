@Test public void file() throws Exception {
  File file=File.createTempFile("RequestTest","tmp");
  FileWriter writer=new FileWriter(file);
  writer.write("abc");
  writer.close();
  MediaType contentType=MediaType.parse("text/plain");
  Request.Body body=Request.Body.create(contentType,file);
  assertEquals(contentType,body.contentType());
  assertEquals(3,body.contentLength());
  assertEquals("616263",bodyToHex(body));
  assertEquals("Retransmit body","616263",bodyToHex(body));
}
