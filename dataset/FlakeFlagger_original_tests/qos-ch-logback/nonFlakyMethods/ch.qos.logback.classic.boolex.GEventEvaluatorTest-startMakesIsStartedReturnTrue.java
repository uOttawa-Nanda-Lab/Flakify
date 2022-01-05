@Test public void startMakesIsStartedReturnTrue(){
  gee.setExpression("return true");
  gee.start();
  assertTrue(gee.isStarted());
}
