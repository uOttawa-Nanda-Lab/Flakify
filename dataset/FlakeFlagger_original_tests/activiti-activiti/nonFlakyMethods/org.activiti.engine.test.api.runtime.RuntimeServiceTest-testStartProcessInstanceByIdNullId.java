public void testStartProcessInstanceByIdNullId(){
  try {
    runtimeService.startProcessInstanceById(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
