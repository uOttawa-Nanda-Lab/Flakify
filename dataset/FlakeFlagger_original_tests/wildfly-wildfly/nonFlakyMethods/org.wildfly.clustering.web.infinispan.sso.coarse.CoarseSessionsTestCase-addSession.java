@Test public void addSession(){
  String id="id";
  String deployment="deployment";
  when(this.map.put(deployment,id)).thenReturn(null);
  this.sessions.addSession(deployment,id);
  verify(this.mutator).mutate();
  reset(this.map,this.mutator);
  when(this.map.put(deployment,id)).thenReturn(id);
  this.sessions.addSession(deployment,id);
  verify(this.mutator,never()).mutate();
}
