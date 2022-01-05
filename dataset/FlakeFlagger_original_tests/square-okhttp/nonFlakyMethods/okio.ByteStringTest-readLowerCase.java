@Test public void readLowerCase() throws Exception {
  InputStream in=new ByteArrayInputStream("ABC".getBytes(Util.UTF_8));
  assertEquals(ByteString.encodeUtf8("ab"),ByteString.read(in,2).toAsciiLowercase());
  assertEquals(ByteString.encodeUtf8("c"),ByteString.read(in,1).toAsciiLowercase());
  assertEquals(ByteString.EMPTY,ByteString.read(in,0).toAsciiLowercase());
}
