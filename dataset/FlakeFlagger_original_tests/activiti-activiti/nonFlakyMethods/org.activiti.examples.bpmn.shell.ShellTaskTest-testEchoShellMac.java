@Deployment public void testEchoShellMac(){
  if (osType == OsType.MAC) {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("echoShellMac");
    String st=(String)runtimeService.getVariable(pi.getId(),"resultVar");
    assertNotNull(st);
    assertTrue(st.startsWith("EchoTest"));
  }
}
