@Deployment public void testThreeExecutionsArrivingInTerminateEndEvent(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("passed_QC",false);
  variableMap.put("has_bad_pixel_pattern",true);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("skybox_image_pull_request",variableMap);
  String processInstanceId=processInstance.getId();
  assertNotNull(processInstance);
  while (processInstance != null) {
    List<Execution> executionList=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
    String activityId="";
    for (    Execution execution : executionList) {
      activityId=execution.getActivityId();
      if (activityId == null || activityId.equalsIgnoreCase("quality_control_passed_gateway") || activityId.equalsIgnoreCase("parallelgateway1") || activityId.equalsIgnoreCase("catch_bad_pixel_signal") || activityId.equalsIgnoreCase("throw_bad_pixel_signal") || activityId.equalsIgnoreCase("has_bad_pixel_pattern") || activityId.equalsIgnoreCase("")) {
        continue;
      }
      runtimeService.trigger(execution.getId());
    }
    processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  }
  assertProcessEnded(processInstanceId);
  assertHistoricProcessInstanceDetails(processInstanceId);
}
