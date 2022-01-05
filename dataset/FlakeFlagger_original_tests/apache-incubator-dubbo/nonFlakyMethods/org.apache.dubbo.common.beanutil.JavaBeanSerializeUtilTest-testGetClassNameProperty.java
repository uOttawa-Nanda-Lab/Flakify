@Test(expected=IllegalStateException.class) public void testGetClassNameProperty() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(long.class.getName(),JavaBeanDescriptor.TYPE_PRIMITIVE);
  descriptor.getClassNameProperty();
}
