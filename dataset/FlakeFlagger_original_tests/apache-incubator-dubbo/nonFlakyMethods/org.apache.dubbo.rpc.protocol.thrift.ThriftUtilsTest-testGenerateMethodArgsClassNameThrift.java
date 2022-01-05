@Test public void testGenerateMethodArgsClassNameThrift(){
  Assert.assertEquals(org.apache.dubbo.rpc.gen.thrift.Demo.echoString_args.class.getName(),ThriftUtils.generateMethodArgsClassNameThrift(org.apache.dubbo.rpc.gen.thrift.Demo.Iface.class.getName(),"echoString"));
  Assert.assertEquals(org.apache.dubbo.rpc.gen.thrift.Demo.echoString_args.class.getName(),ExtensionLoader.getExtensionLoader(ClassNameGenerator.class).getExtension(ThriftClassNameGenerator.NAME).generateArgsClassName(org.apache.dubbo.rpc.gen.thrift.Demo.Iface.class.getName(),"echoString"));
}
