@Test public void postActivate() throws ClassNotFoundException, IOException {
  PassivationListener<Object> listener=mock(PassivationListener.class);
  MarshalledValue<Map<String,Object>,MarshallingContext> value=mock(MarshalledValue.class);
  Map<String,Object> beans=mock(Map.class);
  String id="id";
  Object bean=new Object();
  this.group.postActivate(id,null);
  verifyZeroInteractions(this.entry);
  when(this.entry.getBeans()).thenReturn(value);
  when(value.get(this.context)).thenReturn(beans);
  when(beans.get(id)).thenReturn(bean);
  this.group.postActivate(id,listener);
  verify(listener).postActivate(bean);
}
