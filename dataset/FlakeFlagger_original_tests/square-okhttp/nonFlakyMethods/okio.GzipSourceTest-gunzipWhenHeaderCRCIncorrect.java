/** 
 * Note that you cannot test this with old versions of gzip, as they interpret flag bit 1 as CONTINUATION, not HCRC. For example, this is the case with the default gzip on osx.
 */
@Test public void gunzipWhenHeaderCRCIncorrect() throws Exception {
  OkBuffer gzipped=new OkBuffer();
  gzipped.write(gzipHeaderWithFlags((byte)0x02));
  gzipped.writeShort((short)0);
  gzipped.write(deflated);
  gzipped.write(gzipTrailer);
  try {
    gunzip(gzipped);
    fail();
  }
 catch (  IOException e) {
    assertEquals("FHCRC: actual 0x0000261d != expected 0x00000000",e.getMessage());
  }
}
