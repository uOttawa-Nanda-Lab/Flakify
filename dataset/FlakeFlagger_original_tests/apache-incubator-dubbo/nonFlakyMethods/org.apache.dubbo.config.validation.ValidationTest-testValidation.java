@Test public void testValidation(){
  ServiceConfig<ValidationService> service=new ServiceConfig<ValidationService>();
  service.setApplication(new ApplicationConfig("validation-provider"));
  service.setRegistry(new RegistryConfig("N/A"));
  service.setProtocol(new ProtocolConfig("dubbo",29582));
  service.setInterface(ValidationService.class.getName());
  service.setRef(new ValidationServiceImpl());
  service.setValidation(String.valueOf(true));
  service.export();
  try {
    ReferenceConfig<ValidationService> reference=new ReferenceConfig<ValidationService>();
    reference.setApplication(new ApplicationConfig("validation-consumer"));
    reference.setInterface(ValidationService.class);
    reference.setUrl("dubbo://127.0.0.1:29582?scope=remote&validation=true");
    ValidationService validationService=reference.get();
    try {
      ValidationParameter parameter=new ValidationParameter();
      parameter.setName("liangfei");
      parameter.setEmail("liangfei@liang.fei");
      parameter.setAge(50);
      parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
      parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
      validationService.save(parameter);
      try {
        parameter=new ValidationParameter();
        parameter.setName("l");
        parameter.setEmail("liangfei@liang.fei");
        parameter.setAge(50);
        parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
        parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
        validationService.save(parameter);
        Assert.fail();
      }
 catch (      ConstraintViolationException ve) {
        Set<ConstraintViolation<?>> violations=ve.getConstraintViolations();
        Assert.assertNotNull(violations);
      }
      try {
        parameter=new ValidationParameter();
        parameter.setName("liangfei");
        parameter.setAge(50);
        parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
        parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
        validationService.save(parameter);
        Assert.fail();
      }
 catch (      ConstraintViolationException ve) {
        Set<ConstraintViolation<?>> violations=ve.getConstraintViolations();
        Assert.assertNotNull(violations);
      }
      try {
        parameter=new ValidationParameter();
        parameter.setName("liangfei");
        parameter.setAge(50);
        parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
        parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
        validationService.relatedQuery(parameter);
        Assert.fail();
      }
 catch (      ConstraintViolationException ve) {
        Set<ConstraintViolation<?>> violations=ve.getConstraintViolations();
        Assert.assertEquals(violations.size(),2);
      }
      try {
        parameter=new ValidationParameter();
        validationService.save(parameter);
        Assert.fail();
      }
 catch (      ConstraintViolationException ve) {
        Set<ConstraintViolation<?>> violations=ve.getConstraintViolations();
        Assert.assertTrue(violations.size() == 3);
        Assert.assertNotNull(violations);
      }
      validationService.delete(2,"abc");
      try {
        validationService.delete(2,"a");
        Assert.fail();
      }
 catch (      ConstraintViolationException ve) {
        Set<ConstraintViolation<?>> violations=ve.getConstraintViolations();
        Assert.assertNotNull(violations);
        Assert.assertEquals(1,violations.size());
      }
      try {
        validationService.delete(0,"abc");
        Assert.fail();
      }
 catch (      ConstraintViolationException ve) {
        Set<ConstraintViolation<?>> violations=ve.getConstraintViolations();
        Assert.assertNotNull(violations);
        Assert.assertEquals(1,violations.size());
      }
      try {
        validationService.delete(2,null);
        Assert.fail();
      }
 catch (      ConstraintViolationException ve) {
        Set<ConstraintViolation<?>> violations=ve.getConstraintViolations();
        Assert.assertNotNull(violations);
        Assert.assertEquals(1,violations.size());
      }
      try {
        validationService.delete(0,null);
        Assert.fail();
      }
 catch (      ConstraintViolationException ve) {
        Set<ConstraintViolation<?>> violations=ve.getConstraintViolations();
        Assert.assertNotNull(violations);
        Assert.assertEquals(2,violations.size());
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
