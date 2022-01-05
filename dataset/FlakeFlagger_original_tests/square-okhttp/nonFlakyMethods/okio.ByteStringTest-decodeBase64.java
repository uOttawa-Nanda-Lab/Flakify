@Test public void decodeBase64(){
  assertEquals("",ByteString.decodeBase64("").utf8());
  assertEquals(null,ByteString.decodeBase64("/==="));
  assertEquals(ByteString.decodeHex("ff"),ByteString.decodeBase64("//=="));
  assertEquals(ByteString.decodeHex("ffff"),ByteString.decodeBase64("///="));
  assertEquals(ByteString.decodeHex("ffffff"),ByteString.decodeBase64("////"));
  assertEquals(ByteString.decodeHex("ffffffffffff"),ByteString.decodeBase64("////////"));
  assertEquals("What's to be scared about? It's just a little hiccup in the power...",ByteString.decodeBase64("V2hhdCdzIHRvIGJlIHNjYXJlZCBhYm91dD8gSXQncyBqdXN0IGEgbGl0dGxlIGhpY2" + "N1cCBpbiB0aGUgcG93ZXIuLi4=").utf8());
}
