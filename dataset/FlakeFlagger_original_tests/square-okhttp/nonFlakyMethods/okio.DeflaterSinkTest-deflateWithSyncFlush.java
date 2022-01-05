@Test public void deflateWithSyncFlush() throws Exception {
  String original="Yes, yes, yes. That's why we're taking extreme precautions.";
  OkBuffer data=new OkBuffer();
  data.writeUtf8(original);
  OkBuffer sink=new OkBuffer();
  DeflaterSink deflaterSink=new DeflaterSink(sink,new Deflater());
  deflaterSink.write(data,data.size());
  deflaterSink.flush();
  OkBuffer inflated=inflate(sink);
  assertEquals(original,inflated.readUtf8(inflated.size()));
}
