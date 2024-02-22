public class Atacs {
  String nom;
  double poder;
  Tipus tipus;
  int moviments_maxims;
  int moviments_actuals;

  public Atacs(String nom, Tipus tipus) {
    this.nom = nom;
    poder = 10;
    this.tipus = tipus;
    moviments_maxims = 10;
    moviments_actuals = moviments_maxims;
  }

  public Atacs(String nom, int poder, Tipus tipus, int moviments_maxims) {
    this.nom = nom;

    if (poder < 10)
      this.poder = 10;
    else if (poder > 100)
      this.poder = 100;
    else
      this.poder = poder;

    this.tipus = tipus;
    this.moviments_maxims = moviments_maxims;
    this.moviments_actuals = moviments_maxims;
  }

}
