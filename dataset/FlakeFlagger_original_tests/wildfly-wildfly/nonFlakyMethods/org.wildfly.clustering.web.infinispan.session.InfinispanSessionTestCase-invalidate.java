@SuppressWarnings("unchecked") @Test public void invalidate(){
  when(this.metaData.invalidate()).thenReturn(true);
  this.session.invalidate();
  verify(this.remover).remove(this.id);
  reset(this.remover);
  when(this.metaData.invalidate()).thenReturn(false);
  this.session.invalidate();
  verify(this.remover,never()).remove(this.id);
}
