@Test public void invalidate(){
  this.httpSession.invalidate();
  verifyZeroInteractions(this.session);
}
