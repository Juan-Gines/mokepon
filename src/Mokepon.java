
public class Mokepon {
  String nom;

  int nivell;

  int atk;

  int def;

  int vel;

  int exp;

  int hp_max;

  int hp_actual;

  Tipus tipus;

  Atacs[] atacs = new Atacs[2];

  boolean debilitat = false;

  public Mokepon() {
    nom = "Sense definir";
    nivell = 1;
    atk = 1;
    def = 1;
    vel = 1;
    hp_max = 10;
    hp_actual = hp_max;
    tipus = Tipus.FOC;
  }

  public Mokepon(String nom, Tipus tipus) {
    this.nom = nom;
    nivell = 1;
    atk = 1;
    def = 1;
    vel = 1;
    hp_max = 10;
    hp_actual = hp_max;
    this.tipus = tipus;
  }

  public Mokepon(String nom, int nivell, Tipus tipus) {
    this(nom, tipus);

    for (int i = 0; i < nivell - 1; i++) {
      pujarNivell();
    }
  }

  public Mokepon(String nom, int nivell, int atk, int def, int vel, int hp_max, Tipus tipus) {
    this.nom = nom;
    this.nivell = nivell;
    this.atk = atk;
    this.def = def;
    this.vel = vel;
    this.hp_max = hp_max;
    this.hp_actual = hp_max;
    this.tipus = tipus;
  }

  public void diguesNom() {
    System.out.println(nom);
  }

  public void atorgarExperiencia(int exp_atorgada) {
    exp += exp_atorgada;

    while (exp >= 100) {
      exp -= 100;
      pujarNivell();
    }
  }

  public void pujarNivell() {
    nivell++;
    hp_max += Utilitats.random(0, 5);
    atk += Utilitats.random(0, 2);
    def += Utilitats.random(0, 2);
    vel += Utilitats.random(0, 2);
    hp_actual = hp_max;
  }

  public void afegirAtac(Atacs atac) {
    if (atacs[0] == null)
      atacs[0] = atac;
    else if (atacs[1] == null)
      atacs[1] = atac;
    else
      System.out.println("No pots tenir més de dos atacs");

  }

  public void atacar(Mokepon atacat, int num_atac) {
    if (debilitat) {
      System.out.println("No pots atacar, estàs debilitat");
      return;
    }
    if (atacat.debilitat) {
      System.out.println("No pots atacar, el teu oponent està debilitat");
      return;
    }
    if (atacs[num_atac] != null) {
      if (atacs[num_atac].moviments_actuals > 0) {
        atacs[num_atac].moviments_actuals--;
        double efectivitat = efectivitat(atacs[num_atac].tipus, atacat.tipus);
        long dany = Math
            .round(((2 * nivell / 5 + 2) * atacs[num_atac].poder * atk / atacat.def / 50 + 2) * efectivitat);
        if (dany < atacat.hp_actual) {
          atacat.hp_actual -= dany;
        } else {
          atacat.hp_actual = 0;
          atacat.debilitarse();
        }
        System.out.println(nom + " ha fet " + dany + " de dany a " + atacat.nom);
      } else {
        System.out.println("No pots fer aquest atac, no tens moviments");
      }
    } else {
      System.out.println("No tens aquest atac");
    }
  }

  public double efectivitat(Tipus atac, Tipus defensa) {
    if (atac == Tipus.FOC && defensa == Tipus.AIGUA || atac == Tipus.AIGUA && defensa == Tipus.PLANTA
        || atac == Tipus.PLANTA && defensa == Tipus.FOC) {
      return 0.5;
    } else if (atac == Tipus.AIGUA && defensa == Tipus.FOC || atac == Tipus.FOC && defensa == Tipus.PLANTA
        || atac == Tipus.PLANTA && defensa == Tipus.AIGUA) {
      return 2;
    } else {
      return 1;
    }
  }

  public void debilitarse() {
    debilitat = true;
    System.out.println(Constants.ANSI_RED + nom + " s'ha debilitat" + Constants.ANSI_RESET);
  }

  public void curar() {
    hp_actual = hp_max;
    if (debilitat)
      debilitat = false;
  }

  public void stats() {
    System.out.println(Constants.ANSI_BLUE + "Nom: " + nom);
    System.out.println("Nivell: " + nivell);
    System.out.println("Atk: " + atk);
    System.out.println("Def: " + def);
    System.out.println("Vel: " + vel);
    System.out.println("HP: " + hp_actual + "/" + hp_max);
    System.out.println("Tipus: " + tipus);
    System.out.println(debilitat ? "Debilitat" : "No debilitat" + Constants.ANSI_RESET);
    System.out.println(nom + " ha après els atacs: ");
    for (int i = 0; i < atacs.length; i++) {
      if (atacs[i] != null)
        System.out.println(atacs[i].nom + " poder: " + atacs[i].poder + " tipus: " + atacs[i].tipus + " moviments: "
            + atacs[i].moviments_actuals + "/" + atacs[i].moviments_maxims);
    }
  }
}