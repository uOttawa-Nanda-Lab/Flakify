public void testTableListPB() throws IOException, JAXBException {
  Response response=client.get("/",MIMETYPE_PROTOBUF);
  assertEquals(response.getCode(),200);
  TableListModel model=new TableListModel();
  model.getObjectFromMessage(response.getBody());
  checkTableList(model);
}
