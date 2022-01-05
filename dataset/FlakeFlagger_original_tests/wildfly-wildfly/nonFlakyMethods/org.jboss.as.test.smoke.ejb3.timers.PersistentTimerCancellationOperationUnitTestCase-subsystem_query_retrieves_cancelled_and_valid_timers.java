@Test public void subsystem_query_retrieves_cancelled_and_valid_timers() throws Exception {
  final ModelNode address=new ModelNode();
  address.add("deployment",NAME);
  address.add("subsystem","ejb3");
  address.add("singleton-bean",TimerStartup.class.getSimpleName());
  address.add("service","timer-service");
  address.protect();
  final ModelNode operation=new ModelNode();
  operation.get(ModelDescriptionConstants.OP).set(ModelDescriptionConstants.READ_RESOURCE_OPERATION);
  operation.get(ModelDescriptionConstants.OP_ADDR).set(address);
  operation.get(ModelDescriptionConstants.INCLUDE_RUNTIME).set(true);
  operation.get(ModelDescriptionConstants.RECURSIVE).set(true);
  ModelNode node=managementClient.getControllerClient().execute(operation);
  assertThat(node.get(ModelDescriptionConstants.OUTCOME).asString(),is("success"));
  ModelNode timersNode=node.get(ModelDescriptionConstants.RESULT).get("timer");
  Set<String> timerIDs=timersNode.keys();
  assertThat(timerIDs.size(),is(2));
  List<ModelNode> timers=timerIDs.stream().map(id -> timersNode.get(id)).collect(Collectors.toList());
  List<ModelNode> nonCancelledTimers=timers.stream().filter(n -> n.hasDefined("info")).collect(Collectors.toList());
  assertThat(nonCancelledTimers.size(),is(1));
  List<ModelNode> cancelledTimers=timers.stream().filter(n -> !n.hasDefined("info")).collect(Collectors.toList());
  assertThat(cancelledTimers.size(),is(1));
}
