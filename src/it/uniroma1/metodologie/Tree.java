package it.uniroma1.metodologie;

import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Tree {
	
	private int rootId;
	private TreeMap<Integer, ArrayList<Integer>> tree;
	
	/**
	 * constructor to create a tree with only the root
	 * @param rootId root id to get the node from the tree
	 */
	public Tree(Integer rootId) {
		this(rootId, new TreeMap<Integer, ArrayList<Integer>>(Map.of(rootId, new ArrayList<>())));
		//tree.put(rootId, new ArrayList<>());
	}
	
	/**
	 * constructor to create a full tree by arguments
	 * @param rootId root id 
	 * @param tree all the tree with every node 
	 */
	public Tree(Integer rootId, TreeMap<Integer, ArrayList<Integer>> tree) {
		this.rootId = rootId;
		this.tree = tree;
	}
	
	/**
	 * check if a specified node id is in the tree from the root
	 * @param id id of the node to find
	 * @return true if the node is in the tree
	 */
	public boolean find(int id) {
		//base case the root value is k
		if(rootId == id) return true;
		//base case root has no childred
		if(tree.get(rootId).size() == 0) return false;
		//iterate the children nodes of the root
		for(int childId: tree.get(rootId)) {
			if(find(id, childId))
				return true;
		}
		return false;
	}
	
	/**
	 * check if a specified node id is in the root from the specified node id
	 * @param idToFind node id to find
	 * @param currNodeId node id from witch start the search
	 * @return boolean true if idToFind is in the tree from currNodeId otherwise false
	 */
	public boolean find(int idToFind, int currNodeId) {
		//base case: check if currNodeId is equal to idToFind
		if(idToFind == currNodeId) return true;
		//iterate the children
		for(int child: tree.get(currNodeId)) {
			if(find(idToFind,child))
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param k node id from witch start insert node ids
	 * @return HashSet of nodes id 
	 */
	public Set<Integer> getSubTree(int k){
		//if there isn't a node with id equal to k
		if(!find(k)) return null;
		return getSubTree(k, new HashSet<Integer>());
		
	}
	
	/**
	 * fill the set of nodes id from the relative root passed as an argument
	 * @param currNodeId relative root id
	 * @param set of nodes id
	 * @return
	 */
	private Set<Integer> getSubTree(int currNodeId, Set<Integer> set){
		set.add(currNodeId);
		//insert currNodeId children's id in to the set
		for(int child: tree.get(currNodeId)) {
			set = getSubTree(child, set);
		}
		return set;
	}
	
	/**
	 * create a map where node ids are the keys and the values are a set of every node ids of the relative subtree
	 * @param nodes
	 * @return
	 */
	public Map<Integer, Set<Integer>> getSubTrees(int... nodes){
		TreeMap<Integer, Set<Integer>> outputMap = new TreeMap<>();
		for(int node: nodes)
			outputMap.put(node, getSubTree(node));
		return outputMap;
	}
}
