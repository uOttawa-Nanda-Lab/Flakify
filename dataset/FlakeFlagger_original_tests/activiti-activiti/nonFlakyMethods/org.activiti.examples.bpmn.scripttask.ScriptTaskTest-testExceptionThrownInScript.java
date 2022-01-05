@Deployment public void testExceptionThrownInScript(){
  Exception expectedException=null;
  try {
    runtimeService.startProcessInstanceByKey("failingScript");
  }
 catch (  Exception e) {
    expectedException=e;
  }
  verifyExceptionInStacktrace(expectedException,IllegalStateException.class);
}
