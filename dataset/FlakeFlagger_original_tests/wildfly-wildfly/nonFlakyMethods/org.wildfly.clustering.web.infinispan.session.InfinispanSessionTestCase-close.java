@Test public void close(){
  when(this.metaData.isValid()).thenReturn(true);
  this.session.close();
  verify(this.attributes).close();
  verify(this.metaData).setLastAccessedTime(any(Instant.class));
  reset(this.metaData,this.attributes);
  when(this.metaData.isValid()).thenReturn(false);
  this.session.close();
  this.session.close();
  verify(this.attributes,never()).close();
  verify(this.metaData,never()).setLastAccessedTime(any(Instant.class));
}
