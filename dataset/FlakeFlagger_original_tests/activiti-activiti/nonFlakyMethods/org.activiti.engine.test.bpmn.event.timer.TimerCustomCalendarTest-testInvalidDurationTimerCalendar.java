@Deployment public void testInvalidDurationTimerCalendar(){
  try {
    this.runtimeService.startProcessInstanceByKey("testCustomDurationCalendar");
    fail("Activiti exception expected - calendar not found");
  }
 catch (  ActivitiException e) {
    assertThat(e.getMessage(),containsString("INVALID does not exist"));
  }
}
