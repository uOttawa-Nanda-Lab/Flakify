@Test public void singletonTest(){
  OrbitContainer container=new OrbitContainer();
  container.add(HelloWorld.class);
  container.start();
  final HelloWorld hello1=container.get(HelloWorld.class);
  final HelloWorld hello2=container.get(HelloWorld.class);
  assertSame(hello1,hello2);
  container.stop();
}
