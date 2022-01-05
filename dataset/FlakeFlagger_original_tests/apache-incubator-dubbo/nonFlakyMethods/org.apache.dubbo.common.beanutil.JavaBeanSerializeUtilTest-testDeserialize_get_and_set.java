@Test public void testDeserialize_get_and_set() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(long.class.getName(),JavaBeanDescriptor.TYPE_BEAN);
  descriptor.setType(JavaBeanDescriptor.TYPE_PRIMITIVE);
  Assert.assertTrue(descriptor.getType() == JavaBeanDescriptor.TYPE_PRIMITIVE);
  descriptor.setClassName(JavaBeanDescriptor.class.getName());
  Assert.assertEquals(JavaBeanDescriptor.class.getName(),descriptor.getClassName());
}
