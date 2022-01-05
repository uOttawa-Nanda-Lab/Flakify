@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.sequentialUserTasks.bpmn20.xml"}) public void testSequentialUserTasks(){
  checkSequentialUserTasks("miSequentialUserTasks",LOOP_COUNTER_KEY);
}
