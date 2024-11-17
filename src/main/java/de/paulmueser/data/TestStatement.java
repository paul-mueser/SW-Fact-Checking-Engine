package de.paulmueser.data;

import org.apache.jena.rdf.model.*;

/**
 * Represents a test statement in the RDF model.
 * This class encapsulates the subject, predicate, and object of a statement,
 * along with the actual and predicted truth values.
 */
public class TestStatement {
    private final Property SUBJECT = ResourceFactory.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#subject");
    private final Property PREDICATE = ResourceFactory.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#predicate");
    private final Property OBJECT = ResourceFactory.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#object");
    private final Property HASTRUTHVALUE = ResourceFactory.createProperty("http://swc2017.aksw.org/hasTruthValue");

    private final Resource fact;
    private final Resource subject;
    private final Property predicate;
    private final Resource object;

    private final double actualTruthValue;

    private double predictedTruthValue;

    /**
     * Constructs a TestStatement with the given input data set, fact, and training data flag.
     *
     * @param inputDataSet the input data set
     * @param fact the fact resource
     * @param isTrainData flag indicating if the data is training data
     */
    public TestStatement(InputDataSet inputDataSet, Resource fact, boolean isTrainData) {
        this.fact = fact;
        this.subject = inputDataSet.getObjectOfStatement(fact, SUBJECT);
        this.predicate = ResourceFactory.createProperty(inputDataSet.getObjectOfStatement(fact, PREDICATE).toString());
        this.object = inputDataSet.getObjectOfStatement(fact, OBJECT);
        if (isTrainData) {
            Literal truthValue = inputDataSet.getLiteralOfStatement(fact, HASTRUTHVALUE);
            if (truthValue != null) {
                this.actualTruthValue = truthValue.getDouble();
            } else {
                this.actualTruthValue = -1;
            }
        } else {
            this.actualTruthValue = -1;
        }
    }

    /**
     * Generates a result statement with the predicted truth value.
     *
     * @return the result statement
     */
    public Statement generateResultStatement() {
        return ResourceFactory.createStatement(this.fact, HASTRUTHVALUE, ResourceFactory.createTypedLiteral(this.predictedTruthValue));
    }

    // region Getters/Setters
    public Resource getFact() {
        return fact;
    }
    
    public Resource getSubject() {
        return subject;
    }

    public Property getPredicate() {
        return predicate;
    }

    public Resource getObject() {
        return object;
    }

    public double getActualTruthValue() {
        return actualTruthValue;
    }

    public double getPredictedTruthValue() {
        return predictedTruthValue;
    }

    public void setPredictedTruthValue(double predictedTruthValue) {
        this.predictedTruthValue = predictedTruthValue;
    }
    // endregion
}