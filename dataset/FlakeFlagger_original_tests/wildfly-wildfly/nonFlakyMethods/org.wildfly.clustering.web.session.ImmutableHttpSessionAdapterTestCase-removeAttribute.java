@Test public void removeAttribute(){
  this.httpSession.removeAttribute("name");
  verifyZeroInteractions(this.session);
}
