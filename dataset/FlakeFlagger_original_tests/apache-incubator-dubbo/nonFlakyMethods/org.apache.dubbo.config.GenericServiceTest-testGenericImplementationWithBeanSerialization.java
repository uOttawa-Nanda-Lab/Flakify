@Test public void testGenericImplementationWithBeanSerialization() throws Exception {
  final AtomicReference reference=new AtomicReference();
  ServiceConfig<GenericService> service=new ServiceConfig<GenericService>();
  service.setApplication(new ApplicationConfig("bean-provider"));
  service.setRegistry(new RegistryConfig("N/A"));
  service.setProtocol(new ProtocolConfig("dubbo",29581));
  service.setInterface(DemoService.class.getName());
  service.setRef(new GenericService(){
    public Object $invoke(    String method,    String[] parameterTypes,    Object[] args) throws GenericException {
      if ("getUsers".equals(method)) {
        GenericParameter arg=new GenericParameter();
        arg.method=method;
        arg.parameterTypes=parameterTypes;
        arg.arguments=args;
        reference.set(arg);
        return args[0];
      }
      if ("sayName".equals(method)) {
        return null;
      }
      return args;
    }
  }
);
  service.export();
  ReferenceConfig<DemoService> ref=null;
  try {
    ref=new ReferenceConfig<DemoService>();
    ref.setApplication(new ApplicationConfig("bean-consumer"));
    ref.setInterface(DemoService.class);
    ref.setUrl("dubbo://127.0.0.1:29581?scope=remote&generic=bean&timeout=3000");
    DemoService demoService=ref.get();
    User user=new User();
    user.setName("zhangsan");
    List<User> users=new ArrayList<User>();
    users.add(user);
    List<User> result=demoService.getUsers(users);
    Assert.assertEquals(users.size(),result.size());
    Assert.assertEquals(user.getName(),result.get(0).getName());
    GenericParameter gp=(GenericParameter)reference.get();
    Assert.assertEquals("getUsers",gp.method);
    Assert.assertEquals(1,gp.parameterTypes.length);
    Assert.assertEquals(ReflectUtils.getName(List.class),gp.parameterTypes[0]);
    Assert.assertEquals(1,gp.arguments.length);
    Assert.assertTrue(gp.arguments[0] instanceof JavaBeanDescriptor);
    JavaBeanDescriptor descriptor=(JavaBeanDescriptor)gp.arguments[0];
    Assert.assertTrue(descriptor.isCollectionType());
    Assert.assertEquals(ArrayList.class.getName(),descriptor.getClassName());
    Assert.assertEquals(1,descriptor.propertySize());
    descriptor=(JavaBeanDescriptor)descriptor.getProperty(0);
    Assert.assertTrue(descriptor.isBeanType());
    Assert.assertEquals(User.class.getName(),descriptor.getClassName());
    Assert.assertEquals(user.getName(),((JavaBeanDescriptor)descriptor.getProperty("name")).getPrimitiveProperty());
    Assert.assertNull(demoService.sayName("zhangsan"));
  }
  finally {
    if (ref != null) {
      ref.destroy();
    }
    service.unexport();
  }
}
