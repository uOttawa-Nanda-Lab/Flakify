@Test public void testGenericServiceException(){
  ServiceConfig<GenericService> service=new ServiceConfig<GenericService>();
  service.setApplication(new ApplicationConfig("generic-provider"));
  service.setRegistry(new RegistryConfig("N/A"));
  service.setProtocol(new ProtocolConfig("dubbo",29581));
  service.setInterface(DemoService.class.getName());
  service.setRef(new GenericService(){
    public Object $invoke(    String method,    String[] parameterTypes,    Object[] args) throws GenericException {
      if ("sayName".equals(method)) {
        return "Generic " + args[0];
      }
      if ("throwDemoException".equals(method)) {
        throw new GenericException(DemoException.class.getName(),"Generic");
      }
      if ("getUsers".equals(method)) {
        return args[0];
      }
      return null;
    }
  }
);
  service.export();
  try {
    ReferenceConfig<DemoService> reference=new ReferenceConfig<DemoService>();
    reference.setApplication(new ApplicationConfig("generic-consumer"));
    reference.setInterface(DemoService.class);
    reference.setUrl("dubbo://127.0.0.1:29581?generic=true&timeout=3000");
    DemoService demoService=reference.get();
    try {
      Assert.assertEquals("Generic Haha",demoService.sayName("Haha"));
      List<User> users=new ArrayList<User>();
      users.add(new User("Aaa"));
      users=demoService.getUsers(users);
      Assert.assertEquals("Aaa",users.get(0).getName());
      try {
        demoService.throwDemoException();
        Assert.fail();
      }
 catch (      DemoException e) {
        Assert.assertEquals("Generic",e.getMessage());
      }
    }
  finally {
      reference.destroy();
    }
  }
  finally {
    service.unexport();
  }
}
