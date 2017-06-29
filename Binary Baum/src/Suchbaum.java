public class Suchbaum {

  /** Der Wurzelknoten. */
  private Binaerknoten wurzel; 

  /** Konstruiert einen Baum, der nur einen Wurzelknoten
   * mit Schluessel key besitzt. 
   */
  public Suchbaum( Object key ) {
    wurzel = new Binaerknoten( key ); 
  }

  /** Konstruiert den leeren Baum. */
  public Suchbaum( ) { 
  }

  /** Gibt den Wurzelknoten zurueck. */
  public Binaerknoten wurzel( ) {
    return wurzel; 
  }

  /** Test, ob der Baum leer ist. */
  boolean istLeer( ) {
    return wurzel == null; 
  }

  /** Gibt den Elternknoten des Knotens mit maximalem Schluessel
   * im linken direkten Teilbaum von k zurueck. Wir setzen voraus, 
   * dass dieser linke Teilbaum nicht leer ist.
   */
  private Binaerknoten predMaximum( Binaerknoten k ) {
    Binaerknoten pred = k; 
    Binaerknoten node = k.linkesKind( ); // existiert nach Voraussetzung
    while( node.rechtesKind( ) != null ) {
      pred = node;
      node = node.rechtesKind( );
    }
    return pred; 
  }

  /** Diese Klasse dient als Rueckgabetyp der Methode searchKey. */
  private class SearchKeyResult {
    Binaerknoten pred, node; 
    int direction; 
    SearchKeyResult( Binaerknoten p, Binaerknoten n, int d ) {
      pred = p; 
      node = n; 
      direction = d; 
    }
  }

  /** Gibt ein Tripel (pred, node, direction) vom Typ SearchKeyResult zurueck. 
   * Wenn pred und node beide null sind, dann ist der Baum leer. 
   * Wenn node nicht null ist: 
   *    key ist (aequivalent zum) Schluessel in Knoten node, 
   *    pred ist dessen Elternknoten, falls vorhanden, sonst null. 
   * Wenn node null ist, pred nicht null ist und direction < 0:
   *    key kommt nicht als Schluessel (bzgl. Aequivalenz) vor; 
   *    die korrekte Position fuer das Blatt mit Schluessel key
   *    ist das (noch nicht vorhandene) linke Kind von pred. 
   * Wenn node null ist, pred nicht null ist und direction > 0:
   *    analog mit rechtem Kind von pred. 
   */
  private SearchKeyResult searchKey( Comparable key ) {
    if( istLeer( ) )
      return new SearchKeyResult( null, null, 0 ); 
    Binaerknoten pred = null; 
    Binaerknoten node = wurzel; 
    int direction = 0; 
    do {
      int vergleich = key.compareTo( node.inhalt( ) ); 
      if( vergleich == 0 )
	return new SearchKeyResult( pred, node, direction ); 
      pred = node; 
      if( vergleich < 0 ) {
	node = node.linkesKind( ); 
	direction = -1; 
      }
      else { // vergleich > 0
	node = node.rechtesKind( ); 
	direction = +1; 
      }
    }
    while( node != null );
    return new SearchKeyResult( pred, node, direction ); 
  }
  
  /** Gibt einen Knoten mit einem zu key aequivalenten Schluessel 
   * zurueck, falls ein solcher Knoten vorhanden ist, sonst null. 
   */
  public Binaerknoten isMember( Comparable key ) {
    return searchKey( key ).node; 
  }

  /** Fuegt einen Knoten mit Schluessel key in den Suchbaum ein. */
  public void insert( Comparable key ) {
    SearchKeyResult r = searchKey( key ); 
    if( r.pred == null && r.node == null ) // Baum ist leer
      wurzel = new Binaerknoten( key ); 
    else if( r.direction < 0 )
      r.pred.linkesKindAendern( new Binaerknoten( key ) ); 
    else if( r.direction > 0 )
      r.pred.rechtesKindAendern( new Binaerknoten( key ) ); 
  }

  /** Entfernt den Knoten mit zu key aequivalentem Schluessel, falls vorhanden.
   */ 
  public void delete( Comparable key ) {
    SearchKeyResult r = searchKey( key ); 
    Binaerknoten k = r.node; 
    if( k != null ) { // k hat Schluessel key
      if( k.linkesKind( ) == null || k.rechtesKind( ) == null ) { // Fall 1
	Binaerknoten einzigesKind = 
	  k.linkesKind( ) == null ? k.rechtesKind( ) : k.linkesKind( ); 
	if( r.pred == null ) // k ist Wurzel
	  wurzel = einzigesKind; 
	else // r.pred ist Elternknoten von k
	  if( r.direction < 0 )
	    r.pred.linkesKindAendern( einzigesKind ); 
	  else // r.direction > 0
	    r.pred.rechtesKindAendern( einzigesKind ); 
      }
      else { // Fall 2
	Binaerknoten predMax = predMaximum( k ); 
	Binaerknoten maximum = 
	  predMax == k ? k.linkesKind( ) : predMax.rechtesKind( ); 
	k.inhaltAendern( maximum.inhalt( ) ); 
	if( predMax == k )
	  k.linkesKindAendern( maximum.linkesKind( ) ); 
	else
	  predMax.rechtesKindAendern( maximum.linkesKind( ) ); 
      }
    }
  }

  /** Gibt die Schluessel des Suchbaums in Zwischenordnung aus, 
   * also als aufsteigend geordnete Folge. 
   */
  public void druckeZwischenordnung( ) {
    if( istLeer( ) )
      System.out.println( "Der Baum ist leer." ); 
    else {
      wurzel.druckeZwischenordnung( );
      System.out.println( ); 
    }
  }

  /** Gibt die Schluessel des Suchbaums in Vorordnung mit Klammern 
   * und Kommata aus. 
   */
  public void druckeVorordnung( ) {
    if( istLeer( ) )
      System.out.println( "Der Baum ist leer." ); 
    else {
      wurzel.druckeVorordnung( );
      System.out.println( ); 
    }
  }

  public static void main( String[] args ) {
    Suchbaum b = new Suchbaum( ); 
    b.insert( "JAN" ); b.insert( "FEB" ); b.insert( "APR" ); 
    b.insert( "AUG" ); b.insert( "DEZ" ); b.insert( "MAR" ); 
    b.insert( "MAI" ); b.insert( "JUN" ); b.insert( "JUL" ); 
    b.insert( "SEP" ); b.insert( "OKT" ); b.insert( "NOV" ); 
    b.druckeZwischenordnung( ); b.druckeVorordnung( ); 
    b.delete( "FEB" ); 
    b.druckeZwischenordnung( ); b.druckeVorordnung( ); 
    b.delete( "JAN" ); 
    b.druckeZwischenordnung( ); b.druckeVorordnung( ); 
    b.delete( "MAR" ); 
    b.druckeZwischenordnung( ); b.druckeVorordnung( ); 
  }

}