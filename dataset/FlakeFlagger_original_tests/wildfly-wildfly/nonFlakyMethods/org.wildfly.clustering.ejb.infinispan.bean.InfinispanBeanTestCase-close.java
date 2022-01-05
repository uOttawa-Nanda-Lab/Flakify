@Test public void close(){
  when(this.entry.getLastAccessedTime()).thenReturn(null);
  when(this.group.isCloseable()).thenReturn(false);
  this.bean.close();
  verify(this.entry).setLastAccessedTime(ArgumentMatchers.<Date>any());
  verify(this.mutator,never()).mutate();
  verify(this.group,never()).close();
  reset(this.entry,this.mutator,this.group);
  when(this.entry.getLastAccessedTime()).thenReturn(new Date());
  when(this.group.isCloseable()).thenReturn(true);
  this.bean.close();
  verify(this.entry).setLastAccessedTime(ArgumentMatchers.<Date>any());
  verify(this.mutator).mutate();
  verify(this.group).close();
}
