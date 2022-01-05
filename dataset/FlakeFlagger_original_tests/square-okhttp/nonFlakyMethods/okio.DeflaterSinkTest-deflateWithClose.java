@Test public void deflateWithClose() throws Exception {
  OkBuffer data=new OkBuffer();
  String original="They're moving in herds. They do move in herds.";
  data.writeUtf8(original);
  OkBuffer sink=new OkBuffer();
  DeflaterSink deflaterSink=new DeflaterSink(sink,new Deflater());
  deflaterSink.write(data,data.size());
  deflaterSink.close();
  OkBuffer inflated=inflate(sink);
  assertEquals(original,inflated.readUtf8(inflated.size()));
}
