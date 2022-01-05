@Test public void deflateWellCompressed() throws IOException {
  String original=repeat('a',1024 * 1024);
  OkBuffer data=new OkBuffer();
  data.writeUtf8(original);
  OkBuffer sink=new OkBuffer();
  DeflaterSink deflaterSink=new DeflaterSink(sink,new Deflater());
  deflaterSink.write(data,data.size());
  deflaterSink.close();
  OkBuffer inflated=inflate(sink);
  assertEquals(original,inflated.readUtf8(inflated.size()));
}
