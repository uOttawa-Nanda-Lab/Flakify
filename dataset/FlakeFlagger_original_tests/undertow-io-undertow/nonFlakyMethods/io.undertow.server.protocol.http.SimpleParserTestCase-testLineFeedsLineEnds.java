@Test public void testLineFeedsLineEnds(){
  byte[] in="GET /somepath HTTP/1.1\nHost:   www.somehost.net\nOtherHeader: some\n    value\n\r\n".getBytes();
  runTest(in);
}
