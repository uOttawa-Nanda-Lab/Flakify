@Test public void invalidate(){
  this.sso.invalidate();
  verify(this.remover).remove(this.id);
}
