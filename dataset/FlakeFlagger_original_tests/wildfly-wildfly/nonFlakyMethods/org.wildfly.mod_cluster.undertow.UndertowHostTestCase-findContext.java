@Test public void findContext(){
  Deployment deployment=mock(Deployment.class);
  DeploymentInfo info=new DeploymentInfo();
  String expectedPath="";
  info.setContextPath(expectedPath);
  HttpHandler handler=mock(HttpHandler.class);
  when(deployment.getDeploymentInfo()).thenReturn(info);
  this.undertowHost.registerDeployment(deployment,handler);
  Context result=this.host.findContext(expectedPath);
  assertSame(this.host,result.getHost());
  assertSame(expectedPath,result.getPath());
  result=this.host.findContext("unknown");
  assertNull(result);
}
