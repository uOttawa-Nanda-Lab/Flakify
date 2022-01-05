/** 
 * Headers are compressed, then framed. 
 */
@Test public void pushPromiseThenContinuation() throws IOException {
  OkBuffer frame=new OkBuffer();
  final int expectedPromisedStreamId=11;
  final List<Header> pushPromise=Arrays.asList(new Header(Header.TARGET_METHOD,"GET"),new Header(Header.TARGET_SCHEME,"https"),new Header(Header.TARGET_AUTHORITY,"squareup.com"),new Header(Header.TARGET_PATH,"/"));
  OkBuffer headerBlock=literalHeaders(pushPromise);
  int firstFrameLength=(int)(headerBlock.size() - 1);
{
    frame.writeShort(firstFrameLength + 4);
    frame.writeByte(Http20Draft09.TYPE_PUSH_PROMISE);
    frame.writeByte(0);
    frame.writeInt(expectedStreamId & 0x7fffffff);
    frame.writeInt(expectedPromisedStreamId & 0x7fffffff);
    frame.write(headerBlock,firstFrameLength);
  }
{
    frame.writeShort(1);
    frame.writeByte(Http20Draft09.TYPE_CONTINUATION);
    frame.writeByte(Http20Draft09.FLAG_END_HEADERS);
    frame.writeInt(expectedStreamId & 0x7fffffff);
    frame.write(headerBlock,1);
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
