public class MokeponCapturat extends Mokepon {
  String nomPosat;

  String nomEntrenador;

  int felicitat;

  public MokeponCapturat(String nom, Tipus tipus) {
    super(nom, tipus);

    nomPosat = nom;
    nomEntrenador = "Juangi";
    felicitat = 50;
  }

  public MokeponCapturat(String nom, int nivell, Tipus tipus) {
    super(nom, nivell, tipus);

    nomPosat = nom;
    nomEntrenador = "Juangi";
    felicitat = 50;
  }

  public MokeponCapturat(String nom, int nivell, int atk, int def, int vel, int hp_max, Tipus tipus) {
    super(nom, nivell, atk, def, vel, hp_max, tipus);

    nomPosat = nom;
    nomEntrenador = "Juangi";
    felicitat = 50;
  }

  public MokeponCapturat(Mokepon mok, String nomPosat, String nomEntrenador) {
    super(mok.nom, mok.nivell, mok.atk, mok.def, mok.vel, mok.hp_max, mok.tipus);

    this.nomPosat = nomPosat;
    this.nomEntrenador = nomEntrenador;
    felicitat = 50;
  }

  public void acariciar() {
    if (felicitat < 100)
      felicitat += 10;
    if (felicitat > 100)
      felicitat = 100;
  }

  public void atacar(Mokepon atacat, int num_atac) {
    if (atacs[num_atac] != null) {
      if (atacs[num_atac].moviments_actuals > 0) {
        atacs[num_atac].moviments_actuals--;
        double efectivitat = super.efectivitat(atacs[num_atac].tipus, atacat.tipus);
        long dany = Math
            .round(((2 * nivell / 5 + 2) * atacs[num_atac].poder * atk / atacat.def / 50 + 2) * efectivitat);
        if 
        atacat.hp_actual -= dany;
        System.out.println(nom + " ha fet " + dany + " de dany a " + atacat.nom);
      } else {
        System.out.println("No pots fer aquest atac, no tens moviments");
      }
    } else {
      System.out.println("No tens aquest atac");
    }
  }
}
