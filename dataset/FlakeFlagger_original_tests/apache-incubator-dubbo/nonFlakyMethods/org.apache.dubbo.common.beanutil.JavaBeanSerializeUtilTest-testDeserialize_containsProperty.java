@Test(expected=IllegalArgumentException.class) public void testDeserialize_containsProperty() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(long.class.getName(),JavaBeanDescriptor.TYPE_PRIMITIVE);
  descriptor.containsProperty(null);
}
