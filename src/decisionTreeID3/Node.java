package decisionTreeID3;
//The data structure of Node in Decision tree
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Node {
	public static final double[] chiTest = {0, 3.841, 5.991, 7.815, 9.488, 11.070,12.592, 14.067, 15.507, 16.919,
			18.307, 19.675, 21.026, 22.362, 23.685, 24.996, 26.296, 27.587, 28.869, 30.144, 31.410, 32.671, 33.924, 
			35.172, 36.415, 37.652, 38.885, 40.113, 41.337, 42.557, 43.773, 44.985, 46.194, 47.400, 48.602, 49.802,
			50.998, 52.192, 53.384, 54.572, 55.758, 67.505, 79.082, 90.531, 101.879,
			113.145, 124.342};//chi square test (0.05) table: the index equals to degree of freedom.
	//degree range:1-40, 50, 60, 70, 80, 90, 100
	String attributeOfNode;//the assigned attribute of this node
	ArrayList<Node> children;//contain all the name of attributes, the last one is 'Yes/No' 'The result'
	ArrayList<String> attributeValues;
	//ArrayList<Integer> indexOfTargetAttri;
	String label;
	ArrayList<Integer> trainningData;
	boolean isLeaf;
	static DataSet dataset;
	//constructor
	public Node(String attributeOfNode, boolean isLeaf, String label, ArrayList<Integer> indexOfAttriNames)
	{
		if(isLeaf)//a leaf node, no children, stop at here
		{
			this.attributeOfNode = null;
			this.attributeValues = null;
			this.label = label;
			this.children = null;
			this.isLeaf = isLeaf;
			//this.indexOfTargetAttri = indexOfAttriNames;
		}
		else
		{//not a leaf
			this.attributeOfNode = attributeOfNode;
			this.attributeValues = new ArrayList<String>();
			//this.label = null;
			this.label = label;
			this.children = new ArrayList<Node>();
			this.isLeaf = isLeaf;
			//this.indexOfTargetAttri = indexOfAttriNames;
		}
		trainningData = indexOfAttriNames;
		
	}

	public String getAttributeType()
	{
		return attributeOfNode;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	public ArrayList<Node> getChildren()
	{
		return children;
	}
	public void addAttribute(String attri)
	{//add new attribute to the node
		attributeValues.add(attri);
	}
	public void addChild(Node node)
	{//add child to the node
		children.add(node);
	}
	/*public void chiPrune()
	{
		if(isLeaf)
			return;
		if(!valid(this))
		{
			this.pruneToLeaf();
			return;
		}
		for(Node child : children)
			child.chiPrune();
	}
	private boolean valid(Node node) 
	{
		String nodeAttri = node.getAttributeType();
		System.out.println(nodeAttri);
		//int index = dataset.attributeNames.size() - 1;
		System.out.println(dataset == null);
		//int index2 = dataset.attributeNames.indexOf(nodeArrti);
		//System.out.println(index + " " + nodeArrti);
		return false;
	}
	private static boolean isValidChiSqureTest(Node node, ArrayList<Integer> numOfTuples, DataSet ds) {
		String nodeArrti = node.getAttributeType();
		int index = ds.attributeNames.size() - 1;
		int index2 = ds.attributeNames.indexOf(nodeArrti);
		System.out.println(index + " " + nodeArrti);
		int numOfDecision = ds.possibleAttriOp.get(index).size();
		int numOfAttriVal = ds.possibleAttriOp.get(index2).size();
		int degreeOfFreedom = (numOfDecision - 1) * (numOfAttriVal - 1);
		double[][] observed = new double[numOfDecision][numOfAttriVal];
		ArrayList<Integer> countDecision1 = ds.countDecision(numOfTuples);
		ArrayList<Integer> countDecision2 = ds.numOfTuplesByDecision(numOfTuples);
		System.out.print(countDecision1 + " vs " + countDecision2);
		for(int m : numOfTuples)
		{
			
		}
		return false;
	}
	public static boolean validChiSquareTest(final double[] expected, final double[] observed)
	{
		if (expected.length < 2) 
			 throw new IllegalArgumentException("length less than 2");
		if (expected.length != observed.length) 
		 throw new IllegalArgumentException("the number of expected and observed is not same");
		for(double d : expected)
			if(d <= 0) throw new IllegalArgumentException("Non-positive number in expected");
		for(double d : observed)
			if(d < 0) throw new IllegalArgumentException("Negative number in expected");
		double sumExpected = 0d;
		double sumObserved = 0d;
	  for (int i = 0; i < observed.length; i++) 
	  {
	  	sumExpected += expected[i];
	  	sumObserved += observed[i];
		}
		double ratio = 1.0d;
		boolean rescale = false;
		if (Math.abs(sumExpected - sumObserved) > 10E-6) 
		{
			ratio = sumObserved / sumExpected;
			rescale = true;
		}
		double sumSq = 0.0d;
		for (int i = 0; i < observed.length; i++) 
		{
			if (rescale) 
			{
				final double dev = observed[i] - ratio * expected[i];
				sumSq += dev * dev / (ratio * expected[i]);
			} 
			else 
			{
				final double dev = observed[i] - expected[i];
				sumSq += dev * dev / expected[i];
			}
		}
			
	}*/
	public void pessimPrune()
	{
		if(isLeaf)
			return;
		for(Node child : children)
		{
			child.pessimPrune();
		}
		double selfCorrect = this.correctedMisNumSelf();
		double subTreeCorrect = this.correctedMisNum();
		double sum = (double)this.getSumExamples();
		double standardErr = Math.sqrt(subTreeCorrect*(sum-subTreeCorrect)/sum);
		if(selfCorrect > subTreeCorrect + standardErr){//keep the subtree
			return;
		}
		this.pruneToLeaf();//prune this subtree
		return;
	}
	public void pruneToLeaf()
	{
		if(isLeaf)
			return;
		attributeOfNode = null;
		attributeValues = null;
		children = null;
		isLeaf = true;
	}
	public double correctedMisNumSelf()
	{
		int errNum = dataset.countErrWithLabel(trainningData, label);
		return (double)errNum + 0.5;
	}
	public int getSumExamples()
	{
		int sum = 0;
		for(int i = 0; i<trainningData.size(); i++){
			sum += trainningData.get(i);
		}
		return sum;
	}
	public double correctedMisNum()
	{
		if(this.isLeaf){
			return this.correctedMisNumSelf();
		}
		double res = 0.0;
		for(Node child : children){
			res += child.correctedMisNum();
		}
		return res;
	}
	public void printTree(int a)
	{
		if(isLeaf)
		{
			System.out.print("<Leaf Node>:" + label);
			System.out.println(trainningData.toString());
			//System.out.println(indexOfTargetAttri.toString());
		}
		else
		{
			System.out.print(attributeOfNode);
			System.out.println(trainningData.toString());
			//System.out.println(indexOfTargetAttri.toString());
			//System.out.print("Children:" + children.size());
			for(int k = 0; k < children.size(); k++)
			{
				//System.out.print(i + "\t");
				for(int j = 0; j < a + 1; j++)
					System.out.print("|.");
				System.out.print("(" + attributeValues.get(k) + ")" + "-" );
				children.get(k).printTree(a+1);
			}
		}
	}
	public void outputTree(int a)
	{//print tree to output file
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHMMSS");
		String s = sdf.format(cal.getTime()).toString();
		try{
			PrintStream out = new PrintStream(new File("output" + s +".txt"));
			System.setOut(out);
			if(isLeaf)
			{
				out.print("Leaf Node:" + label);
				out.println(trainningData.toString());
				//out.println(indexOfTargetAttri.toString());
			}
			else
			{
				out.print(attributeOfNode);
				out.println(trainningData.toString());
				//out.println(indexOfTargetAttri.toString());
				
				for(int k = 0; k < children.size(); k++)
				{
					for(int j = 0; j < a + 1; j++)
						out.print("|.");
					out.print("(" + attributeValues.get(k) + ")" + "-" );
					children.get(k).printTree(a+1);
				}
			}
		}
		catch(FileNotFoundException fx)
		{
			System.out.println(fx);
		}
	}
}
