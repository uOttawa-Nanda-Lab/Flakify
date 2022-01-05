@Test public void getClusterName(){
  String expected="cluster";
  when(this.manager.getClusterName()).thenReturn(expected);
  String result=this.subject.getClusterName();
  assertSame(expected,result);
}
