@Test public void toStringOnEmptyByteString(){
  assertEquals("ByteString[size=0]",ByteString.of().toString());
}
