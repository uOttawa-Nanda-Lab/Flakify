@Test(expected=IllegalStateException.class) public void testSetClassNameProperty() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(long.class.getName(),JavaBeanDescriptor.TYPE_PRIMITIVE);
  descriptor.setClassNameProperty(JavaBeanDescriptor.class.getName());
}
