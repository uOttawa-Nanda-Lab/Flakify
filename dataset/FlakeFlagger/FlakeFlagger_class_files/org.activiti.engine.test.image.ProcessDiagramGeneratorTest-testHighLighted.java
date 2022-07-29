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

    @Deployment public void testHighLighted() throws Exception{ProcessDiagramGenerator imageGenerator=new DefaultProcessDiagramGenerator();String activityFontName=imageGenerator.getDefaultActivityFontName();String labelFontName=imageGenerator.getDefaultLabelFontName();String annotationFontName=imageGenerator.getDefaultAnnotationFontName();runtimeService.startProcessInstanceByKey("myProcess");List<Task> tasks=taskService.createTaskQuery().list();for (Task task:tasks){taskService.complete(task.getId());}Task task=taskService.createTaskQuery().taskDefinitionKey("usertask4").singleResult();taskService.complete(task.getId());List<String> activityIds=runtimeService.getActiveActivityIds(task.getProcessInstanceId());InputStream diagram=imageGenerator.generateDiagram(repositoryService.getBpmnModel(task.getProcessDefinitionId()),activityIds);assertNotNull(diagram);List<String> highLightedFlows=Arrays.asList("flow1","flow2","flow3","flow4","flow5","flow6");diagram=imageGenerator.generateDiagram(repositoryService.getBpmnModel(task.getProcessDefinitionId()),activityIds,highLightedFlows);assertNotNull(diagram);diagram=imageGenerator.generateDiagram(repositoryService.getBpmnModel(task.getProcessDefinitionId()),activityIds,highLightedFlows,activityFontName,labelFontName,annotationFontName);assertNotNull(diagram);}

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
