@Test public void gunzip_withName() throws Exception {
  OkBuffer gzipped=new OkBuffer();
  gzipped.write(gzipHeaderWithFlags((byte)0x08));
  gzipped.write("foo.txt".getBytes(UTF_8),0,7);
  gzipped.writeByte(0);
  gzipped.write(deflated);
  gzipped.write(gzipTrailer);
  assertGzipped(gzipped);
}
