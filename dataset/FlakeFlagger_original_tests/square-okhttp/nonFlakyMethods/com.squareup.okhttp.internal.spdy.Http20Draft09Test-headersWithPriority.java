@Test public void headersWithPriority() throws IOException {
  OkBuffer frame=new OkBuffer();
  final List<Header> sentHeaders=headerEntries("name","value");
{
    OkBuffer headerBytes=literalHeaders(sentHeaders);
    frame.writeShort((int)(headerBytes.size() + 4));
    frame.writeByte(Http20Draft09.TYPE_HEADERS);
    frame.writeByte(Http20Draft09.FLAG_END_HEADERS | Http20Draft09.FLAG_PRIORITY);
    frame.writeInt(expectedStreamId & 0x7fffffff);
    frame.writeInt(0);
    frame.write(headerBytes,headerBytes.size());
  }
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void headers(    boolean outFinished,    boolean inFinished,    int streamId,    int associatedStreamId,    int priority,    List<Header> nameValueBlock,    HeadersMode headersMode){
      assertFalse(outFinished);
      assertFalse(inFinished);
      assertEquals(expectedStreamId,streamId);
      assertEquals(-1,associatedStreamId);
      assertEquals(0,priority);
      assertEquals(sentHeaders,nameValueBlock);
      assertEquals(HeadersMode.HTTP_20_HEADERS,headersMode);
    }
  }
);
}
