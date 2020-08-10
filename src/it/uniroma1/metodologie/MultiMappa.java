package it.uniroma1.metodologie;

import java.util.Collection;

public interface MultiMappa<K,V> extends Iterable<K> {
	
	/**
	 * specifica se i valore possono essere duplicati
	 * @author giaco
	 *
	 */
	public enum Type { DUPLICATES, NO_DUPLICATES }
	
	/**
	 * aggiungie l'elemento v a fronte della chiave k
	 * @param k
	 * @param v
	 */
	public void put(K k, V v);
	
	/**
	 * restituisce l'elenco di valori associati alla chiave k
	 * @param k
	 * @return
	 */
	public Collection<V> get(K k);
	
	/**
	 * restituisce l'elenco di valori associati alla chiave k
	 * o il risultato defaultResult se la chiave non e' presente
	 * @param k
	 * @param defaultResult
	 * @return
	 */
	public Collection<V> get(K k, Collection<V> defaultResult);
	
	/**
	 * mantiene nella mappa solo le chiavi specificate
	 * @param keys
	 */
	public void reatinKeys(Collection<K> keys);
	
	/**
	 * restituisce la dimensione della mappa (numero di chiavi)
	 * @return
	 */
	public int size();
}
