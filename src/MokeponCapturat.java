public class MokeponCapturat extends Mokepon {
  String nomPosat;

  private String nomEntrenador;

  private int felicitat;

  static int NombreMokeponsCapturats = 0;

  public MokeponCapturat(String nom, Tipus tipus) {
    super(nom, tipus);

    nomPosat = nom;
    nomEntrenador = "Juangi";
    felicitat = 50;
    NombreMokeponsCapturats++;
  }

  public MokeponCapturat(String nom, int nivell, Tipus tipus) {
    super(nom, nivell, tipus);

    nomPosat = nom;
    nomEntrenador = "Juangi";
    felicitat = 50;
    NombreMokeponsCapturats++;
  }

  public MokeponCapturat(String nom, int nivell, int atk, int def, int vel, int hp_max, Tipus tipus) {
    super(nom, nivell, atk, def, vel, hp_max, tipus);

    nomPosat = nom;
    nomEntrenador = "Juangi";
    felicitat = 50;
    NombreMokeponsCapturats++;
  }

  public MokeponCapturat(Mokepon mok, String nomPosat, String nomEntrenador) {
    super(mok.getNom(), mok.getNivell(), mok.getAtk(), mok.getDef(), mok.getVel(), mok.getHpMax(), mok.getTipus());

    this.nomPosat = nomPosat;
    this.nomEntrenador = nomEntrenador;
    felicitat = 50;
    NombreMokeponsCapturats++;
  }

  public void acariciar() {
    if (felicitat < 100)
      felicitat += 10;
    if (felicitat > 100)
      felicitat = 100;
  }

  public void atacar(Mokepon atacat, int num_atac) {
    if (getDebilitat()) {
      System.out.println("No pots atacar, estàs debilitat");
      return;
    }
    if (atacat.getDebilitat()) {
      System.out.println(Constants.ANSI_RED + "No pots atacar, el teu oponent està debilitat" + Constants.ANSI_RESET);
      return;
    }
    if (getAtacks()[num_atac] != null) {
      if (getAtacks()[num_atac].moviments_actuals > 0) {
        getAtacks()[num_atac].moviments_actuals--;
        double efectivitat = efectivitat(getAtacks()[num_atac].tipus, atacat.getTipus());
        double felic = (felicitat < 50) ? 0.8 : 1.2;
        long dany = Math
            .round(((2 * getNivell() / 5 + 2) * getAtacks()[num_atac].poder * getAtk() / atacat.getDef() / 50 + 2) * efectivitat * felic);
        System.out.println(nomPosat + " ha fet " + dany + " de dany a " + atacat.getNom());
        atacat.setHp(atacat.getHpActual() - dany);
        if (atacat.getHpActual() <= 0) {
          atacat.setHp(0);
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

  public String getNomEntrenador() {
    return nomEntrenador;
  }

  public void setNomEntrenador(String nomEntrenador) {
    this.nomEntrenador = nomEntrenador;
  }
 
  public String toString() {
    return "MokeponCapturat [nomPosat=" + nomPosat + ", nomEntrenador=" + nomEntrenador + ", felicitat=" + felicitat + "]";
  }
}
