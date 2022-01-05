@Test public void testParseMessagedDefinitionWithExtension(){
  BpmnParse bpmnParseMock=Mockito.mock(BpmnParse.class);
  MessageEventDefinition messageEventDefinitionMock=Mockito.mock(MessageEventDefinition.class);
  BpmnModel bpmnModelMock=Mockito.mock(BpmnModel.class);
  Message messageMock=Mockito.mock(Message.class);
  @SuppressWarnings("unchecked") Map<String,List<ExtensionElement>> extensionElementMap=Mockito.mock(Map.class);
  Mockito.when(bpmnParseMock.getBpmnModel()).thenReturn(bpmnModelMock);
  Mockito.when(messageEventDefinitionMock.getMessageRef()).thenReturn("messageId");
  Mockito.when(bpmnModelMock.containsMessageId("messageId")).thenReturn(true);
  Mockito.when(bpmnModelMock.getMessage("messageId")).thenReturn(messageMock);
  Mockito.when(messageMock.getName()).thenReturn("MessageWithExtensionElements");
  Mockito.when(messageMock.getExtensionElements()).thenReturn(extensionElementMap);
  MessageEventDefinitionParseHandler handler=new MessageEventDefinitionParseHandler();
  handler.parse(bpmnParseMock,messageEventDefinitionMock);
  Mockito.verify(messageEventDefinitionMock).setMessageRef("MessageWithExtensionElements");
  Mockito.verify(messageEventDefinitionMock).setExtensionElements(extensionElementMap);
}
