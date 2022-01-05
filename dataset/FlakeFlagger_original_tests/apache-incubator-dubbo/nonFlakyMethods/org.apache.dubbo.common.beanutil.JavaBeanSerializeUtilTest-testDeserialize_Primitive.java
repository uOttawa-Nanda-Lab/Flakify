@Test public void testDeserialize_Primitive() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(long.class.getName(),JavaBeanDescriptor.TYPE_PRIMITIVE);
  descriptor.setPrimitiveProperty(Long.MAX_VALUE);
  Assert.assertEquals(Long.MAX_VALUE,JavaBeanSerializeUtil.deserialize(descriptor));
  BigDecimal decimal=BigDecimal.TEN;
  Assert.assertEquals(Long.MAX_VALUE,descriptor.setPrimitiveProperty(decimal));
  Assert.assertEquals(decimal,JavaBeanSerializeUtil.deserialize(descriptor));
  String string=UUID.randomUUID().toString();
  Assert.assertEquals(decimal,descriptor.setPrimitiveProperty(string));
  Assert.assertEquals(string,JavaBeanSerializeUtil.deserialize(descriptor));
}
