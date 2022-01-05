@Test public void outputStreamFromSink() throws Exception {
  OkBuffer sink=new OkBuffer();
  OutputStream out=new RealBufferedSink(sink).outputStream();
  out.write('a');
  out.write(repeat('b',9998).getBytes(UTF_8));
  out.write('c');
  out.flush();
  assertEquals("a" + repeat('b',9998) + "c",sink.readUtf8(10000));
}
