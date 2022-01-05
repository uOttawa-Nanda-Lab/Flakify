@Deployment(resources={"org/activiti/engine/test/bpmn/event/timer/IntermediateTimerEventTest.testExpression.bpmn20.xml"}) public void testTimeExpressionComplete() throws Exception {
  Date dt=new Date();
  Date dueDate=testExpression(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(dt));
  assertEquals(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(dt),new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(dueDate));
}
