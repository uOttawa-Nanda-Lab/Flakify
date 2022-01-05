@Deployment(resources={"org/activiti/engine/test/bpmn/event/timer/IntermediateTimerEventTest.testExpression.bpmn20.xml"}) public void testTimeExpressionWithoutTime() throws Exception {
  Date dt=new Date();
  Date dueDate=testExpression(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
  assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(dt),new SimpleDateFormat("yyyy-MM-dd").format(dueDate));
}
