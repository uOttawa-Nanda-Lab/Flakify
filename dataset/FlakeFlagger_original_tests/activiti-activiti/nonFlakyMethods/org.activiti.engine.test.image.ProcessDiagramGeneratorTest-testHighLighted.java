@Deployment public void testHighLighted() throws Exception {
  ProcessDiagramGenerator imageGenerator=new DefaultProcessDiagramGenerator();
  String activityFontName=imageGenerator.getDefaultActivityFontName();
  String labelFontName=imageGenerator.getDefaultLabelFontName();
  String annotationFontName=imageGenerator.getDefaultAnnotationFontName();
  runtimeService.startProcessInstanceByKey("myProcess");
  List<Task> tasks=taskService.createTaskQuery().list();
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  Task task=taskService.createTaskQuery().taskDefinitionKey("usertask4").singleResult();
  taskService.complete(task.getId());
  List<String> activityIds=runtimeService.getActiveActivityIds(task.getProcessInstanceId());
  InputStream diagram=imageGenerator.generateDiagram(repositoryService.getBpmnModel(task.getProcessDefinitionId()),activityIds);
  assertNotNull(diagram);
  List<String> highLightedFlows=Arrays.asList("flow1","flow2","flow3","flow4","flow5","flow6");
  diagram=imageGenerator.generateDiagram(repositoryService.getBpmnModel(task.getProcessDefinitionId()),activityIds,highLightedFlows);
  assertNotNull(diagram);
  diagram=imageGenerator.generateDiagram(repositoryService.getBpmnModel(task.getProcessDefinitionId()),activityIds,highLightedFlows,activityFontName,labelFontName,annotationFontName);
  assertNotNull(diagram);
}
