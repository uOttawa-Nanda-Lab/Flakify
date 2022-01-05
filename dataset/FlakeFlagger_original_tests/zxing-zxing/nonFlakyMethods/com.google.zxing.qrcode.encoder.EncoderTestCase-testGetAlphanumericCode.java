@Test public void testGetAlphanumericCode(){
  for (int i=0; i < 10; ++i) {
    assertEquals(i,Encoder.getAlphanumericCode('0' + i));
  }
  for (int i=10; i < 36; ++i) {
    assertEquals(i,Encoder.getAlphanumericCode('A' + i - 10));
  }
  assertEquals(36,Encoder.getAlphanumericCode(' '));
  assertEquals(37,Encoder.getAlphanumericCode('$'));
  assertEquals(38,Encoder.getAlphanumericCode('%'));
  assertEquals(39,Encoder.getAlphanumericCode('*'));
  assertEquals(40,Encoder.getAlphanumericCode('+'));
  assertEquals(41,Encoder.getAlphanumericCode('-'));
  assertEquals(42,Encoder.getAlphanumericCode('.'));
  assertEquals(43,Encoder.getAlphanumericCode('/'));
  assertEquals(44,Encoder.getAlphanumericCode(':'));
  assertEquals(-1,Encoder.getAlphanumericCode('a'));
  assertEquals(-1,Encoder.getAlphanumericCode('#'));
  assertEquals(-1,Encoder.getAlphanumericCode('\0'));
}
