@Deployment public void testUpdateJPAEntityValues(){
  setupJPAEntityToUpdate();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("entityToUpdate",entityToUpdate);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("UpdateJPAValuesProcess",variables);
  Object updatedEntity=runtimeService.getVariable(processInstance.getId(),"entityToUpdate");
  assertTrue(updatedEntity instanceof FieldAccessJPAEntity);
  assertEquals("updatedValue",((FieldAccessJPAEntity)updatedEntity).getValue());
}
