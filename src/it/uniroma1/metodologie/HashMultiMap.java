package it.uniroma1.metodologie;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HashMultiMap<K, V> implements MultiMappa<K, V>{

	//tutto ArrayList<k, list<V>> in cui quando inserisco un elemento 
	//di chiave k nella relativa lista se è gia' presente allora lo sovrascrivo
	protected Type type;
	protected ArrayList<K> multiMappaKeys;
	protected ArrayList<ArrayList<V>> multiMappaValues;
		
	//MANCA IL COSTRUTTORE!!!!
	public HashMultiMap() {
		this(Type.NO_DUPLICATES);
	}
	
	public HashMultiMap(Type type) {
		this.type = type;
		multiMappaKeys = new ArrayList<>();
		multiMappaValues = new ArrayList<>();
	}
	
	@Override
	public Iterator<K> iterator() {
		//this way it returns an iterator of the keys so if the
		//values are needed, you have to access them from the relative key
		return multiMappaKeys.iterator();
	}

	@Override
	public void put(K k, V v) {
		//check if the key is already saved in the map
		if(!(multiMappaKeys.contains(k))) {
			multiMappaKeys.add(k);
			multiMappaValues.add(new ArrayList<>(List.of(v)));
		}
		else {
			//if type == DUPLICATES the key will contain mutli values
			if(type == Type.DUPLICATES)
				//add the new value to the values of the key
				multiMappaValues.get(multiMappaKeys.indexOf(k)).add(v);
			else
				//change the value of the key with the new one
				multiMappaValues.set(multiMappaKeys.indexOf(k), new ArrayList<>(List.of(v)));
		}		
	}

	@Override
	public Collection<V> get(K k) {
		return multiMappaValues.get(multiMappaKeys.indexOf(k));
	}

	@Override
	public Collection<V> get(K k, Collection<V> defaultResult) {
		//itero le chiavi
		for(int i=0; i<multiMappaKeys.size(); i++) {
			if(multiMappaKeys.get(i).equals(k)) return multiMappaValues.get(i);
		}
		return defaultResult;
	}

	@Override
	public void reatinKeys(Collection<K> keys) {
		//MI CONVIENE PRIMA SALVARMI GLI INDICI IN UNA LISTA, ORDINARLA IN ORDINE DECRESCENTE
		//POI CANCELLARE DA ENTRAMBE LE LISTE
		ArrayList<K> toRemove = new ArrayList<>();
		for(K k:multiMappaKeys)
			if(!keys.contains(k)) toRemove.add(k);
		for(K k: toRemove) {
			multiMappaValues.remove(multiMappaKeys.indexOf(k));
			multiMappaKeys.remove(multiMappaKeys.indexOf(k));
		}
	}

	@Override
	public int size() {
		return multiMappaKeys.size();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(K k: this) {
			sb.append("chiave: "+k+
					  " valori: "+multiMappaValues.get(multiMappaKeys.indexOf(k)).toString()+"\n");
			
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		HashMultiMap<Integer, String> hmm = new HashMultiMap<>(Type.DUPLICATES);
		hmm.put(1, "uno");
		hmm.put(1, "uno_UNO");
		hmm.put(2, "due");
		hmm.put(3, "tre");
		hmm.put(4, "quattro");
		hmm.put(5, "cinque");
		System.out.println(hmm.toString());
		hmm.reatinKeys(List.of(1,3,5));
		
		System.out.println();
		
		System.out.println(hmm.toString());
	}

}
