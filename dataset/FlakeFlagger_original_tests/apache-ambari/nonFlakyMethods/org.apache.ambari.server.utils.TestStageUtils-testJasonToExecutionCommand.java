@Test public void testJasonToExecutionCommand() throws JsonGenerationException, JsonMappingException, JAXBException, IOException {
  Stage s=StageUtils.getATestStage(1,2,"host1");
  ExecutionCommand cmd=s.getExecutionCommands("host1").get(0).getExecutionCommand();
  String json=StageUtils.jaxbToString(cmd);
  ExecutionCommand cmdDes=StageUtils.stringToExecutionCommand(json);
  assertEquals(cmd.toString(),cmdDes.toString());
  assertEquals(cmd,cmdDes);
}
