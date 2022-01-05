@Test public void prePassivate() throws ClassNotFoundException, IOException {
  PassivationListener<Object> listener=mock(PassivationListener.class);
  MarshalledValue<Map<String,Object>,MarshallingContext> value=mock(MarshalledValue.class);
  Map<String,Object> beans=mock(Map.class);
  String id="id";
  Object bean=new Object();
  this.group.prePassivate(id,null);
  verifyZeroInteractions(this.entry);
  when(this.entry.getBeans()).thenReturn(value);
  when(value.get(this.context)).thenReturn(beans);
  when(beans.get(id)).thenReturn(bean);
  this.group.prePassivate(id,listener);
  verify(listener).prePassivate(bean);
}
