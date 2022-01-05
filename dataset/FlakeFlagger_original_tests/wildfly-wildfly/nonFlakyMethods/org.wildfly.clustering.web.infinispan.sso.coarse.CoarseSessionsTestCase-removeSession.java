@Test public void removeSession(){
  String deployment="deployment";
  when(this.map.remove(deployment)).thenReturn("id");
  this.sessions.removeSession(deployment);
  verify(this.mutator).mutate();
  reset(this.map,this.mutator);
  when(this.map.remove(deployment)).thenReturn(null);
  this.sessions.removeSession(deployment);
  verify(this.mutator,never()).mutate();
}
