/** Die Klasse Binaerknoten implementiert Knoten eines binaeren Baums. 
 * Jeder Knoten speichert ein beliebiges Objekt vom Typ Object, 
 * zusaetzlich je eine Referenz auf das linke und das rechte Kind. 
 */
class Binaerknoten {

  /** Der Knoteninhalt. */
  private Object inhalt; 

  /** Das linke und das rechte Kind. */
  private Binaerknoten linkesKind; 
  private Binaerknoten rechtesKind; 

  /** Konstruiert einen Blattknoten mit Inhalt inhalt. */
  public Binaerknoten( Object inhalt ) {
    this.inhalt = inhalt; 
  }

  /** Gibt den Inhalt des Knotens zurueck. */
  public Object inhalt( ) {
    return inhalt; 
  }

  /** Gibt das linke Kind zurueck. */
  public Binaerknoten linkesKind( ) {
    return linkesKind; 
  }

  /** Gibt das rechte Kind zurueck. */
  public Binaerknoten rechtesKind( ) {
    return rechtesKind; 
  }

  /** Der Inhalt wird zu inhalt. */
  public void inhaltAendern( Object inhalt ) {
    this.inhalt = inhalt; 
  }

  /** Das linke Kind wird zu knoten. */
  public void linkesKindAendern( Binaerknoten knoten ) {
    linkesKind = knoten; 
  }

  /** Das rechte Kind wird zu knoten. */
  public void rechtesKindAendern( Binaerknoten knoten ) {
    rechtesKind = knoten; 
  }

  /** Gibt eine String-Darstellung des Inhalts zurueck. */
  public String toString( ) {
    return inhalt.toString( ); 
  }

  /** Gibt den hier wurzelnden Baum in Zwischenordnung aus. */
  public void druckeZwischenordnung( ) {
    if( linkesKind != null ) 
      linkesKind.druckeZwischenordnung( );
    System.out.print( this + " " ); 
    if( rechtesKind != null ) 
      rechtesKind.druckeZwischenordnung( );
  }

  /** Zu Testzwecken: Gibt den hier wurzelnden Baum in Vorordnung 
   * mit Klammern und Kommata aus. 
   */
  public void druckeVorordnung( ) {
    System.out.print( this + "(" ); 
    if( linkesKind != null ) 
      linkesKind.druckeVorordnung( );
    System.out.print( "," ); 
    if( rechtesKind != null ) 
      rechtesKind.druckeVorordnung( );
    System.out.print( ")" ); 
  }

} // class Binaerknoten


/** Die Klasse Suchbaum implementiert binaere Suchbaeume mittels Referenzen. */
 // class Suchbaum