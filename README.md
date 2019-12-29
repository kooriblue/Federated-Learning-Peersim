# Federated-Learning-Peersim

> Implementation of the federated learning paper: [Communication-Efficient Learning of Deep Networks from Decentralized Data](https://arxiv.org/abs/1602.05629)

The design goal of federated learning is to implement efficient machine learning among multiple participants or multiple computing nodes on the premise of ensuring information security during data exchange, protecting the privacy of terminal data and personal data, and ensuring legal compliance.

This experiment uses the **cycle-based** simulation mode of peersim.

The FederatedAveraging Algorithm.

<img src="http://cdn.sshpark.com.cn/20191229154015.png" alt="FedAvg" height="220" />

# Environment
 * PeerSim 1.5 (A Peer-to-Peer Simulator)
 * Java 12

# Todo

- [ ] Loading datasets, such as **MNIST** and **CIFAR10**
- [ ] LogisticRegression
- [ ] FedAvg Algorithm
- [ ] Observer
- [ ] Plotting loss curve