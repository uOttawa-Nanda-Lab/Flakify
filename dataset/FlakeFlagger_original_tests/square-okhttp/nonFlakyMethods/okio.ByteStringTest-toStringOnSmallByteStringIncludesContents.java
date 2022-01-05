@Test public void toStringOnSmallByteStringIncludesContents(){
  assertEquals("ByteString[size=16 data=a1b2c3d4e5f61a2b3c4d5e6f10203040]",ByteString.decodeHex("a1b2c3d4e5f61a2b3c4d5e6f10203040").toString());
}
