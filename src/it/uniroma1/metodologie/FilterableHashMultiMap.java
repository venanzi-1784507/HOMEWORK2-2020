package it.uniroma1.metodologie;

import java.util.function.Predicate;
import java.util.ArrayList;

public class FilterableHashMultiMap<K, V> extends HashMultiMap<K, V>{

	/**
	 * sulla base del valore del predicato dato in input, maintiene solo le chiavi valide
	 * @param chiaviValide
	 */
	public void retainKeys(Predicate<K> chiaviValide) {
		ArrayList<K> keysToKeep = new ArrayList<>();
		for(K k: multiMappaKeys)
			if(chiaviValide.test(k)) keysToKeep.add(k);
		this.reatinKeys(keysToKeep);
	}
	
	/**
	 * restituisce il conteggio dei valori che superano il test del predicato passato in input
	 * @param valoriValidi
	 * @return intero >= 0
	 */
	public int countValues(Predicate<V> valoriValidi) {
		ArrayList<V> valuesToCount = new ArrayList<>();
		for(ArrayList<V> arrV: multiMappaValues) {
			for(V v: arrV)
				if(valoriValidi.test(v)) valuesToCount.add(v);
		}
		return valuesToCount.size();
	}
	
}
