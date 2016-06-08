package neuralNetwork;

import java.util.ArrayList;

//Layer contains a list of neurons
public class Layer {
	ArrayList<Neuron> neurons;//neurons in this layer
	int numOfNeuron;
	double[] outputToNextLayer;//output value of each neuron of this layer without bias
	
	public Layer(int numOfNeuron)
	{//constructor
		neurons = new ArrayList<>();
		for(int i = 0; i < numOfNeuron; i++)
		{
			Neuron n = new Neuron();
			neurons.add(n);
		}
		this.numOfNeuron = numOfNeuron;
	}
	void initialLayer(int numOfWeight)
	{
		neurons.get(0).output = 1.0;//set bias to 1
		for(Neuron n : neurons)
			n.initalWeight(numOfWeight);
	}
	double sigmoid(double a)
	{
		return 1.0/(1.0 + Math.exp(-a));
	}
	void forwardPropagation()
	{//calculate outputToNextLayer
		double[] outputToNextLayerWithoutBiasNode = new double[neurons.get(1).weight.size()]; 
		for(int i = 0; i < outputToNextLayerWithoutBiasNode.length; i++)
		{
			for(int j = 0; j < numOfNeuron; j++)
			{
				outputToNextLayerWithoutBiasNode[i] += neurons.get(j).output * neurons.get(j).weight.get(i); 
			}
		}
		for(int i = 0; i < outputToNextLayerWithoutBiasNode.length; i++)
			outputToNextLayerWithoutBiasNode[i] = sigmoid(outputToNextLayerWithoutBiasNode[i]);
		outputToNextLayer = outputToNextLayerWithoutBiasNode;
	}
	
	void setActivationOfLayer(double[] activation)
	{//include the first bias node, calculate activation/output for each node in current layer
		for(int i = 0; i < activation.length; i++)
		{
			neurons.get(i + 1).output = activation[i];
		}
	}
	
	double[] getOutputOfEachNode()
	{
		return outputToNextLayer;
	}
	
	double[] getError()
	{
		double[] error = new double[numOfNeuron - 1];//no bias node
		for(int i = 0; i < error.length; i++)
			error[i] = neurons.get(i + 1).error;//no bias node
		return error;
	}
	
	void setFinalLayerError(double[] expectedOutput)
	{//calculate the last layer error when compare to expected result
		for(int i = 0; i < expectedOutput.length; i++)
		{//skip the first bias neuron, since it is useless in the last layer
			neurons.get(i + 1).error = neurons.get(i + 1).output * (1 - neurons.get(i + 1).output) * 
					(expectedOutput[i] - neurons.get(i + 1).output);
		}
	}
	
	void setHiddenLayerError(double[] nextLayerError)
	{//calculate hidden layer error
		for(int i = 1; i < numOfNeuron; i++)
		{
			double sum = 0.0;
			Neuron n = neurons.get(i);
			for(int j = 0; j < n.weight.size(); j++)
				sum += n.weight.get(j) * nextLayerError[j];
			n.error = n.output * (1 - n.output) * sum;
		}
	}

	void updateNeuronWeight(double learningRate, double[] nextLayerError)
	{//For the bias node, update its weights, but keep its output/activation same as 1.0
		for(int i = 0; i < numOfNeuron; i++)
		{
			for(int j = 0; j < neurons.get(i).weight.size(); j++)
			{
				double updatedWeight = neurons.get(i).weight.get(j) + 
						learningRate * nextLayerError[j] * neurons.get(i).output;
				neurons.get(i).weight.set(j, updatedWeight);
			}
		}
	}
	
}
