@Test public void testSingleStagePlan(){
  RoleCommandOrder.initialize();
  RoleCommandOrder rco=new RoleCommandOrder();
  RoleGraph rg=new RoleGraph(rco);
  String hostname="dummy";
  Stage stage=StageUtils.getATestStage(1,1,hostname);
  rg.build(stage);
  List<Stage> outStages=rg.getStages();
  for (  Stage s : outStages) {
    System.out.println(s.toString());
  }
  assertEquals(1,outStages.size());
  assertEquals(stage.getExecutionCommands(hostname),outStages.get(0).getExecutionCommands(hostname));
}
