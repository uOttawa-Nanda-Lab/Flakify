@Test(expected=IllegalArgumentException.class) public void testDeserialize_Primitive0() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(long.class.getName(),JavaBeanDescriptor.TYPE_BEAN + 1);
}
