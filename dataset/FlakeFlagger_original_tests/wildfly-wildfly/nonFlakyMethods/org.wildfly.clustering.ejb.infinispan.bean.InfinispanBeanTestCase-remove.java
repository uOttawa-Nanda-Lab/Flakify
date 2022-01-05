@Test public void remove(){
  RemoveListener<Object> listener=mock(RemoveListener.class);
  when(this.group.isCloseable()).thenReturn(false);
  this.bean.remove(listener);
  verify(this.remover).remove(this.id,listener);
  this.bean.remove(listener);
  verifyNoMoreInteractions(this.remover);
}
