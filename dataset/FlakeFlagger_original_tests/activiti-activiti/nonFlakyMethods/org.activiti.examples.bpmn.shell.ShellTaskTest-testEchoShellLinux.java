@Deployment public void testEchoShellLinux(){
  if (osType == OsType.LINUX) {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("echoShellLinux");
    String st=(String)runtimeService.getVariable(pi.getId(),"resultVar");
    assertNotNull(st);
    assertTrue(st.startsWith("EchoTest"));
  }
}
