@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryStartedBefore() throws Exception {
  Calendar calendar=new GregorianCalendar();
  calendar.set(Calendar.YEAR,2010);
  calendar.set(Calendar.MONTH,8);
  calendar.set(Calendar.DAY_OF_MONTH,30);
  calendar.set(Calendar.HOUR_OF_DAY,12);
  calendar.set(Calendar.MINUTE,0);
  calendar.set(Calendar.SECOND,0);
  calendar.set(Calendar.MILLISECOND,0);
  Date noon=calendar.getTime();
  processEngineConfiguration.getClock().setCurrentTime(noon);
  calendar.add(Calendar.HOUR_OF_DAY,1);
  Date hourLater=calendar.getTime();
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().startedBefore(hourLater).list();
  assertEquals(1,processInstances.size());
}
