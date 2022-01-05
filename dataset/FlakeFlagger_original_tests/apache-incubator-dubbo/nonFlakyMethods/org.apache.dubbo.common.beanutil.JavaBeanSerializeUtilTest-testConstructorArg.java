@Test public void testConstructorArg(){
  Assert.assertFalse((boolean)JavaBeanSerializeUtil.getConstructorArg(boolean.class));
  Assert.assertFalse((boolean)JavaBeanSerializeUtil.getConstructorArg(Boolean.class));
  Assert.assertEquals((byte)0,JavaBeanSerializeUtil.getConstructorArg(byte.class));
  Assert.assertEquals((byte)0,JavaBeanSerializeUtil.getConstructorArg(Byte.class));
  Assert.assertEquals((short)0,JavaBeanSerializeUtil.getConstructorArg(short.class));
  Assert.assertEquals((short)0,JavaBeanSerializeUtil.getConstructorArg(Short.class));
  Assert.assertEquals(0,JavaBeanSerializeUtil.getConstructorArg(int.class));
  Assert.assertEquals(0,JavaBeanSerializeUtil.getConstructorArg(Integer.class));
  Assert.assertEquals((long)0,JavaBeanSerializeUtil.getConstructorArg(long.class));
  Assert.assertEquals((long)0,JavaBeanSerializeUtil.getConstructorArg(Long.class));
  Assert.assertEquals((float)0,JavaBeanSerializeUtil.getConstructorArg(float.class));
  Assert.assertEquals((float)0,JavaBeanSerializeUtil.getConstructorArg(Float.class));
  Assert.assertEquals((double)0,JavaBeanSerializeUtil.getConstructorArg(double.class));
  Assert.assertEquals((double)0,JavaBeanSerializeUtil.getConstructorArg(Double.class));
  Assert.assertEquals((char)0,JavaBeanSerializeUtil.getConstructorArg(char.class));
  Assert.assertEquals(new Character((char)0),JavaBeanSerializeUtil.getConstructorArg(Character.class));
  Assert.assertEquals(null,JavaBeanSerializeUtil.getConstructorArg(JavaBeanSerializeUtil.class));
}
