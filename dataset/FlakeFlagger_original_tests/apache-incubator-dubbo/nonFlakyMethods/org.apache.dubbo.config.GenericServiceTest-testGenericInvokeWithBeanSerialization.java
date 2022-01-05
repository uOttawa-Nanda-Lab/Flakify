@Test public void testGenericInvokeWithBeanSerialization() throws Exception {
  ServiceConfig<DemoService> service=new ServiceConfig<DemoService>();
  service.setApplication(new ApplicationConfig("bean-provider"));
  service.setInterface(DemoService.class);
  service.setRegistry(new RegistryConfig("N/A"));
  DemoServiceImpl impl=new DemoServiceImpl();
  service.setRef(impl);
  service.setProtocol(new ProtocolConfig("dubbo",29581));
  service.export();
  ReferenceConfig<GenericService> reference=null;
  try {
    reference=new ReferenceConfig<GenericService>();
    reference.setApplication(new ApplicationConfig("bean-consumer"));
    reference.setInterface(DemoService.class);
    reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");
    reference.setGeneric(Constants.GENERIC_SERIALIZATION_BEAN);
    GenericService genericService=reference.get();
    User user=new User();
    user.setName("zhangsan");
    List<User> users=new ArrayList<User>();
    users.add(user);
    Object result=genericService.$invoke("getUsers",new String[]{ReflectUtils.getName(List.class)},new Object[]{JavaBeanSerializeUtil.serialize(users,JavaBeanAccessor.METHOD)});
    Assert.assertTrue(result instanceof JavaBeanDescriptor);
    JavaBeanDescriptor descriptor=(JavaBeanDescriptor)result;
    Assert.assertTrue(descriptor.isCollectionType());
    Assert.assertEquals(1,descriptor.propertySize());
    descriptor=(JavaBeanDescriptor)descriptor.getProperty(0);
    Assert.assertTrue(descriptor.isBeanType());
    Assert.assertEquals(user.getName(),((JavaBeanDescriptor)descriptor.getProperty("name")).getPrimitiveProperty());
  }
  finally {
    if (reference != null) {
      reference.destroy();
    }
    service.unexport();
  }
}
