public void testNativeQuery(){
  assertEquals("ACT_RU_TASK",managementService.getTableName(Task.class));
  assertEquals("ACT_RU_TASK",managementService.getTableName(TaskEntity.class));
  assertEquals(12,taskService.createNativeTaskQuery().sql("SELECT * FROM " + managementService.getTableName(Task.class)).list().size());
  assertEquals(12,taskService.createNativeTaskQuery().sql("SELECT count(*) FROM " + managementService.getTableName(Task.class)).count());
  assertEquals(144,taskService.createNativeTaskQuery().sql("SELECT count(*) FROM ACT_RU_TASK T1, ACT_RU_TASK T2").count());
  assertEquals(1,taskService.createNativeTaskQuery().sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T1, "+ managementService.getTableName(VariableInstanceEntity.class)+ " V1 WHERE V1.TASK_ID_ = T1.ID_").count());
  List<Task> tasks=taskService.createNativeTaskQuery().sql("SELECT * FROM " + managementService.getTableName(Task.class) + " T1, "+ managementService.getTableName(VariableInstanceEntity.class)+ " V1 WHERE V1.TASK_ID_ = T1.ID_").list();
  assertEquals(1,tasks.size());
  assertEquals("gonzoTask",tasks.get(0).getName());
  assertEquals(12,taskService.createNativeTaskQuery().sql("SELECT DISTINCT T1.* FROM ACT_RU_TASK T1").list().size());
  assertEquals(1,taskService.createNativeTaskQuery().sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T WHERE T.NAME_ = 'gonzoTask'").count());
  assertEquals(1,taskService.createNativeTaskQuery().sql("SELECT * FROM " + managementService.getTableName(Task.class) + " T WHERE T.NAME_ = 'gonzoTask'").list().size());
  assertEquals(1,taskService.createNativeTaskQuery().sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T WHERE T.NAME_ = #{taskName}").parameter("taskName","gonzoTask").count());
}
