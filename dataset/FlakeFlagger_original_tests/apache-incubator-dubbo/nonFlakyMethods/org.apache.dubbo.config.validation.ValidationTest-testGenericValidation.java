@Test public void testGenericValidation(){
  ServiceConfig<ValidationService> service=new ServiceConfig<ValidationService>();
  service.setApplication(new ApplicationConfig("validation-provider"));
  service.setRegistry(new RegistryConfig("N/A"));
  service.setProtocol(new ProtocolConfig("dubbo",29582));
  service.setInterface(ValidationService.class.getName());
  service.setRef(new ValidationServiceImpl());
  service.setValidation(String.valueOf(true));
  service.export();
  try {
    ReferenceConfig<GenericService> reference=new ReferenceConfig<GenericService>();
    reference.setApplication(new ApplicationConfig("validation-consumer"));
    reference.setInterface(ValidationService.class.getName());
    reference.setUrl("dubbo://127.0.0.1:29582?scope=remote&validation=true&timeout=9000000");
    reference.setGeneric(true);
    GenericService validationService=reference.get();
    try {
      Map<String,Object> parameter=new HashMap<String,Object>();
      parameter.put("name","liangfei");
      parameter.put("Email","liangfei@liang.fei");
      parameter.put("Age",50);
      parameter.put("LoginDate",new Date(System.currentTimeMillis() - 1000000));
      parameter.put("ExpiryDate",new Date(System.currentTimeMillis() + 1000000));
      validationService.$invoke("save",new String[]{ValidationParameter.class.getName()},new Object[]{parameter});
      try {
        parameter=new HashMap<String,Object>();
        validationService.$invoke("save",new String[]{ValidationParameter.class.getName()},new Object[]{parameter});
        Assert.fail();
      }
 catch (      GenericException e) {
        Assert.assertTrue(e.getMessage().contains("Failed to validate service"));
      }
      validationService.$invoke("delete",new String[]{long.class.getName(),String.class.getName()},new Object[]{2,"abc"});
      try {
        validationService.$invoke("delete",new String[]{long.class.getName(),String.class.getName()},new Object[]{0,"abc"});
        Assert.fail();
      }
 catch (      GenericException e) {
        Assert.assertTrue(e.getMessage().contains("Failed to validate service"));
      }
      try {
        validationService.$invoke("delete",new String[]{long.class.getName(),String.class.getName()},new Object[]{2,null});
        Assert.fail();
      }
 catch (      GenericException e) {
        Assert.assertTrue(e.getMessage().contains("Failed to validate service"));
      }
      try {
        validationService.$invoke("delete",new String[]{long.class.getName(),String.class.getName()},new Object[]{0,null});
        Assert.fail();
      }
 catch (      GenericException e) {
        Assert.assertTrue(e.getMessage().contains("Failed to validate service"));
      }
    }
 catch (    GenericException e) {
      Assert.assertTrue(e.getMessage().contains("Failed to validate service"));
    }
 finally {
      reference.destroy();
    }
  }
  finally {
    service.unexport();
  }
}
