@Test public void wildcardSources(){
  Object[] sources={"classpath:org/springframework/boot/sample-${sample.app.test.prop}.xml"};
  TestSpringApplication application=new TestSpringApplication(sources);
  application.setWebEnvironment(false);
  application.run();
}
