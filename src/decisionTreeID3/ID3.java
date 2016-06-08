package decisionTreeID3;
import java.util.ArrayList;

/*
 * An implementation of ID3 Algorithm
 */
public class ID3 {
	
	public static Node buildTreeByID3(ArrayList<Integer> numOfTuples, ArrayList<Integer> attributes, DataSet dataset)
	{
		ArrayList<Integer> numOfTupleByDeci = dataset.countDecision(numOfTuples);
		ArrayList<Integer> zeroTuple = new ArrayList<Integer>();//for attributes.size() == 0
		for(int i = 0; i < numOfTupleByDeci.size(); i++)
			zeroTuple.add(0);
		String label = dataset.mostDecision(numOfTupleByDeci);
		if(attributes.size() == 0)
		{//all attributes have been assigned, should stop here and return a leaf
			//String label = dataset.mostDecision(numOfTuples);
			return new Node("", true, label, numOfTupleByDeci);//return a leaf
		}
		double entropy = dataset.getEntropy(numOfTuples);
		if(entropy < 0.00005)
		{//if entropy is very small, we deem it as pure, so we return a leaf
			return new Node("", true, label, numOfTupleByDeci);
		}
		//else we need to return an non-leaf new node
		int indexOfAttributeOfNode = dataset.findDecisionAttri(numOfTuples, attributes);//select the right attribute as a new node
		String nameOfAttriOfNode = dataset.getNameOfAttributeByIndex(indexOfAttributeOfNode);
		Node root = new Node(nameOfAttriOfNode, false, label, numOfTupleByDeci);
		ArrayList<String> nodeAttriValue = dataset.getAttributesByStr(nameOfAttriOfNode);//i.e., given 'wind', return ["high", "low"]
		ArrayList<ArrayList<Integer>> par = dataset.partition(numOfTuples, indexOfAttributeOfNode);
		ArrayList<Integer> reducedAttriSet = new ArrayList<Integer>(attributes);//reduce the set of Attributes after dividing
		reducedAttriSet.remove(attributes.indexOf(indexOfAttributeOfNode));
		//String largestNumOfDecision = dataset.mostDecision(numOfTuples);
		for(int i = 0; i < nodeAttriValue.size(); i++)
		{
			root.addAttribute(nodeAttriValue.get(i));
			if(par.get(i).size() == 0)
			{//no more attribute, just stop and return a leaf
				Node node = new Node("", true, label, zeroTuple);
				root.addChild(node);
			}
			else
			{//recursive call ID3 to build tree
				Node node = buildTreeByID3(par.get(i), reducedAttriSet, dataset);
				root.addChild(node);
			}
		}
		return root;
	}
	
	public static double getErrorRate(Node tree, ArrayList<Integer> testData, DataSet ds)
	{//given a tree and testing data, calculate the errorRate of the tree
		double errRate = 0.0;
		double errCounter = 0.0;
		for(int i : testData)
		{
			if(!isCorrect(tree, ds.allTuples.get(i), ds))
			{
				errCounter++;
				//System.out.println(":"+errCounter);
			}
		}
		errRate = errCounter/testData.size();
		return errRate;
	}
	private static boolean isCorrect(Node tree, ArrayList<String> tuple, DataSet ds) {
		Node node = tree;
		//System.out.println("***"+tuple);
		if(node == null) return false;
		if (node.isLeaf) 
		{
			//System.out.println("node label: " + node.label +" vs " +tuple.get(tuple.size() - 1));
			return tuple.get(tuple.size() - 1).equals(node.label);
		}
		String nodeAttri = node.attributeOfNode;
		//System.out.println("Attribute Of Node:" + nodeAttri);
		//System.out.println("Available Sons: " + node.attributeValues);
		int indexOfAttri = ds.attributeNames.indexOf(nodeAttri);//index of value in test data
		String testDataVal = tuple.get(indexOfAttri);
		//System.out.println(testDataVal);
		
		int index = node.attributeValues.indexOf(testDataVal);
		node = node.getChildren().get(index);
		return isCorrect(node, tuple, ds);
	}
	public static int[] countNode(Node root)
	{//count number of node and number of leaf
		int[] counter = new int[2];//counter[0] == number of node, and counter[1] == number of leaf
		dfs(root, counter);
		return counter;
	}
	private static void dfs(Node root, int[] counter) {
		if(root == null) return;
		if(root.children == null) 
			{//leaf node
				counter[0]++;//total number of node increment
				counter[1]++;//total number of leaf increment
				return; 
			}
		else
		{//not leaf
			counter[0]++;
			for(Node node : root.children)
			{
				if(node.isLeaf)
				{
					counter[0]++;
					counter[1]++;
				}
				else
				{//not a leaf
					dfs(node, counter);
				}
			}
		}
	}
	public static ArrayList<Node> findLeaf(Node root)
	{//find all leaf node and save to an ArrayList
		ArrayList<Node> allLeaf = new ArrayList<Node>();
		leaf(root, allLeaf);
		return allLeaf;
	}
	private static void leaf(Node root, ArrayList<Node> allLeaf) {
		// find all leaf node
		if(root == null) return;
		if(root.children == null) 
		{
			allLeaf.add(root);
			return;
		}
		else
		{
			for(Node node : root.children)
				leaf(node, allLeaf);
		}
	}
	
	
}
