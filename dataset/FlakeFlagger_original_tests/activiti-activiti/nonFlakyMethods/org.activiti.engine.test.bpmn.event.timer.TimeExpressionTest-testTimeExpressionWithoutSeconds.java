@Deployment(resources={"org/activiti/engine/test/bpmn/event/timer/IntermediateTimerEventTest.testExpression.bpmn20.xml"}) public void testTimeExpressionWithoutSeconds() throws Exception {
  Date dt=new Date();
  Date dueDate=testExpression(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(dt));
  assertEquals(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(dt),new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(dueDate));
}
