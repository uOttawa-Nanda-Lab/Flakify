@Test public void testCanonicalPath(){
  byte[] in="GET\thttp://www.somehost.net/somepath\tHTTP/1.1\nHost: \t www.somehost.net\nOtherHeader:\tsome\n \t  value\n\r\n".getBytes();
  final ParseState context=new ParseState();
  HttpServerExchange result=new HttpServerExchange(null);
  HttpRequestParser.instance(OptionMap.EMPTY).handle(ByteBuffer.wrap(in),context,result);
  Assert.assertEquals("/somepath",result.getRelativePath());
  Assert.assertEquals("http://www.somehost.net/somepath",result.getRequestURI());
}
