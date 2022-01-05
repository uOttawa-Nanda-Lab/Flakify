@Test public void getPath(){
  DeploymentInfo info=new DeploymentInfo();
  String expected="";
  info.setContextPath(expected);
  when(this.deployment.getDeploymentInfo()).thenReturn(info);
  String result=this.context.getPath();
  assertSame(expected,result);
}
