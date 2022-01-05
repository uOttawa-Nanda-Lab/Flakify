/** 
 * Headers are compressed, then framed. 
 */
@Test public void headersFrameThenContinuation() throws IOException {
  OkBuffer frame=new OkBuffer();
  OkBuffer headerBlock=literalHeaders(headerEntries("foo","barrr","baz","qux"));
{
    frame.writeShort((int)(headerBlock.size() / 2));
    frame.writeByte(Http20Draft09.TYPE_HEADERS);
    frame.writeByte(0);
    frame.writeInt(expectedStreamId & 0x7fffffff);
    frame.write(headerBlock,headerBlock.size() / 2);
  }
{
    frame.writeShort((int)headerBlock.size());
    frame.writeByte(Http20Draft09.TYPE_CONTINUATION);
    frame.writeByte(Http20Draft09.FLAG_END_HEADERS);
    frame.writeInt(expectedStreamId & 0x7fffffff);
    frame.write(headerBlock,headerBlock.size());
  }
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void headers(    boolean outFinished,    boolean inFinished,    int streamId,    int associatedStreamId,    int priority,    List<Header> headerBlock,    HeadersMode headersMode){
      assertFalse(outFinished);
      assertFalse(inFinished);
      assertEquals(expectedStreamId,streamId);
      assertEquals(-1,associatedStreamId);
      assertEquals(-1,priority);
      assertEquals(headerEntries("foo","barrr","baz","qux"),headerBlock);
      assertEquals(HeadersMode.HTTP_20_HEADERS,headersMode);
    }
  }
);
}
