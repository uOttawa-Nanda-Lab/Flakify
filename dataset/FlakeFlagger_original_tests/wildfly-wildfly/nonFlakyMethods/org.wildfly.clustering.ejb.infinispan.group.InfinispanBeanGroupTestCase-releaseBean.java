@Test public void releaseBean() throws ClassNotFoundException, IOException {
  PassivationListener<Object> listener=mock(PassivationListener.class);
  MarshalledValue<Map<String,Object>,MarshallingContext> value=mock(MarshalledValue.class);
  Map<String,Object> beans=mock(Map.class);
  String id="id";
  Object bean=new Object();
  when(this.entry.decrementUsage(id)).thenReturn(1);
  boolean result=this.group.releaseBean(id,listener);
  Assert.assertFalse(result);
  verifyZeroInteractions(listener);
  verify(this.entry,never()).getBeans();
  when(this.entry.decrementUsage(id)).thenReturn(0);
  when(this.entry.getBeans()).thenReturn(value);
  when(value.get(this.context)).thenReturn(beans);
  when(beans.get(id)).thenReturn(bean);
  result=this.group.releaseBean(id,listener);
  Assert.assertTrue(result);
  verify(listener).prePassivate(bean);
}
