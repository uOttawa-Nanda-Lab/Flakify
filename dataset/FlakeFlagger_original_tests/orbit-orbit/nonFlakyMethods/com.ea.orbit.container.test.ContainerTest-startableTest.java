@Test public void startableTest(){
  OrbitContainer container=new OrbitContainer();
  container.add(HelloWorld.class);
  container.start();
  final HelloWorld hello=container.get(HelloWorld.class);
  assertTrue(hello.startCalled);
  assertFalse(hello.stopCalled);
  container.stop();
  assertTrue(hello.stopCalled);
}
