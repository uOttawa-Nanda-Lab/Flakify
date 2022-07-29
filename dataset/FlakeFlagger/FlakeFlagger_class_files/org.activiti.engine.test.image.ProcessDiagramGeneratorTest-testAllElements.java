package org.activiti.engine.test.image;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.exception.ActivitiImageException;
import org.activiti.image.exception.ActivitiInterchangeInfoNotFoundException;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.commons.io.IOUtils;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;

public class ProcessDiagramGeneratorTest extends PluggableActivitiTestCase {

    @Deployment public void testAllElements() throws Exception{ProcessDiagramGenerator imageGenerator=new DefaultProcessDiagramGenerator();String activityFontName=imageGenerator.getDefaultActivityFontName();String labelFontName=imageGenerator.getDefaultLabelFontName();String annotationFontName=imageGenerator.getDefaultAnnotationFontName();String id=repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess").singleResult().getId();BpmnModel bpmnModel=repositoryService.getBpmnModel(id);try (final InputStream resourceStream=imageGenerator.generateDiagram(bpmnModel,activityFontName,labelFontName,annotationFontName)){SVGOMDocument svg=parseXml(resourceStream);List<String> startEventIdList=Arrays.asList("startevent1","errorstartevent1","signalstartevent1","messagestartevent1","timerstartevent1");checkDiagramElements(startEventIdList,svg);List<String> userTaskIdList=Arrays.asList("usertask1","usertask2","usertask3","usertask4","usertask5","usertask6","usertask7","usertask8","usertask9","usertask10","usertask11","usertask12","usertask13","usertask14","usertask15","usertask16","usertask17","usertask18","usertask19");checkDiagramElements(userTaskIdList,svg);List<String> scriptTaskIdList=Arrays.asList("scripttask1","scripttask2","scripttask3");checkDiagramElements(scriptTaskIdList,svg);List<String> otherTaskIdList=Arrays.asList("servicetask1","mailtask1","manualtask1","receivetask1","callactivity1");checkDiagramElements(otherTaskIdList,svg);List<String> intermediateEvent=Arrays.asList("timerintermediatecatchevent1","signalintermediatecatchevent1","messageintermediatecatchevent1","signalintermediatethrowevent1","compensationintermediatethrowevent1","noneintermediatethrowevent1");checkDiagramElements(intermediateEvent,svg);List<String> gatewayIdList=Arrays.asList("parallelgateway1","parallelgateway2","exclusivegateway1","exclusivegateway3","inclusivegateway1","inclusivegateway2","eventgateway1");checkDiagramElements(gatewayIdList,svg);List<String> containerIdList=Arrays.asList("subprocess1","eventsubprocess1","pool1","pool2","pool3","lane1","lane2","lane3","lane4");checkDiagramElements(containerIdList,svg);List<String> endEventIdList=Arrays.asList("errorendevent1","endevent1","endevent2","endevent3","endevent4","endevent5","endevent6","endevent7","endevent8","endevent9","endevent10","endevent11","endevent12");checkDiagramElements(endEventIdList,svg);} }

    private void checkDiagramElements(List<String> elementIdList, SVGOMDocument svg) {
        for (String elementId : elementIdList) {
            assertNotNull(svg.getElementById(elementId));
        }
    }

    private SVGOMDocument parseXml(InputStream resourceStream) throws Exception {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        return (SVGOMDocument) factory.createDocument(null, resourceStream);
    }
}
