@Test(expected=IllegalStateException.class) public void testEnvironment2() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setEnvironment("illegal-env");
}
