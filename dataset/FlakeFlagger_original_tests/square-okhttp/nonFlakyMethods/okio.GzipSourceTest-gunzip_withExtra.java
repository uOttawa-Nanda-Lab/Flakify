@Test public void gunzip_withExtra() throws Exception {
  OkBuffer gzipped=new OkBuffer();
  gzipped.write(gzipHeaderWithFlags((byte)0x04));
  gzipped.writeShort(Util.reverseBytesShort((short)7));
  gzipped.write("blubber".getBytes(UTF_8),0,7);
  gzipped.write(deflated);
  gzipped.write(gzipTrailer);
  assertGzipped(gzipped);
}
