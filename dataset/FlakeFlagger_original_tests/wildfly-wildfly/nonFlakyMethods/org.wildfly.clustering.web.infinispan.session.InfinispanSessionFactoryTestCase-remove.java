@Test public void remove(){
  String id="id";
  when(this.metaDataFactory.remove(id)).thenReturn(false);
  this.factory.remove(id);
  verify(this.attributesFactory,never()).remove(id);
  when(this.metaDataFactory.remove(id)).thenReturn(true);
  this.factory.remove(id);
  verify(this.attributesFactory).remove(id);
}
