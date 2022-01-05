@Test public void createSession(){
  Map.Entry<InfinispanSessionMetaData<Object>,Object> entry=mock(Map.Entry.class);
  SessionCreationMetaData creationMetaData=mock(SessionCreationMetaData.class);
  SessionAccessMetaData accessMetaData=mock(SessionAccessMetaData.class);
  Object localContext=new Object();
  InfinispanSessionMetaData<Object> metaDataValue=new InfinispanSessionMetaData<>(creationMetaData,accessMetaData,new AtomicReference<>(localContext));
  Object attributesValue=new Object();
  InvalidatableSessionMetaData metaData=mock(InvalidatableSessionMetaData.class);
  SessionAttributes attributes=mock(SessionAttributes.class);
  String id="id";
  when(entry.getKey()).thenReturn(metaDataValue);
  when(entry.getValue()).thenReturn(attributesValue);
  when(this.metaDataFactory.createSessionMetaData(id,metaDataValue)).thenReturn(metaData);
  when(this.attributesFactory.createSessionAttributes(id,attributesValue)).thenReturn(attributes);
  Session<Object> result=this.factory.createSession(id,entry);
  assertSame(id,result.getId());
  assertSame(metaData,result.getMetaData());
  assertSame(attributes,result.getAttributes());
  assertSame(localContext,result.getLocalContext());
}
