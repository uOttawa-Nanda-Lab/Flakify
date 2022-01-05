public void testLocalDateVariable(){
  Calendar todayCal=new GregorianCalendar();
  int todayYear=todayCal.get(Calendar.YEAR);
  int todayMonth=todayCal.get(Calendar.MONTH);
  int todayDate=todayCal.get(Calendar.DAY_OF_MONTH);
  LocalDate date1=(LocalDate)runtimeService.getVariable(processInstanceId,"localdateVar1");
  assertEquals(todayYear,date1.getYear());
  assertEquals(todayMonth + 1,date1.getMonthOfYear());
  assertEquals(todayDate,date1.getDayOfMonth());
  date1=new LocalDate(2010,11,10);
  runtimeService.setVariable(processInstanceId,"localdateVar1",date1);
  date1=(LocalDate)runtimeService.getVariable(processInstanceId,"localdateVar1");
  assertEquals(2010,date1.getYear());
  assertEquals(11,date1.getMonthOfYear());
  assertEquals(10,date1.getDayOfMonth());
  LocalDate queryDate=new LocalDate(2010,11,9);
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().variableValueGreaterThan("localdateVar1",queryDate).singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstanceId,processInstance.getId());
  queryDate=new LocalDate(2010,11,10);
  processInstance=runtimeService.createProcessInstanceQuery().variableValueGreaterThan("localdateVar1",queryDate).singleResult();
  assertNull(processInstance);
  processInstance=runtimeService.createProcessInstanceQuery().variableValueGreaterThanOrEqual("localdateVar1",queryDate).singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstanceId,processInstance.getId());
}
