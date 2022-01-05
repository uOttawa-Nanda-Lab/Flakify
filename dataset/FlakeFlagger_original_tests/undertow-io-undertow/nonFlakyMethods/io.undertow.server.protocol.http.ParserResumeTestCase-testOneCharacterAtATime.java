@Test public void testOneCharacterAtATime(){
  byte[] in=DATA.getBytes();
  final ParseState context=new ParseState();
  HttpServerExchange result=new HttpServerExchange(null);
  ByteBuffer buffer=ByteBuffer.wrap(in);
  buffer.limit(1);
  while (context.state != ParseState.PARSE_COMPLETE) {
    PARSER.handle(buffer,context,result);
    buffer.limit(buffer.limit() + 1);
  }
  runAssertions(result,context);
}
