@Test(expected=IllegalArgumentException.class) public void testGeneric2() throws Exception {
  ServiceConfig service=new ServiceConfig();
  service.setGeneric("illegal");
}
