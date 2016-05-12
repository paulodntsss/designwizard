package tests.org.designwizard.design;

import java.util.Set;

import junit.framework.TestCase;

import org.designwizard.api.DesignWizard;
import org.designwizard.design.ClassNode;
import org.designwizard.design.FieldNode;
import org.designwizard.design.MethodNode;
import org.designwizard.exception.InexistentEntityException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO cadastrar nova issue
 * 
 * Test for Issue XX - Issue Name
 * Link: https://github.com/joaoarthurbm/designwizard/issues/XX
 *
 * @author Taciano Morais Silva <tacianosilva@gmail.com>
 */
public class GenericTypeExtractTest extends TestCase {

    private DesignWizard dw;
    private ClassNode genericTypeExamples;
    private ClassNode elementType;
    private ClassNode fieldNode;

    @Before
    public void setUp() throws Exception {
        String arquivoJar = "classes/tests/org/designwizard/design/mocks/generictypes/";
        dw = new DesignWizard(arquivoJar);
        genericTypeExamples = dw.getClass("tests.org.designwizard.design.mocks.generictypes.GenericTypesExamples");
        elementType = new ClassNode("tests.org.designwizard.design.mocks.generictypes.ElementType");
        fieldNode = new ClassNode("org.designwizard.design.FieldNode");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAllClasses() throws InexistentEntityException {
        // Internal Classes of the project
        Set<ClassNode> classes = dw.getAllClasses();
        for (ClassNode classNode : classes) {
        	System.out.println("GenericTypeExamples Class: " + classNode.getName());
		}
 
        Set<FieldNode> fields = genericTypeExamples.getAllFields();
        Set<MethodNode> methods = genericTypeExamples.getAllMethods();
        for (MethodNode method : methods) {
			System.out.println("GenericTypeExamples Method: " + method.getName());
		}

        assertNotNull("GenericTypeExamples class?", genericTypeExamples);
        assertNotNull("ElementType class?", elementType);
        assertNotNull("ElementType class?", fieldNode);
        
        assertTrue("Contains GenericTypeExamples class?", classes.contains(genericTypeExamples));
        assertTrue("Contains ElementType class?", classes.contains(elementType));
        assertTrue("Contains FieldNode class?", classes.contains(fieldNode));
        
        assertEquals("total classes: ", 2, classes.size());
        assertEquals("total fields: ", 1, fields.size());
        // include GenericTypeExamples.<init>() - Constructor
        assertEquals("total methods: ", 4, methods.size());
    }

    
    @Test
    public void testGetElements() throws InexistentEntityException {
    	assertNotNull("GenericTypeExamples class?", genericTypeExamples);
    	
    	MethodNode getElements = genericTypeExamples.getDeclaredMethod("getElements()");
    	Set<ClassNode> callees = getElements.getCalleeClasses();
    	for (ClassNode classNode : callees) {
			System.out.println("Get Elements Test Callee: " + classNode.getName());
		}
        
    	assertTrue("Contains ElementType class?", callees.contains(elementType));
    }
    
    @Test
    public void testSetElements() throws InexistentEntityException {
    	assertNotNull("GenericTypeExamples class?", genericTypeExamples);
    	
    	MethodNode setElements = genericTypeExamples.getDeclaredMethod("setElements(java.util.Set)");
    	Set<ClassNode> callees = setElements.getCalleeClasses();
    	for (ClassNode classNode : callees) {
			System.out.println("Set Elements Test Callee: " + classNode.getName());
		}
        
    	assertTrue("Contains ElementType class?", callees.contains(elementType));
    }
    
    @Test
    public void testExtractionFieldNode() throws InexistentEntityException {
        //external annotation and unused
    	Set<ClassNode> callees = genericTypeExamples.getCalleeClasses();
    	for (ClassNode classNode : callees) {
			System.out.println("FieldNode Extraction Test Callee: " + classNode.getName());
		}
        
        assertTrue("Contains FieldNode?", callees.contains(fieldNode));
    }
}