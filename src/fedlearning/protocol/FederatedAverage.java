package fedlearning.protocol;

import fedlearning.model.LogisticRegression;
import peersim.cdsim.CDProtocol;
import peersim.core.Node;
import peersim.vector.SingleValueHolder;

public class FederatedAverage implements CDProtocol {

    private LogisticRegression model;

    @Override
    public Object clone() {
        return new FederatedAverage("test");
    }

    public FederatedAverage(String prefix) {
        this.model = new LogisticRegression();
    }

    @Override
    public void nextCycle(Node node, int protocolID) {
        int nidx = node.getIndex();

        if (nidx != 0) {
            System.out.println("not 0: " + nidx);
        } else {
            System.out.println("is 0: " + nidx);
        }
    }


}
