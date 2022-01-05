@Test public void createImmutableSession(){
  Map.Entry<InfinispanSessionMetaData<Object>,Object> entry=mock(Map.Entry.class);
  SessionCreationMetaData creationMetaData=mock(SessionCreationMetaData.class);
  SessionAccessMetaData accessMetaData=mock(SessionAccessMetaData.class);
  InfinispanSessionMetaData<Object> metaDataValue=new InfinispanSessionMetaData<>(creationMetaData,accessMetaData,null);
  Object attributesValue=new Object();
  ImmutableSessionMetaData metaData=mock(ImmutableSessionMetaData.class);
  ImmutableSessionAttributes attributes=mock(ImmutableSessionAttributes.class);
  String id="id";
  when(entry.getKey()).thenReturn(metaDataValue);
  when(entry.getValue()).thenReturn(attributesValue);
  when(this.metaDataFactory.createImmutableSessionMetaData(id,metaDataValue)).thenReturn(metaData);
  when(this.attributesFactory.createImmutableSessionAttributes(id,attributesValue)).thenReturn(attributes);
  ImmutableSession result=this.factory.createImmutableSession(id,entry);
  assertSame(id,result.getId());
  assertSame(metaData,result.getMetaData());
  assertSame(attributes,result.getAttributes());
}
