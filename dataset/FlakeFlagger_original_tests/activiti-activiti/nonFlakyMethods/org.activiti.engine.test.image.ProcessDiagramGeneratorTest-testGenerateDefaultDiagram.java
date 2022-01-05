/** 
 * Test that when the diagram is generated for a model without graphic info then the default diagram image is returned or the ActivitiInterchangeInfoNotFoundException is thrown depending on the value of the generateDefaultDiagram parameter.
 */
@Deployment public void testGenerateDefaultDiagram() throws Exception {
  String id=repositoryService.createProcessDefinitionQuery().processDefinitionKey("fixSystemFailure").singleResult().getId();
  BpmnModel bpmnModel=repositoryService.getBpmnModel(id);
  ProcessDiagramGenerator imageGenerator=new DefaultProcessDiagramGenerator();
  String activityFontName=imageGenerator.getDefaultActivityFontName();
  String labelFontName=imageGenerator.getDefaultLabelFontName();
  String annotationFontName=imageGenerator.getDefaultAnnotationFontName();
  try (final InputStream resourceStream=imageGenerator.generateDiagram(bpmnModel,emptyList(),emptyList(),activityFontName,labelFontName,annotationFontName,true)){
    assertThat(resourceStream).isNotNull();
    byte[] diagram=IOUtils.toByteArray(resourceStream);
    assertThat(diagram).isNotNull();
    try (InputStream imageStream=getClass().getResourceAsStream(imageGenerator.getDefaultDiagramImageFileName())){
      assertThat(diagram).isEqualTo(IOUtils.toByteArray(imageStream));
    }
   }
   assertThatExceptionOfType(ActivitiInterchangeInfoNotFoundException.class).isThrownBy(() -> imageGenerator.generateDiagram(bpmnModel,emptyList(),emptyList(),activityFontName,labelFontName,annotationFontName,false)).withMessage("No interchange information found.");
  assertThatExceptionOfType(ActivitiImageException.class).isThrownBy(() -> imageGenerator.generateDiagram(bpmnModel,emptyList(),emptyList(),activityFontName,labelFontName,annotationFontName,true,"invalid-file-name")).withMessage("Error occurred while getting default diagram image from file: invalid-file-name");
}
