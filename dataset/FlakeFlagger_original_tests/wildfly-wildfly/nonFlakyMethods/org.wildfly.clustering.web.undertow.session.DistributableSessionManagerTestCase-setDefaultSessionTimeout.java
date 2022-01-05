@Test public void setDefaultSessionTimeout(){
  this.adapter.setDefaultSessionTimeout(10);
  verify(this.manager).setDefaultMaxInactiveInterval(Duration.ofSeconds(10L));
}
