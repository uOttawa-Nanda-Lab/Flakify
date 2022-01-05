@Test public void concat(){
  assertEquals(ByteString.of(),ByteString.concat());
  assertEquals(ByteString.of(),ByteString.concat(ByteString.EMPTY));
  assertEquals(ByteString.of(),ByteString.concat(ByteString.EMPTY,ByteString.EMPTY));
  ByteString foo=ByteString.encodeUtf8("foo");
  ByteString bar=ByteString.encodeUtf8("bar");
  assertEquals(foo,ByteString.concat(foo));
  assertEquals(ByteString.encodeUtf8("foobar"),ByteString.concat(foo,bar));
}
