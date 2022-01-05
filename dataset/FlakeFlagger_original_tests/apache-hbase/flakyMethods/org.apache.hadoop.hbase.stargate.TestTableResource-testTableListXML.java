public void testTableListXML() throws IOException, JAXBException {
  Response response=client.get("/",MIMETYPE_XML);
  assertEquals(response.getCode(),200);
  TableListModel model=(TableListModel)context.createUnmarshaller().unmarshal(new ByteArrayInputStream(response.getBody()));
  checkTableList(model);
}
