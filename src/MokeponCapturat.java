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
    if (debilitat) {
      System.out.println("No pots atacar, estàs debilitat");
      return;
    }
    if (atacat.debilitat) {
      System.out.println(Constants.ANSI_RED + "No pots atacar, el teu oponent està debilitat" + Constants.ANSI_RESET);
      return;
    }
    if (atacs[num_atac] != null) {
      if (atacs[num_atac].moviments_actuals > 0) {
        atacs[num_atac].moviments_actuals--;
        double efectivitat = efectivitat(atacs[num_atac].tipus, atacat.tipus);
        double felic = (felicitat < 50) ? 0.8 : 1.2;
        long dany = Math
            .round(((2 * nivell / 5 + 2) * atacs[num_atac].poder * atk / atacat.def / 50 + 2) * efectivitat * felic);
        System.out.println(nom + " ha fet " + dany + " de dany a " + atacat.nom);
        atacat.hp_actual -= dany;
        if (atacat.hp_actual <= 0) {
          atacat.hp_actual = 0;
          atacat.debilitarse();
        }
      } else {
        System.out.println("No pots fer aquest atac, no tens moviments");
      }
    } else {
      System.out.println("No tens aquest atac");
    }
  }

  public void stats() {
    System.out.println(Constants.ANSI_YELLOW + "Nom posat: " + nomPosat);
    System.out.println("Nom entrenador: " + nomEntrenador);
    System.out.println("Felicitat: " + felicitat + Constants.ANSI_RESET);
    super.stats();
  }
}
