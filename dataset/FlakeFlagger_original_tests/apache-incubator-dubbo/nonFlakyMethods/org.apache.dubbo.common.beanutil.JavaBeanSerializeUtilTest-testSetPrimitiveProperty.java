@Test(expected=IllegalStateException.class) public void testSetPrimitiveProperty() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(JavaBeanDescriptor.class.getName(),JavaBeanDescriptor.TYPE_BEAN);
  descriptor.setPrimitiveProperty(JavaBeanDescriptor.class.getName());
}
