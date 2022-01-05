@Test public void getBean() throws ClassNotFoundException, IOException {
  PassivationListener<Object> listener=mock(PassivationListener.class);
  MarshalledValue<Map<String,Object>,MarshallingContext> value=mock(MarshalledValue.class);
  Map<String,Object> beans=mock(Map.class);
  String id="id";
  Object bean=new Object();
  when(this.entry.getBeans()).thenReturn(value);
  when(value.get(this.context)).thenReturn(beans);
  when(beans.get(id)).thenReturn(bean);
  when(this.entry.incrementUsage(id)).thenReturn(1);
  Object result=this.group.getBean(id,listener);
  Assert.assertSame(bean,result);
  verifyZeroInteractions(listener);
  when(this.entry.incrementUsage(id)).thenReturn(0);
  result=this.group.getBean(id,listener);
  Assert.assertSame(bean,result);
  verify(listener).postActivate(bean);
}
