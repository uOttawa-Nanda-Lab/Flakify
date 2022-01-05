@Deployment public void testFailingScript(){
  Exception expectedException=null;
  try {
    runtimeService.startProcessInstanceByKey("failingScript");
  }
 catch (  Exception e) {
    expectedException=e;
  }
  verifyExceptionInStacktrace(expectedException,MissingPropertyException.class);
}
