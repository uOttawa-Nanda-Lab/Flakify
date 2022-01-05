@Test public void gunzipWhenCRCIncorrect() throws Exception {
  OkBuffer gzipped=new OkBuffer();
  gzipped.write(gzipHeader);
  gzipped.write(deflated);
  gzipped.writeInt(Util.reverseBytesInt(0x1234567));
  gzipped.write(gzipTrailer.toByteArray(),3,4);
  try {
    gunzip(gzipped);
    fail();
  }
 catch (  IOException e) {
    assertEquals("CRC: actual 0x37ad8f8d != expected 0x01234567",e.getMessage());
  }
}
