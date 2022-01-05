@Test public void testNewInstance(){
  HelloServiceImpl0 instance=(HelloServiceImpl0)ClassUtils.newInstance(HelloServiceImpl0.class.getName());
  Assert.assertEquals("Hello world!",instance.sayHello());
}
