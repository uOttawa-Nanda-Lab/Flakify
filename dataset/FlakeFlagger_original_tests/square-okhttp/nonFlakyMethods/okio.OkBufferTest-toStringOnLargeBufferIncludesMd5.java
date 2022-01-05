@Test public void toStringOnLargeBufferIncludesMd5() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.write(ByteString.encodeUtf8("12345678901234567"));
  assertEquals("OkBuffer[size=17 md5=2c9728a2138b2f25e9f89f99bdccf8db]",buffer.toString());
}
