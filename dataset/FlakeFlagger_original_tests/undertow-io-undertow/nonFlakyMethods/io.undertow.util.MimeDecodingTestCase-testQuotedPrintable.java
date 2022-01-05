@Test public void testQuotedPrintable() throws IOException {
  final String data=fixLineEndings(FileUtils.readFile(MimeDecodingTestCase.class,"mime4.txt"));
  TestPartHandler handler=new TestPartHandler();
  MultipartParser.ParseState parser=MultipartParser.beginParse(bufferPool,handler,"someboundarytext".getBytes());
  ByteBuffer buf=ByteBuffer.wrap(data.getBytes());
  parser.parse(buf);
  Assert.assertTrue(parser.isComplete());
  Assert.assertEquals(1,handler.parts.size());
  Assert.assertEquals("time=money.",handler.parts.get(0).data.toString());
  Assert.assertEquals("text/plain",handler.parts.get(0).map.getFirst(Headers.CONTENT_TYPE));
}
