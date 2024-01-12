package org.lemma;

import com.google.inject.Injector;
import de.fhdo.lemma.data.ComplexType;
import de.fhdo.lemma.data.Context;
import de.fhdo.lemma.data.DataDslStandaloneSetup;
import de.fhdo.lemma.data.DataModel;
import de.fhdo.lemma.data.DataPackage;
import de.fhdo.lemma.data.DataStructure;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        DataModel dataModel = main.loadDomainModel(args[0]);
        for (Context c : dataModel.getContexts()) {
            System.out.println("Context: " + c.getName());

            for (ComplexType t : c.getComplexTypes()) {
                if (t instanceof DataStructure) {
                    System.out.println("\tDataStructure: " + t.buildQualifiedName("."));
                }
            }
        }
    }

    private DataModel loadDomainModel(String path) throws IOException {
        EPackage.Registry.INSTANCE.put(DataPackage.eNS_URI, DataPackage.eINSTANCE);
        DataDslStandaloneSetup setup = new DataDslStandaloneSetup();
        Injector injector = setup.createInjectorAndDoEMFRegistration();
        ResourceSet set = injector.getInstance(XtextResourceSet.class);
        Resource resource = set.createResource(URI.createURI(path));
        resource.load(new FileInputStream(path), resource.getResourceSet().getLoadOptions());
        return (DataModel) resource.getContents().get(0);
    }


}