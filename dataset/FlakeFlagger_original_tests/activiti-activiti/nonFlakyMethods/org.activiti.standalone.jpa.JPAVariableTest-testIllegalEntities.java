@Deployment public void testIllegalEntities(){
  setupIllegalJPAEntities();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("compoundIdJPAEntity",compoundIdJPAEntity);
  try {
    runtimeService.startProcessInstanceByKey("JPAVariableProcessExceptions",variables);
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Cannot find field or method with annotation @Id on class",ae.getMessage());
    assertTextPresent("only single-valued primary keys are supported on JPA-entities",ae.getMessage());
  }
  variables=new HashMap<String,Object>();
  variables.put("nullValueEntity",new FieldAccessJPAEntity());
  try {
    runtimeService.startProcessInstanceByKey("JPAVariableProcessExceptions",variables);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Value of primary key for JPA-Entity cannot be null",ae.getMessage());
  }
  variables=new HashMap<String,Object>();
  IllegalIdClassJPAEntity illegalIdTypeEntity=new IllegalIdClassJPAEntity();
  illegalIdTypeEntity.setId(Calendar.getInstance());
  variables.put("illegalTypeId",illegalIdTypeEntity);
  try {
    runtimeService.startProcessInstanceByKey("JPAVariableProcessExceptions",variables);
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Unsupported Primary key type for JPA-Entity",ae.getMessage());
  }
  variables=new HashMap<String,Object>();
  FieldAccessJPAEntity nonPersistentEntity=new FieldAccessJPAEntity();
  nonPersistentEntity.setId(9999L);
  variables.put("nonPersistentEntity",nonPersistentEntity);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("JPAVariableProcessExceptions",variables);
  try {
    runtimeService.getVariable(processInstance.getId(),"nonPersistentEntity");
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Entity does not exist: " + FieldAccessJPAEntity.class.getName() + " - 9999",ae.getMessage());
  }
}
