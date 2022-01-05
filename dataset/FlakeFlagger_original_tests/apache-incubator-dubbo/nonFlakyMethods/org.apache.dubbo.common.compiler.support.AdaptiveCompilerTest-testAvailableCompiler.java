@Test public void testAvailableCompiler() throws Exception {
  AdaptiveCompiler.setDefaultCompiler("jdk");
  AdaptiveCompiler compiler=new AdaptiveCompiler();
  Class<?> clazz=compiler.compile(getSimpleCode(),AdaptiveCompiler.class.getClassLoader());
  HelloService helloService=(HelloService)clazz.newInstance();
  Assert.assertEquals("Hello world!",helloService.sayHello());
}
