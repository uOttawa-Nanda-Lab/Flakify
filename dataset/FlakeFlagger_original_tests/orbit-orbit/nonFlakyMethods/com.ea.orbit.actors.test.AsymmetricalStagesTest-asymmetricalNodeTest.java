@Test public void asymmetricalNodeTest() throws ExecutionException, InterruptedException, NoSuchFieldException, IllegalAccessException {
  OrbitStage stage1=createStage(SomeActor.class,SomePlayer.class);
  OrbitStage stage2=createStage(SomeActor.class,SomeMatch.class);
  final List<Task<String>> tasksA=new ArrayList<>();
  final List<Task<String>> tasksP=new ArrayList<>();
  final List<Task<String>> tasksM=new ArrayList<>();
  stage1.getReference(ISomeMatch.class,"100_000").getNodeId().join();
  stage2.getReference(ISomePlayer.class,"100_000").getNodeId().join();
  for (int i=0; i < 25; i++) {
    tasksA.add(stage1.getReference(ISomeActor.class,"100_" + i).getNodeId());
    tasksM.add(stage1.getReference(ISomeMatch.class,"100_" + i).getNodeId());
    tasksP.add(stage1.getReference(ISomePlayer.class,"200_" + i).getNodeId());
    tasksA.add(stage2.getReference(ISomeActor.class,"100_" + i).getNodeId());
    tasksM.add(stage2.getReference(ISomeMatch.class,"300_" + i).getNodeId());
    tasksP.add(stage2.getReference(ISomePlayer.class,"400_" + i).getNodeId());
  }
  final Set<String> setA=tasksA.stream().map(x -> x.join()).collect(Collectors.toSet());
  final Set<String> setM=tasksM.stream().map(x -> x.join()).collect(Collectors.toSet());
  final Set<String> setP=tasksP.stream().map(x -> x.join()).collect(Collectors.toSet());
  assertEquals(2,setA.size());
  assertEquals(1,setM.size());
  assertEquals(1,setP.size());
  assertNotEquals(setM,setP);
}
