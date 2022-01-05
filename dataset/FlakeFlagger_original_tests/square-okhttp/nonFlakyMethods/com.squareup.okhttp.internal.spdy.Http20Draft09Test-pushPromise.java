@Test public void pushPromise() throws IOException {
  OkBuffer frame=new OkBuffer();
  final int expectedPromisedStreamId=11;
  final List<Header> pushPromise=Arrays.asList(new Header(Header.TARGET_METHOD,"GET"),new Header(Header.TARGET_SCHEME,"https"),new Header(Header.TARGET_AUTHORITY,"squareup.com"),new Header(Header.TARGET_PATH,"/"));
{
    OkBuffer headerBytes=literalHeaders(pushPromise);
    frame.writeShort((int)(headerBytes.size() + 4));
    frame.writeByte(Http20Draft09.TYPE_PUSH_PROMISE);
    frame.writeByte(Http20Draft09.FLAG_END_PUSH_PROMISE);
    frame.writeInt(expectedStreamId & 0x7fffffff);
    frame.writeInt(expectedPromisedStreamId & 0x7fffffff);
    frame.write(headerBytes,headerBytes.size());
  }
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void pushPromise(    int streamId,    int promisedStreamId,    List<Header> headerBlock){
      assertEquals(expectedStreamId,streamId);
      assertEquals(expectedPromisedStreamId,promisedStreamId);
      assertEquals(pushPromise,headerBlock);
    }
  }
);
}
