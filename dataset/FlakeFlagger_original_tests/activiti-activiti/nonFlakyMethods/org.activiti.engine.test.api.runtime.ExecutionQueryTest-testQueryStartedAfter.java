@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryStartedAfter() throws Exception {
  Calendar calendar=new GregorianCalendar();
  calendar.set(Calendar.YEAR,2030);
  calendar.set(Calendar.MONTH,8);
  calendar.set(Calendar.DAY_OF_MONTH,30);
  calendar.set(Calendar.HOUR_OF_DAY,12);
  calendar.set(Calendar.MINUTE,0);
  calendar.set(Calendar.SECOND,0);
  calendar.set(Calendar.MILLISECOND,0);
  Date noon=calendar.getTime();
  processEngineConfiguration.getClock().setCurrentTime(noon);
  calendar.add(Calendar.HOUR_OF_DAY,-1);
  Date hourEarlier=calendar.getTime();
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<Execution> executions=runtimeService.createExecutionQuery().startedAfter(hourEarlier).list();
  assertEquals(2,executions.size());
}
