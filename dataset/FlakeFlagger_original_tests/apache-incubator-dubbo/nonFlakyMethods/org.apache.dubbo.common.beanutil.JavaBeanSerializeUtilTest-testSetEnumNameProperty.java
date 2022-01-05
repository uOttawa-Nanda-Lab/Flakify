@Test(expected=IllegalStateException.class) public void testSetEnumNameProperty() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(long.class.getName(),JavaBeanDescriptor.TYPE_PRIMITIVE);
  descriptor.setEnumNameProperty(JavaBeanDescriptor.class.getName());
}
