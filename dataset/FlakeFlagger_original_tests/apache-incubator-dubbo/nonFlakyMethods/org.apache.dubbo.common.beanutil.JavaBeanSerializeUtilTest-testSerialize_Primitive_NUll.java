@Test public void testSerialize_Primitive_NUll() throws Exception {
  JavaBeanDescriptor descriptor;
  descriptor=JavaBeanSerializeUtil.serialize(null);
  Assert.assertTrue(descriptor == null);
}
