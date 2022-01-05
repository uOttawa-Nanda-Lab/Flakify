@Deployment(resources={"org/activiti/standalone/jpa/JPAVariableTest.testStoreJPAEntityAsVariable.bpmn20.xml"}) public void testEnhancedEntityListVariables() throws Exception {
  if (FieldAccessJPAEntity.class == fieldEntity.getClass() || PropertyAccessJPAEntity.class == propertyEntity.getClass()) {
    logger.warn("Entity enhancement is not used");
    return;
  }
  Map<String,Object> params=new HashMap<String,Object>();
  params.put("list1",Arrays.asList(fieldEntity,fieldEntity));
  params.put("list2",Arrays.asList(propertyEntity,propertyEntity));
  ProcessInstance instance=processEngine.getRuntimeService().startProcessInstanceByKey("JPAVariableProcess",params);
  Task task=getTask(instance);
  List list=(List)task.getProcessVariables().get("list1");
  assertTrue(list.size() == 2);
  assertTrue(list.get(0) instanceof FieldAccessJPAEntity);
  assertTrue(list.get(1) instanceof FieldAccessJPAEntity);
  list=(List)task.getProcessVariables().get("list2");
  assertTrue(list.size() == 2);
  assertTrue(list.get(0) instanceof PropertyAccessJPAEntity);
  assertTrue(list.get(1) instanceof PropertyAccessJPAEntity);
  params.putAll(Collections.singletonMap("list",Arrays.asList(fieldEntity,fieldEntity2)));
  instance=processEngine.getRuntimeService().startProcessInstanceByKey("JPAVariableProcess",params);
  task=getTask(instance);
  list=(List)task.getProcessVariables().get("list");
  assertTrue(list.size() == 2);
  assertTrue(list.get(0) instanceof FieldAccessJPAEntity);
  assertTrue(((FieldAccessJPAEntity)list.get(0)).getId().equals(1L));
  assertTrue(list.get(1) instanceof FieldAccessJPAEntity);
  assertTrue(((FieldAccessJPAEntity)list.get(1)).getId().equals(2L));
  params.putAll(Collections.singletonMap("list",Arrays.asList(fieldEntity2,fieldEntity)));
  instance=processEngine.getRuntimeService().startProcessInstanceByKey("JPAVariableProcess",params);
  task=getTask(instance);
  list=(List)task.getProcessVariables().get("list");
  assertTrue(list.size() == 2);
  assertTrue(list.get(0) instanceof FieldAccessJPAEntity);
  assertTrue(((FieldAccessJPAEntity)list.get(0)).getId().equals(2L));
  assertTrue(list.get(1) instanceof FieldAccessJPAEntity);
  assertTrue(((FieldAccessJPAEntity)list.get(1)).getId().equals(1L));
  try {
    params=new HashMap<String,Object>();
    params.put("list",Arrays.asList(fieldEntity,propertyEntity));
    instance=processEngine.getRuntimeService().startProcessInstanceByKey("JPAVariableProcess",params);
    fail();
  }
 catch (  Exception e) {
  }
}
