public void testLocalDateTimeVariable(){
  Calendar todayCal=new GregorianCalendar();
  int todayYear=todayCal.get(Calendar.YEAR);
  int todayMonth=todayCal.get(Calendar.MONTH);
  int todayDate=todayCal.get(Calendar.DAY_OF_MONTH);
  DateTime date1=(DateTime)runtimeService.getVariable(processInstanceId,"datetimeVar1");
  assertEquals(todayYear,date1.getYear());
  assertEquals(todayMonth + 1,date1.getMonthOfYear());
  assertEquals(todayDate,date1.getDayOfMonth());
  date1=new DateTime(2010,11,10,10,15);
  runtimeService.setVariable(processInstanceId,"datetimeVar1",date1);
  date1=(DateTime)runtimeService.getVariable(processInstanceId,"datetimeVar1");
  assertEquals(2010,date1.getYear());
  assertEquals(11,date1.getMonthOfYear());
  assertEquals(10,date1.getDayOfMonth());
  assertEquals(10,date1.getHourOfDay());
  assertEquals(15,date1.getMinuteOfHour());
  DateTime queryDate=new DateTime(2010,11,10,9,15);
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().variableValueGreaterThan("datetimeVar1",queryDate).singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstanceId,processInstance.getId());
  queryDate=new DateTime(2010,11,10,10,15);
  processInstance=runtimeService.createProcessInstanceQuery().variableValueGreaterThan("datetimeVar1",queryDate).singleResult();
  assertNull(processInstance);
  processInstance=runtimeService.createProcessInstanceQuery().variableValueGreaterThanOrEqual("datetimeVar1",queryDate).singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstanceId,processInstance.getId());
}
