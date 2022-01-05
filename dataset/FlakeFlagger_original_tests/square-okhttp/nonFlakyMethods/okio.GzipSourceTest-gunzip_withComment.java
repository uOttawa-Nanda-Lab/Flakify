@Test public void gunzip_withComment() throws Exception {
  OkBuffer gzipped=new OkBuffer();
  gzipped.write(gzipHeaderWithFlags((byte)0x10));
  gzipped.write("rubbish".getBytes(UTF_8),0,7);
  gzipped.writeByte(0);
  gzipped.write(deflated);
  gzipped.write(gzipTrailer);
  assertGzipped(gzipped);
}
