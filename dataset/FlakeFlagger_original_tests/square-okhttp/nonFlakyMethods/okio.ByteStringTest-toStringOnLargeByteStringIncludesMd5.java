@Test public void toStringOnLargeByteStringIncludesMd5(){
  assertEquals("ByteString[size=17 md5=2c9728a2138b2f25e9f89f99bdccf8db]",ByteString.encodeUtf8("12345678901234567").toString());
}
