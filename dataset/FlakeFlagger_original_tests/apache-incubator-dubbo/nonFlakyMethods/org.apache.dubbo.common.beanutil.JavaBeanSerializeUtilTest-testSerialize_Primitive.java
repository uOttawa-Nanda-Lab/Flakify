@Test public void testSerialize_Primitive() throws Exception {
  JavaBeanDescriptor descriptor;
  descriptor=JavaBeanSerializeUtil.serialize(Integer.MAX_VALUE);
  Assert.assertTrue(descriptor.isPrimitiveType());
  Assert.assertEquals(Integer.MAX_VALUE,descriptor.getPrimitiveProperty());
  Date now=new Date();
  descriptor=JavaBeanSerializeUtil.serialize(now);
  Assert.assertTrue(descriptor.isPrimitiveType());
  Assert.assertEquals(now,descriptor.getPrimitiveProperty());
}
