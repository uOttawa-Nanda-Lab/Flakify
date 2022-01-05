@Test public void testMultiStagePlan(){
  RoleCommandOrder.initialize();
  RoleCommandOrder rco=new RoleCommandOrder();
  RoleGraph rg=new RoleGraph(rco);
  long now=System.currentTimeMillis();
  Stage stage=StageUtils.getATestStage(1,1,"host1");
  stage.addHostRoleExecutionCommand("host2",Role.HBASE_MASTER,RoleCommand.START,new ServiceComponentHostStartEvent("HBASE_MASTER","host2",now,new HashMap<String,String>()),"cluster1","HBASE");
  stage.addHostRoleExecutionCommand("host3",Role.ZOOKEEPER_SERVER,RoleCommand.START,new ServiceComponentHostStartEvent("ZOOKEEPER_SERVER","host3",now,new HashMap<String,String>()),"cluster1","ZOOKEEPER");
  System.out.println(stage.toString());
  rg.build(stage);
  System.out.println(rg.stringifyGraph());
  List<Stage> outStages=rg.getStages();
  for (  Stage s : outStages) {
    System.out.println(s.toString());
  }
  assertEquals(3,outStages.size());
}
