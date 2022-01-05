@Test public void nonSingletonTest2(){
  OrbitContainer container=new OrbitContainer();
  container.add(HelloWorldNonSingleton.class);
  container.start();
  final HelloWorldNonSingleton hello1=container.get(HelloWorldNonSingleton.class);
  final HelloWorldNonSingleton hello2=container.get(HelloWorldNonSingleton.class);
  assertNotNull(hello1);
  assertNotNull(hello2);
  assertNotSame(hello1,hello2);
}
