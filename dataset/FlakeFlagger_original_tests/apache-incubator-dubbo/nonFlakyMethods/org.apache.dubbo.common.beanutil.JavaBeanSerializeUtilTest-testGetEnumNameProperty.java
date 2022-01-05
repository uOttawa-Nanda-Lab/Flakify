@Test(expected=IllegalStateException.class) public void testGetEnumNameProperty() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(long.class.getName(),JavaBeanDescriptor.TYPE_PRIMITIVE);
  descriptor.getEnumPropertyName();
}
