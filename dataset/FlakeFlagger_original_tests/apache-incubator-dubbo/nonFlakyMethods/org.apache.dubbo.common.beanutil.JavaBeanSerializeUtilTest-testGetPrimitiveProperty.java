@Test(expected=IllegalStateException.class) public void testGetPrimitiveProperty() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(JavaBeanDescriptor.class.getName(),JavaBeanDescriptor.TYPE_BEAN);
  descriptor.getPrimitiveProperty();
}
