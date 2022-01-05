/** 
 * For portability, it is a good idea to export the gzipped bytes and try running gzip.  Ex. {@code echo gzipped | base64 --decode | gzip -l -v}
 */
@Test public void gunzip_withAll() throws Exception {
  OkBuffer gzipped=new OkBuffer();
  gzipped.write(gzipHeaderWithFlags((byte)0x1c));
  gzipped.writeShort(Util.reverseBytesShort((short)7));
  gzipped.write("blubber".getBytes(UTF_8),0,7);
  gzipped.write("foo.txt".getBytes(UTF_8),0,7);
  gzipped.writeByte(0);
  gzipped.write("rubbish".getBytes(UTF_8),0,7);
  gzipped.writeByte(0);
  gzipped.write(deflated);
  gzipped.write(gzipTrailer);
  assertGzipped(gzipped);
}
