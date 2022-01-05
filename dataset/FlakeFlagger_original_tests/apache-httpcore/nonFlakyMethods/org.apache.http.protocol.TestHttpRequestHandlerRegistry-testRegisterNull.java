@Test(expected=IllegalArgumentException.class) public void testRegisterNull() throws Exception {
  HttpRequestHandlerRegistry registry=new HttpRequestHandlerRegistry();
  registry.register(null,null);
}
