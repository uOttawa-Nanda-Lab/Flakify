@Test public void onlyOneLiteralHeadersFrame() throws IOException {
  final List<Header> sentHeaders=headerEntries("name","value");
  OkBuffer frame=new OkBuffer();
{
    OkBuffer headerBytes=literalHeaders(sentHeaders);
    frame.writeShort((int)headerBytes.size());
    frame.writeByte(Http20Draft09.TYPE_HEADERS);
    frame.writeByte(Http20Draft09.FLAG_END_HEADERS | Http20Draft09.FLAG_END_STREAM);
    frame.writeInt(expectedStreamId & 0x7fffffff);
    frame.write(headerBytes,headerBytes.size());
  }
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void headers(    boolean outFinished,    boolean inFinished,    int streamId,    int associatedStreamId,    int priority,    List<Header> headerBlock,    HeadersMode headersMode){
      assertFalse(outFinished);
      assertTrue(inFinished);
      assertEquals(expectedStreamId,streamId);
      assertEquals(-1,associatedStreamId);
      assertEquals(-1,priority);
      assertEquals(sentHeaders,headerBlock);
      assertEquals(HeadersMode.HTTP_20_HEADERS,headersMode);
    }
  }
);
}
