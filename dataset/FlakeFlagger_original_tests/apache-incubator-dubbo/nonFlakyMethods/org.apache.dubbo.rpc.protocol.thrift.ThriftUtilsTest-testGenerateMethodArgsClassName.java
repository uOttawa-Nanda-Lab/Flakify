@Test public void testGenerateMethodArgsClassName(){
  Assert.assertEquals($__DemoStub.echoString_args.class.getName(),ThriftUtils.generateMethodArgsClassName(org.apache.dubbo.rpc.gen.dubbo.Demo.class.getName(),"echoString"));
  Assert.assertEquals($__DemoStub.echoString_args.class.getName(),ExtensionLoader.getExtensionLoader(ClassNameGenerator.class).getExtension(DubboClassNameGenerator.NAME).generateArgsClassName(org.apache.dubbo.rpc.gen.dubbo.Demo.class.getName(),"echoString"));
}
