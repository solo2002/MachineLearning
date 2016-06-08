package neuralNetwork;

import java.util.ArrayList;

//contains a list of layers, and constructed by backpropagation algorithm 
public class Network {

	int[] numOfNeuron;//the length is number of layers, and the respective number
	//is the number of neurons in each layer
	double learningRate;//the value should be between 10 to -2 and 10 to -4
	int numOfLayer;//at least 2, if the hidden layer is 0;
	ArrayList<Layer> layersInNetwork;
	
	public Network(int numOfNeuron[], double learningRate)
	{//constructor
		layersInNetwork = new ArrayList<>();
		this.numOfNeuron = numOfNeuron;
		this.learningRate = learningRate;//the value should be between 10 to -2 and 10 to -4;
		numOfLayer = numOfNeuron.length;
		for(int i : numOfNeuron)
			layersInNetwork.add(new Layer(i));
		//initialize weight of neuron in network
		for(int i = 0; i < numOfLayer - 1; i++)
		{
			int numOfWeight = layersInNetwork.get(i + 1).numOfNeuron - 1;//skip the bias neuron
			layersInNetwork.get(i).initialLayer(numOfWeight);
		}
	}
	void forward(double[] input)
	{
		layersInNetwork.get(0).setActivationOfLayer(input);//the input of first layer
		for(int i = 1; i < numOfLayer; i++)
		{
			layersInNetwork.get(i - 1).forwardPropagation();
			double[] OutputOfLastLayer = layersInNetwork.get(i - 1).getOutputOfEachNode();
			layersInNetwork.get(i).setActivationOfLayer(OutputOfLastLayer);
		}
	}
	void backpropagation(double[] result)
	{
		layersInNetwork.get(numOfLayer - 1).setFinalLayerError(result);//calculate the error of final layer
		for(int i = numOfLayer - 2; i > 0; i--)//from second of final to the second layer, namely, hidden layer
		{
			double[] nextLayerError = layersInNetwork.get(i + 1).getError();
			layersInNetwork.get(i).setHiddenLayerError(nextLayerError);
			layersInNetwork.get(i).updateNeuronWeight(learningRate, nextLayerError);
		}
		layersInNetwork.get(0).updateNeuronWeight(learningRate, layersInNetwork.get(1).getError());//there are at least 2 layers 
		//in the network, input layer and output layer if without any hidden layer 
	}

}
