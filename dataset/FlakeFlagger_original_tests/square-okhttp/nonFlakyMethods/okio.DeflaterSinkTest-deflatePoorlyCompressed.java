@Test public void deflatePoorlyCompressed() throws IOException {
  ByteString original=randomBytes(1024 * 1024);
  OkBuffer data=new OkBuffer();
  data.write(original);
  OkBuffer sink=new OkBuffer();
  DeflaterSink deflaterSink=new DeflaterSink(sink,new Deflater());
  deflaterSink.write(data,data.size());
  deflaterSink.close();
  OkBuffer inflated=inflate(sink);
  assertEquals(original,inflated.readByteString(inflated.size()));
}
