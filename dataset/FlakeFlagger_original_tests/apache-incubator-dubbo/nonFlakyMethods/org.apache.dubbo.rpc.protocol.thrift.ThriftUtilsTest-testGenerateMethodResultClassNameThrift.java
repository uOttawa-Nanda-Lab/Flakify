@Test public void testGenerateMethodResultClassNameThrift(){
  Assert.assertEquals(org.apache.dubbo.rpc.gen.thrift.Demo.echoString_result.class.getName(),ThriftUtils.generateMethodResultClassNameThrift(org.apache.dubbo.rpc.gen.thrift.Demo.Iface.class.getName(),"echoString"));
  Assert.assertEquals(org.apache.dubbo.rpc.gen.thrift.Demo.echoString_result.class.getName(),ExtensionLoader.getExtensionLoader(ClassNameGenerator.class).getExtension(ThriftClassNameGenerator.NAME).generateResultClassName(org.apache.dubbo.rpc.gen.thrift.Demo.Iface.class.getName(),"echoString"));
}
