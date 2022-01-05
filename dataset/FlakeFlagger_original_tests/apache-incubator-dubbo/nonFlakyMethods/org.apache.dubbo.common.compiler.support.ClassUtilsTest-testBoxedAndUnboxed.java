@Test public void testBoxedAndUnboxed(){
  Assert.assertEquals(Boolean.valueOf(true),ClassUtils.boxed(true));
  Assert.assertEquals(Character.valueOf('0'),ClassUtils.boxed('0'));
  Assert.assertEquals(Byte.valueOf((byte)0),ClassUtils.boxed((byte)0));
  Assert.assertEquals(Short.valueOf((short)0),ClassUtils.boxed((short)0));
  Assert.assertEquals(Integer.valueOf((int)0),ClassUtils.boxed((int)0));
  Assert.assertEquals(Long.valueOf((long)0),ClassUtils.boxed((long)0));
  Assert.assertEquals(Float.valueOf((float)0),ClassUtils.boxed((float)0));
  Assert.assertEquals(Double.valueOf((double)0),ClassUtils.boxed((double)0));
  Assert.assertEquals(true,ClassUtils.unboxed(Boolean.valueOf(true)));
  Assert.assertEquals('0',ClassUtils.unboxed(Character.valueOf('0')));
  Assert.assertEquals((byte)0,ClassUtils.unboxed(Byte.valueOf((byte)0)));
  Assert.assertEquals((short)0,ClassUtils.unboxed(Short.valueOf((short)0)));
  Assert.assertEquals(0,ClassUtils.unboxed(Integer.valueOf((int)0)));
  Assert.assertEquals((long)0,ClassUtils.unboxed(Long.valueOf((long)0)));
  Assert.assertEquals((float)0,ClassUtils.unboxed(Float.valueOf((float)0)),((float)0));
  Assert.assertEquals((double)0,ClassUtils.unboxed(Double.valueOf((double)0)),((double)0));
}
