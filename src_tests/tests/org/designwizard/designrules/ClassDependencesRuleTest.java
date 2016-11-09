package tests.org.designwizard.designrules;

import static org.junit.Assert.*;

import org.designwizard.api.DesignWizard;
import org.designwizard.designrules.AbstractDependencesRule;
import org.designwizard.designrules.ClassDependencesRule;
import org.designwizard.exception.InexistentEntityException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ClassDependencesRuleTest {

    private AbstractDependencesRule rule;
    private DesignWizard dw;
    private static final String DESIGNWIZARD_DIR = "classes";

    @After
    public void tearDown() throws Exception {
        this.dw = null;
    }

    @Before
    public void setUp() throws Exception {
        this.dw = new DesignWizard(DESIGNWIZARD_DIR);
    }

    @Test
    public void testCheckRule() {
        String entity = "org.designwizard.design.Design";
        rule = new ClassDependencesRule(entity, dw);

        try {
            Boolean result = rule.checkRule();

            System.out.println(rule.getReport());

            Assert.assertTrue("Resultado: " + result, result);

            Assert.assertSame("report", "", rule.getReport());

        } catch (InexistentEntityException e) {
            fail("Exception not expected: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testAddAllowedEntities() {
        String entity = "org.designwizard.design.manager.ResultOfImpact";
        rule = new ClassDependencesRule(entity, dw);

        String[] allowed = { "org.designwizard.design.manager.ResultOfImpact", "org.designwizard.design.MethodNode",
                "java.util.Map", "java.lang.String[]", "java.lang.StringBuilder", "java.lang.String", "java.util.Set",
                "java.util.HashMap", "java.util.ArrayList", "java.lang.StringBuffer", "java.lang.Object",
                "java.util.Iterator" //, "java.util.List" removed to fail! 
                };

        rule.addAllowedEntities(allowed);

        try {
            Boolean result = rule.checkRule();

            System.out.println(rule.getReport());

            Assert.assertFalse("Resultado: " + result, result);

            Assert.assertNotSame("report", "", rule.getReport());

        } catch (InexistentEntityException e) {
            fail("Exception not expected: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testAddDeniedEntities() {
        String entity = "org.designwizard.design.Design";
        rule = new ClassDependencesRule(entity, dw);

        String[] denied = { "org.designwizard.design.manager.DesignManager", 
                "org.designwizard.design.ClassNode" //Must fail in this.
        };

        rule.addDeniedEntities(denied);

        try {
            Boolean result = rule.checkRule();

            System.out.println(rule.getReport());

            Assert.assertFalse("Resultado: " + result, result);

            Assert.assertNotSame("report", "", rule.getReport());

        } catch (InexistentEntityException e) {
            fail("Exception not expected: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
