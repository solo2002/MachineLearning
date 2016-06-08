package decisionTreeID3;
import java.util.ArrayList;

public class DataSet {
	ArrayList<ArrayList<String>> possibleAttriOp;//contain all the values of every attribute, 
	//i.e. wind  include weak and strong, so weak and strong are stored in this list.
	ArrayList<ArrayList<String>> allTuples; //contain tuples of data
	ArrayList<String> attributeNames;//contain all the type of attribute
	ArrayList<String> tuple;//one tuple of data
	
	public DataSet(ArrayList<String> attributeNames, ArrayList<ArrayList<String>> possibleAttriOp)
	{
		this.attributeNames = attributeNames;
		this.possibleAttriOp = possibleAttriOp;
		this.allTuples = new ArrayList<ArrayList<String>>();
	}
	
	public ArrayList<Integer> countDecision(ArrayList<Integer> numOfTuple)
	{//Given an list of number of data, count the number of possible decisions
		int num = possibleAttriOp.size();//number of all attributes types
		int numOfDecision = possibleAttriOp.get(num - 1).size();//the last column contains all the possible decision
		ArrayList<Integer> counter = new ArrayList<Integer>();//contains the number of potential decision
		for(int i = 0; i < numOfDecision; i++)
			counter.add(0);//initialization
		for(int i : numOfTuple)
		{
			String decision = allTuples.get(i).get(possibleAttriOp.size() - 1);//get the decision for tuple i
			int index = possibleAttriOp.get(possibleAttriOp.size() - 1).indexOf(decision);//find the position of this decision.
			int position = counter.get(index).intValue();
			position++;
			counter.set(index, position);//set the number of decisions
		}
		return counter;
	}
	public String mostDecision(ArrayList<Integer> numOfTuple)
	{//calculate the decision with most number, like yes/no in the PlayTennis sample
		//ArrayList<Integer> counter = countDecision(numOfTuple);
		int max = 0;
		for(int i = 0; i < numOfTuple.size(); i++)
		{//for each decision, counter contains its number, and here just find the one (decision) with largest number
			if(numOfTuple.get(i).intValue() > numOfTuple.get(max).intValue())
				max = i;
		}
		String result = possibleAttriOp.get(possibleAttriOp.size() - 1).get(max);//get the string value related decision
		return result;
	}
	public double getEntropy(ArrayList<Integer> numOfTuple)
	{
		double entropy = 0.0;
		if(numOfTuple.size() == 0)
		{
			throw new IllegalArgumentException("The data is empty!");
		}
		int totalNumOfTuples = numOfTuple.size();
		ArrayList<Integer> counter = countDecision(numOfTuple);
		for(int i : counter) 
		{	
			if(i > 0) 
			{
				double probability = (double) i / (double) totalNumOfTuples;
				entropy += -probability * Math.log(probability) / Math.log(2);
			}
		}
		 return entropy;
	}
	
	public int findDecisionAttri(ArrayList<Integer> examples, ArrayList<Integer> attributes)
	{//select the right attribute as new node based on information gain
		//double oldEntropy = getEntropy(numOfTuple);
		double minEn = 100.0;
		int targetAttri = attributes.get(0);
		for(int attri : attributes)
		{
			ArrayList<ArrayList<Integer>> res = partition(examples, attri);
			double sumEntropy = 0.0;
			for(int i = 0; i < res.size(); i++)
			{
				ArrayList<Integer> cat = res.get(i);
				if(cat.size() > 0)
				{
					double subEntropy = getEntropy(cat);
					sumEntropy += subEntropy * (cat.size()) / examples.size();
				}//the calculation of information gain is total entropy - sum of Entropy for each attribute
				//so we could only compare the sum of Entropy for each attribute instead of information gain
			}
			if(sumEntropy < minEn)
			{
				minEn = sumEntropy;
				targetAttri = attri;///changed something here
			}
			
		}
		return targetAttri;
	}
	public ArrayList<Integer> numOfTuplesByDecision(ArrayList<Integer> numOfTuple)
	{//return an ArrayList which contains the number of final conclusion, i.e. 3 'yes', 2 'no', return [3, 2]
		ArrayList<Integer> res = new ArrayList<Integer>();
		//if(numOfTuple.size() == 0) return res;
		ArrayList<ArrayList<Integer>> par = partition(numOfTuple, attributeNames.size() - 1);
		for(ArrayList<Integer> i : par)
		{
			res.add(i.size());
		}
		return res;
	}
	public int countErrWithLabel(ArrayList<Integer> testTargetCount, String label) 
	{
		int index = possibleAttriOp.get(attributeNames.size()-1).indexOf(label);
		int sum = 0;
		for(int i = 0; i<testTargetCount.size(); i++)
		{
			sum += testTargetCount.get(i);
		}
		return sum - testTargetCount.get(index);
	}
	public String getNameOfAttributeByIndex(int indexOfAttributeOfNode)
	{//get the name of attribute by given an index
		return attributeNames.get(indexOfAttributeOfNode);
	}
	public ArrayList<String> getAttributesByStr(String nodeAttribute)
	{//get an ArrayList of potential value of a given attribute, 
		//i.e., given 'wind', return ["high", "low"]
		int index = attributeNames.indexOf(nodeAttribute);
		return possibleAttriOp.get(index);
	}
	public ArrayList<ArrayList<Integer>> partition(ArrayList<Integer> numOfTuple, int targetAttri) 
	{//return the partition separated by 'targetAttri'(yes or no), but keep the order of original sequence(or, it's stable)
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		int numOfChildrenNode = possibleAttriOp.get(targetAttri).size();
		for(int i = 0; i < numOfChildrenNode; i++)
			res.add(new ArrayList<Integer>());//each ArrayList stands for one partition
		for(int i : numOfTuple)
		{
			String targetVal = allTuples.get(i).get(targetAttri);//the value of target arrtibute to be separated
			int index = possibleAttriOp.get(targetAttri).indexOf(targetVal);//the position of subNode
			res.get(index).add(i);
		}
		return res;
	}
	public boolean addExample(int[] example){
		if(example.length != attributeNames.size())
			return false;
		ArrayList<String> newExample = new ArrayList<String>();
		for(int i = 0; i < example.length; i++){
			newExample.add(possibleAttriOp.get(i).get(example[i]));
		}
		allTuples.add(newExample);
		return true;
	}
	public static void main(String[] args)
	{
		System.out.println("\u00B1");
	}
}
