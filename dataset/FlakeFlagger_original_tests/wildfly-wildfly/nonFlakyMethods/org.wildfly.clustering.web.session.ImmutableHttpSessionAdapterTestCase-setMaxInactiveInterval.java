@Test public void setMaxInactiveInterval(){
  this.httpSession.setMaxInactiveInterval(10);
  verifyZeroInteractions(this.session);
}
