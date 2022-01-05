public void testStartProcessInstanceByKeyNullKey(){
  try {
    runtimeService.startProcessInstanceByKey(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
