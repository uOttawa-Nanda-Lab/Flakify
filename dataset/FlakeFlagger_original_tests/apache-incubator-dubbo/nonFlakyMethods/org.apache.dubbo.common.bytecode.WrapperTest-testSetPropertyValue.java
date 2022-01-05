@Test(expected=NoSuchPropertyException.class) public void testSetPropertyValue() throws Exception {
  Wrapper w=Wrapper.getWrapper(Object.class);
  w.setPropertyValue(null,null,null);
}
