public void testDeployWithMissingWaypointsForSequenceflowInDiagramInterchange(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/repository/noWayPointsForSequenceFlowInDiagramInterchange.bpmn20.xml").deploy().getId();
    fail();
  }
 catch (  XMLException e) {
  }
}
