@Test(expected=IllegalStateException.class) public void testNewInstance0(){
  ClassUtils.newInstance(PrivateHelloServiceImpl.class.getName());
}
