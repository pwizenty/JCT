package org.comparator.jolie;

import org.comparator.Comparator;
import org.comparator.CompareResult;
import org.comparator.CompareType;
import org.utils.Program;
import org.utils.ResultStatus;
import org.utils.Type;
import org.utils.annotations.Annotation;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class JolieComparator implements Comparator {
    private Program modelProgram;
    private Program jolieProgram;

    public JolieComparator(Program modelProgram, Program jolieProgram) {
        this.modelProgram = modelProgram;
        this.jolieProgram = jolieProgram;
    }

    @Override
    public List<CompareResult> compare() {

        return compareTypes();
    }

    private List<CompareResult> compareTypes() {
        var compareResults = new LinkedList<CompareResult>();

        modelProgram.getTypes().forEach(modelType -> {
            var optionalJolieType = jolieProgram.getTypes().stream().filter(type
                -> type.getName().equals(modelType.getName())).findFirst();
            if (optionalJolieType.isPresent()) {
                compareResults.addAll(compareType(modelType, optionalJolieType.get()));
            } else {
                compareResults.add(new CompareResult(modelType.getName(), "", CompareType.NOT_PRESENT,
                    ResultStatus.VIOLATION, "Missing type in Jolie program.", modelType.getLine()));
            }
        });
        return compareResults;
    }

    private List<CompareResult> compareType(Type modelType, Type jolieType) {
        var results = new LinkedList<CompareResult>();
        results.add(compareAnnotations(modelType.getName(), modelType.getAnnotations(), jolieType.getAnnotations(),
            modelType.getLine()));
        modelType.getSubnodes().forEach(node -> results.add(compareSubNodes(node, jolieType)));
        return results;
    }

    private CompareResult compareAnnotations(String typeName, Set<Annotation> modelAnnotation,
        Set<Annotation> jolieAnnotation, int line) {
        if (modelAnnotation.size() != jolieAnnotation.size()) {
            return new CompareResult(typeName, "", CompareType.ANNOTATIONS, ResultStatus.VIOLATION ,
                "Different number of annotations.", line);
        } else if (!modelAnnotation.equals(jolieAnnotation)) {
            return new CompareResult(typeName, "", CompareType.ANNOTATIONS, ResultStatus.VIOLATION,
                "Different type of annotations.", line);
        } else {
            return new CompareResult(typeName, "", CompareType.ANNOTATIONS, ResultStatus.CORRECT,
                "Matching annotations.", line);
        }
    }

    private CompareResult compareSubNodes(Type subNode, Type jolieType) {
        if (jolieType.getSubnodes().stream().anyMatch(s -> s.getName().equals(subNode.getName()))) {
            return new CompareResult(subNode.getName(), subNode.getName(), CompareType.TYPE,ResultStatus.CORRECT ,
                "Type present.", subNode.getLine());
        } else {
            return new CompareResult(subNode.getName(), subNode.getName(), CompareType.TYPE, ResultStatus.VIOLATION,
                "Missing type in jolie program.", subNode.getLine());
        }
    }
}
