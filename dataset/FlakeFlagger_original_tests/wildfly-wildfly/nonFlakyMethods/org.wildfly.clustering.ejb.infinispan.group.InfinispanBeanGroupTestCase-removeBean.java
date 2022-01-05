@Test public void removeBean() throws ClassNotFoundException, IOException {
  MarshalledValue<Map<String,Object>,MarshallingContext> value=mock(MarshalledValue.class);
  Map<String,Object> beans=mock(Map.class);
  String id="id";
  Object bean=new Object();
  when(this.entry.getBeans()).thenReturn(value);
  when(value.get(this.context)).thenReturn(beans);
  when(beans.remove(id)).thenReturn(bean);
  Object result=this.group.removeBean(id);
  Assert.assertSame(bean,result);
}
