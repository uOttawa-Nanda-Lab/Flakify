@SuppressWarnings("serial") @Test public void testGetActions() throws Exception {
  Set<ActionResponse> responses=controller.getActions(new HashSet<ActionRequest>(){
{
      add(new ActionRequest(null,"HDFS",null,null));
    }
  }
);
  assertFalse(responses.isEmpty());
  assertEquals(1,responses.size());
  ActionResponse response=responses.iterator().next();
  assertEquals(Role.HDFS_SERVICE_CHECK.name(),response.getActionName());
}
