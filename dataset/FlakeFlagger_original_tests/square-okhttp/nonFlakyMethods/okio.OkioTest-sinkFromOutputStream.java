@Test public void sinkFromOutputStream() throws Exception {
  OkBuffer data=new OkBuffer();
  data.writeUtf8("a");
  data.writeUtf8(repeat('b',9998));
  data.writeUtf8("c");
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  Sink sink=Okio.sink(out);
  sink.write(data,3);
  assertEquals("abb",out.toString("UTF-8"));
  sink.write(data,data.size());
  assertEquals("a" + repeat('b',9998) + "c",out.toString("UTF-8"));
}
