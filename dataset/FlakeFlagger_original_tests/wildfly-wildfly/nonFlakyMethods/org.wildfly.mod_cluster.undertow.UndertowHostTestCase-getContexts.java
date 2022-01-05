@Test public void getContexts(){
  Deployment deployment=mock(Deployment.class);
  DeploymentInfo info=new DeploymentInfo();
  String expectedPath="";
  info.setContextPath(expectedPath);
  HttpHandler handler=mock(HttpHandler.class);
  when(deployment.getDeploymentInfo()).thenReturn(info);
  this.undertowHost.registerDeployment(deployment,handler);
  Iterator<Context> result=this.host.getContexts().iterator();
  assertTrue(result.hasNext());
  Context context=result.next();
  assertSame(this.host,context.getHost());
  assertSame(expectedPath,context.getPath());
  assertFalse(result.hasNext());
}
