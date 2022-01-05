@Test public void testGenerateMethodResultClassName(){
  Assert.assertEquals($__DemoStub.echoString_result.class.getName(),ThriftUtils.generateMethodResultClassName(org.apache.dubbo.rpc.gen.dubbo.Demo.class.getName(),"echoString"));
  Assert.assertEquals($__DemoStub.echoString_result.class.getName(),ExtensionLoader.getExtensionLoader(ClassNameGenerator.class).getExtension(DubboClassNameGenerator.NAME).generateResultClassName(org.apache.dubbo.rpc.gen.dubbo.Demo.class.getName(),"echoString"));
}
