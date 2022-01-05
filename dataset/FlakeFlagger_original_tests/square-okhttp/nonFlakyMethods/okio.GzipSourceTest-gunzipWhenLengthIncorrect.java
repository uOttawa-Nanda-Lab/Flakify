@Test public void gunzipWhenLengthIncorrect() throws Exception {
  OkBuffer gzipped=new OkBuffer();
  gzipped.write(gzipHeader);
  gzipped.write(deflated);
  gzipped.write(gzipTrailer.toByteArray(),0,4);
  gzipped.writeInt(Util.reverseBytesInt(0x123456));
  try {
    gunzip(gzipped);
    fail();
  }
 catch (  IOException e) {
    assertEquals("ISIZE: actual 0x00000020 != expected 0x00123456",e.getMessage());
  }
}
