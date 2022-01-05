@SuppressWarnings("unchecked") @Test public void testGenericReferenceException(){
  ServiceConfig<DemoService> service=new ServiceConfig<DemoService>();
  service.setApplication(new ApplicationConfig("generic-provider"));
  service.setRegistry(new RegistryConfig("N/A"));
  service.setProtocol(new ProtocolConfig("dubbo",29581));
  service.setInterface(DemoService.class.getName());
  service.setRef(new DemoServiceImpl());
  service.export();
  try {
    ReferenceConfig<GenericService> reference=new ReferenceConfig<GenericService>();
    reference.setApplication(new ApplicationConfig("generic-consumer"));
    reference.setInterface(DemoService.class);
    reference.setUrl("dubbo://127.0.0.1:29581?scope=remote&timeout=3000");
    reference.setGeneric(true);
    GenericService genericService=reference.get();
    try {
      List<Map<String,Object>> users=new ArrayList<Map<String,Object>>();
      Map<String,Object> user=new HashMap<String,Object>();
      user.put("class","org.apache.dubbo.config.api.User");
      user.put("name","actual.provider");
      users.add(user);
      users=(List<Map<String,Object>>)genericService.$invoke("getUsers",new String[]{List.class.getName()},new Object[]{users});
      Assert.assertEquals(1,users.size());
      Assert.assertEquals("actual.provider",users.get(0).get("name"));
    }
  finally {
      reference.destroy();
    }
  }
  finally {
    service.unexport();
  }
}
