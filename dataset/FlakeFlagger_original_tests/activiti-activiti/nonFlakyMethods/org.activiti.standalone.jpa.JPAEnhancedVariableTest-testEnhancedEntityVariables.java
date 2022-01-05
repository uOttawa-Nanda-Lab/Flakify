@Deployment(resources={"org/activiti/standalone/jpa/JPAVariableTest.testStoreJPAEntityAsVariable.bpmn20.xml"}) public void testEnhancedEntityVariables() throws Exception {
  if (FieldAccessJPAEntity.class == fieldEntity.getClass() || PropertyAccessJPAEntity.class == propertyEntity.getClass()) {
    logger.warn("Entity enhancement is not used");
    return;
  }
  Map<String,Object> params=new HashMap<String,Object>();
  params.put("fieldEntity",fieldEntity);
  params.put("propertyEntity",propertyEntity);
  ProcessInstance instance=processEngine.getRuntimeService().startProcessInstanceByKey("JPAVariableProcess",params);
  Task task=getTask(instance);
  for (  Map.Entry<String,Object> entry : task.getProcessVariables().entrySet()) {
    String name=entry.getKey();
    Object value=entry.getValue();
    if (name.equals("fieldEntity")) {
      assertTrue(value instanceof FieldAccessJPAEntity);
    }
 else     if (name.equals("propertyEntity")) {
      assertTrue(value instanceof PropertyAccessJPAEntity);
    }
 else {
      fail();
    }
  }
}
