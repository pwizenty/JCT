package org.comparator.jolie;

import org.comparator.Comparator;
import org.comparator.CompareResult;
import org.comparator.CompareType;
import org.utils.Program;
import org.utils.ResultStatus;
import org.utils.Type;
import org.utils.annotations.Annotation;

import java.util.HashSet;
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
        modelType.getSubnodes().forEach(node -> results.addAll(compareSubNodes(node, jolieType)));
        return results;
    }

    private CompareResult compareAnnotations(String typeName, Set<Annotation> modelAnnotation,
        Set<Annotation> jolieAnnotation, int line) {
        if (modelAnnotation.size() != jolieAnnotation.size()) {
            var symmetricDifference = new HashSet<>(modelAnnotation);
            symmetricDifference.addAll(jolieAnnotation);
            var intersection = new HashSet<>(modelAnnotation);
            intersection.retainAll(jolieAnnotation);
            symmetricDifference.removeAll(intersection);
            var messagePreFix = "Annotations variation, ";
            var stringBuilder = new StringBuilder();
            stringBuilder.append(messagePreFix);
            symmetricDifference.forEach(annotation -> stringBuilder.append(annotation.toString().replace("{}", "")));
            var message = stringBuilder.toString();
            return new CompareResult(typeName, "", CompareType.ANNOTATION, ResultStatus.VIOLATION ,
                message, line);
        } else if (!modelAnnotation.equals(jolieAnnotation)) {
            return new CompareResult(typeName, "", CompareType.ANNOTATION, ResultStatus.VIOLATION,
                "Different type of annotations.", line);
        } else {
            return new CompareResult(typeName, "", CompareType.ANNOTATION, ResultStatus.CORRECT,
                "Matching annotations.", line);
        }
    }

    private List<CompareResult> compareSubNodes(Type subNode, Type jolieType) {
        var results = new LinkedList<CompareResult>();
        var jolieNode = jolieType.getSubnodes().stream().filter(s -> s.getName().equals(subNode.getName())).toList()
            .getFirst();
        if (jolieType.getSubnodes().stream().anyMatch(s -> s.getName().equals(subNode.getName()))) {
            results.add( new CompareResult(subNode.getName(), subNode.getName(), CompareType.TYPE,ResultStatus.CORRECT,
                "Type present.", subNode.getLine()));

            results.add(compareAnnotations(subNode.getName(), subNode.getAnnotations(), jolieNode.getAnnotations(),
                subNode.getLine()));
        } else {
            results.add(new CompareResult(subNode.getName(), subNode.getName(), CompareType.TYPE,
                ResultStatus.VIOLATION, "Missing type in jolie program.", subNode.getLine()));
        }
        return results;
    }
}
