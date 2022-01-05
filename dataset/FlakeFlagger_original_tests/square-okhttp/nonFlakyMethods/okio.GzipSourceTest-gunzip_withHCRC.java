@Test public void gunzip_withHCRC() throws Exception {
  CRC32 hcrc=new CRC32();
  ByteString gzipHeader=gzipHeaderWithFlags((byte)0x02);
  hcrc.update(gzipHeader.toByteArray());
  OkBuffer gzipped=new OkBuffer();
  gzipped.write(gzipHeader);
  gzipped.writeShort(Util.reverseBytesShort((short)hcrc.getValue()));
  gzipped.write(deflated);
  gzipped.write(gzipTrailer);
  assertGzipped(gzipped);
}
