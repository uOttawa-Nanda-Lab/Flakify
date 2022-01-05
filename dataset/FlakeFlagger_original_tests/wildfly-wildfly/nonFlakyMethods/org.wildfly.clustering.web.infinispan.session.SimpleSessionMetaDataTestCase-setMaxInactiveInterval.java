@Test public void setMaxInactiveInterval(){
  Duration duration=Duration.ZERO;
  this.metaData.setMaxInactiveInterval(duration);
  verify(this.creationMetaData).setMaxInactiveInterval(duration);
}
