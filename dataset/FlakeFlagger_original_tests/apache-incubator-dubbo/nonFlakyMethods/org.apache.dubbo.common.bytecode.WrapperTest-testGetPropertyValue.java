@Test(expected=NoSuchPropertyException.class) public void testGetPropertyValue() throws Exception {
  Wrapper w=Wrapper.getWrapper(Object.class);
  w.getPropertyValue(null,null);
}
