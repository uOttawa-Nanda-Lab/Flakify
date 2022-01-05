@Test public void setAttribute(){
  this.httpSession.setAttribute("name","value");
  verifyZeroInteractions(this.session);
}
