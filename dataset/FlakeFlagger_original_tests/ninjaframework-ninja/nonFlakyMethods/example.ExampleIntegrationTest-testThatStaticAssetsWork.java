@Test public void testThatStaticAssetsWork(){
  goTo(getServerAddress() + "/assets/webjars/bootstrap/3.0.0/css/bootstrap.min.css");
  assertTrue(pageSource().contains("Bootstrap"));
}
