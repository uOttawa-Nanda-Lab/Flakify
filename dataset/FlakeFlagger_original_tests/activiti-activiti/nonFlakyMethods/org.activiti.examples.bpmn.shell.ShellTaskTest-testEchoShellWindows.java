@Deployment public void testEchoShellWindows(){
  if (osType == OsType.WINDOWS) {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("echoShellWindows");
    String st=(String)runtimeService.getVariable(pi.getId(),"resultVar");
    assertNotNull(st);
    assertTrue(st.startsWith("EchoTest"));
  }
}
