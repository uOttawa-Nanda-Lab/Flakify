@Test public void testTabWhitespace(){
  byte[] in="GET\t/somepath\tHTTP/1.1\nHost: \t www.somehost.net\nOtherHeader:\tsome\n \t  value\n\r\n".getBytes();
  runTest(in);
}
