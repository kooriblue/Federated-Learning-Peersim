package fedlearning.protocol;

import fedlearning.model.NN;
import peersim.cdsim.CDProtocol;
import peersim.core.Node;

public class FederatedAverage implements CDProtocol {

    private NN model;

    @Override
    public Object clone() {
        FederatedAverage fvg = null;
        try {
            fvg = (FederatedAverage) super.clone();
        } catch (CloneNotSupportedException e) { }
        return  fvg;
    }

    public FederatedAverage(String prefix) {
        this.model = new NN();
    }

    @Override
    public void nextCycle(Node node, int protocolID) {
        int nidx = (int) node.getID();
        System.out.println("index: " + nidx);
    }


}
