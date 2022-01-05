@Test(expected=IllegalArgumentException.class) public void testDeserialize_Null() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(null,JavaBeanDescriptor.TYPE_BEAN);
}
