@Test public void getSession(){
  String expected="id";
  String deployment="deployment1";
  String missingDeployment="deployment2";
  when(this.map.get(deployment)).thenReturn(expected);
  when(this.map.get(missingDeployment)).thenReturn(null);
  assertSame(expected,this.sessions.getSession(deployment));
  assertNull(this.sessions.getSession(missingDeployment));
  verify(this.mutator,never()).mutate();
}
