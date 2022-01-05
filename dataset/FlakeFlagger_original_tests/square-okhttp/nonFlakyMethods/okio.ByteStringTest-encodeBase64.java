@Test public void encodeBase64(){
  assertEquals("",ByteString.encodeUtf8("").base64());
  assertEquals("AA==",ByteString.encodeUtf8("\u0000").base64());
  assertEquals("AAA=",ByteString.encodeUtf8("\u0000\u0000").base64());
  assertEquals("AAAA",ByteString.encodeUtf8("\u0000\u0000\u0000").base64());
  assertEquals("V2UncmUgZ29ubmEgbWFrZSBhIGZvcnR1bmUgd2l0aCB0aGlzIHBsYWNlLg==",ByteString.encodeUtf8("We're gonna make a fortune with this place.").base64());
}
