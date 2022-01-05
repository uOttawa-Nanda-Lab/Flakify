@Test public void testNoHeaders(){
  byte[] in="GET\t/aa\tHTTP/1.1\n\n\n".getBytes();
  final ParseState context=new ParseState();
  HttpServerExchange result=new HttpServerExchange(null);
  HttpRequestParser.instance(OptionMap.EMPTY).handle(ByteBuffer.wrap(in),context,result);
  Assert.assertTrue(context.isComplete());
  Assert.assertEquals("/aa",result.getRelativePath());
}
