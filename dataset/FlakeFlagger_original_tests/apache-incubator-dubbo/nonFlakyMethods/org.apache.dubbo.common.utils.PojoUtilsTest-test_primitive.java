@Test public void test_primitive() throws Exception {
  assertObject(Boolean.TRUE);
  assertObject(Boolean.FALSE);
  assertObject(Byte.valueOf((byte)78));
  assertObject('a');
  assertObject('中');
  assertObject(Short.valueOf((short)37));
  assertObject(78);
  assertObject(123456789L);
  assertObject(3.14F);
  assertObject(3.14D);
}
