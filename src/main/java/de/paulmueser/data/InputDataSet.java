package de.paulmueser.data;

import org.apache.jena.rdf.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents an input data set for RDF model processing.
 * This class provides methods to read RDF data, extract distinct facts,
 * and retrieve specific statements from the RDF model.
 */
public class InputDataSet {
    private final String NAMESPACE = "http://dice-research.org/data/fb15k-237.ttl#";

    private Model model;
    private final boolean isTrainData;

    /**
     * Constructs an InputDataSet with the given RDF data set.
     *
     * @param dataSet the RDF data set file path
     */
    public InputDataSet(String dataSet) {
        this.isTrainData = false;
        this.model = ModelFactory.createDefaultModel();
        this.model.read(dataSet);
    }

    /**
     * Constructs an InputDataSet with the given RDF data set and training data flag.
     *
     * @param isTrainData flag indicating if the data is training data
     * @param dataSet the RDF data set file path
     */
    public InputDataSet(boolean isTrainData, String dataSet) {
        this.isTrainData = isTrainData;
        this.model = ModelFactory.createDefaultModel();
        this.model.read(dataSet);
    }

    /**
     * Retrieves a list of test statements from the RDF model.
     *
     * @return a list of TestStatement objects
     */
    public List<TestStatement> getTestStatements() {
        List<TestStatement> testStatements = new ArrayList<>();
        Set<Resource> facts = this.getDistinctFacts();
        for (Resource fact : facts) {
            testStatements.add(new TestStatement(this, fact, this.isTrainData));
        }

        return testStatements;
    }

    /**
     * Retrieves a set of distinct facts from the RDF model.
     *
     * @return a set of distinct Resource objects representing facts
     */
    public Set<Resource> getDistinctFacts() {
        Set<Resource> facts = new HashSet<>();
        StmtIterator iterator = this.model.listStatements();
        while (iterator.hasNext()) {
            Statement statement = iterator.next();
            Resource fact = statement.getSubject();
            if (fact.getNameSpace().startsWith(NAMESPACE)) {
                facts.add(fact);
            }
        }

        return facts;
    }

    /**
     * Retrieves the object of a statement given a fact and predicate.
     *
     * @param fact the fact resource
     * @param predicate the predicate property
     * @return the object resource of the statement
     */
    public Resource getObjectOfStatement(Resource fact, Property predicate) {
        List<Statement> statements = this.model.listStatements(fact, predicate, (RDFNode) null).toList();
        if (statements.isEmpty()) {
            return null;
        }
        return statements.getFirst().getObject().asResource();
    }

    /**
     * Retrieves the literal object of a statement given a fact and predicate.
     *
     * @param fact the fact resource
     * @param predicate the predicate property
     * @return the literal object of the statement
     */
    public Literal getLiteralOfStatement(Resource fact, Property predicate) {
        List<Statement> statements = this.model.listStatements(fact, predicate, (RDFNode) null).toList();
        if (statements.isEmpty()) {
            return null;
        }
        return statements.getFirst().getObject().asLiteral();
    }

    /**
     * Checks if the data set is training data.
     *
     * @return true if the data set is training data, false otherwise
     */
    public boolean isTrainData() {
        return isTrainData;
    }
}