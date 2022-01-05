@Test(expected=NoSuchMethodException.class) public void testNoSuchMethod() throws Exception {
  Wrapper w=Wrapper.getWrapper(Object.class);
  w.invokeMethod(new Object(),"__XX__",null,null);
}
