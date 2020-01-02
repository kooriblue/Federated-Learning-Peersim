package fedlearning.init;

import peersim.core.Control;
import peersim.core.Network;
import peersim.core.Node;

public class Shuffle implements Control {
    public Shuffle(String prefix) {}

    @Override
    public boolean execute() {
        for (int i = 0; i < Network.size(); i++)

        Network.print();
        return false;
    }
}
