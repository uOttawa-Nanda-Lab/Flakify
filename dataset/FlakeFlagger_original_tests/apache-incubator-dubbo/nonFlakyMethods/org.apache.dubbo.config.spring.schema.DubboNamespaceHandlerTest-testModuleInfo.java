@Test public void testModuleInfo(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/provider-with-module.xml");
  ctx.start();
  ModuleConfig moduleConfig=ctx.getBean(ModuleConfig.class);
  assertThat(moduleConfig.getName(),is("test-module"));
}
