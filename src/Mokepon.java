
public class Mokepon {
  private String nom;

  private int nivell;

  private int atk;

  private int def;

  private int vel;

  private int exp;

  private long hp_max;

  private long hp_actual;

  private Tipus tipus;

  private Atacs[] getAtacks = new Atacs[2];

  private boolean debilitat = false;

  // Constructors
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

  public Mokepon(String nom, int nivell, int atk, int def, int vel, long hp_max, Tipus tipus) {
    this.nom = nom;
    this.nivell = nivell;
    this.atk = atk;
    this.def = def;
    this.vel = vel;
    this.hp_max = hp_max;
    this.hp_actual = hp_max;
    this.tipus = tipus;
  }

  // Getters
  public String getNom() {
    return nom;
  }

  public int getNivell() {
    return nivell;
  }

  public int getAtk() {
    return atk;
  }

  public int getDef() {
    return def;
  }

  public int getVel() {
    return vel;
  }

  public int getExp() {
    return exp;
  }

  public long getHpMax() {
    return hp_max;
  }

  public long getHpActual() {
    return hp_actual;
  }

  public Tipus getTipus() {
    return tipus;
  }

  public Atacs[] getAtacks() {
    return getAtacks;
  }

  public boolean getDebilitat() {
    return debilitat;
  }

  // Setters
  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setNivell(int nivell) {
    this.nivell = nivell;
  }

  public void setAtk(int atk) {
    this.atk = atk;
  }

  public void setDef(int def) {
    this.def = def;
  }

  public void setVel(int vel) {
    this.vel = vel;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }

  public void setHp(long hp_actual) {
    if (hp_actual > hp_max)
      this.hp_actual = hp_max;
    else if (hp_actual < 0)
      this.hp_actual = 0;
    else
      this.hp_actual = hp_actual;
  }

  public void setTipus(Tipus tipus) {
    this.tipus = tipus;
  }

  public void setDebilitat(boolean debilitat) {
    this.debilitat = debilitat;
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

  private void pujarNivell() {
    nivell++;
    hp_max += Utilitats.random(0, 5);
    atk += Utilitats.random(0, 2);
    def += Utilitats.random(0, 2);
    vel += Utilitats.random(0, 2);
    hp_actual = hp_max;
  }

  public void afegirAtac(Atacs atac) {
    if (getAtacks[0] == null)
      getAtacks[0] = atac;
    else if (getAtacks[1] == null)
      getAtacks[1] = atac;
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
    if (getAtacks[num_atac] != null) {
      if (getAtacks[num_atac].moviments_actuals > 0) {
        getAtacks[num_atac].moviments_actuals--;
        double efectivitat = efectivitat(getAtacks[num_atac].tipus, atacat.tipus);
        long dany = Math
            .round(((2 * nivell / 5 + 2) * getAtacks[num_atac].poder * atk / atacat.def / 50 + 2) * efectivitat);
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

  public static double efectivitat(Tipus atac, Tipus defensa) {
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
  
  public MokeponCapturat capturar(String nomEntrenador, String nomDonat) {

    if(!(this instanceof MokeponCapturat)) {
      System.out.println("Has capturat a " + nomDonat);      
      return new MokeponCapturat(this, nomDonat, nomEntrenador);
    } else {
      System.out.println("Aquest mokepon ja està capturat");
      return null;
    }
  }

  public void stats() {
    System.out.println(Constants.ANSI_BLUE + "Nom: " + nom);
    System.out.println("Nivell: " + nivell);
    System.out.println("Atk: " + atk);
    System.out.println("Def: " + def);
    System.out.println("Vel: " + vel);
    System.out.println("HP: " + hp_actual + "/" + hp_max);
    System.out.println("Tipus: " + tipus);
    System.out.println(debilitat ? "Debilitat" : "No debilitat");
    System.out.println(nom + " ha après els atacs: ");
    for (int i = 0; i < getAtacks.length; i++) {
      if (getAtacks[i] != null)
        System.out.println(getAtacks[i].nom + " poder: " + getAtacks[i].poder + " tipus: " + getAtacks[i].tipus + " moviments: "
            + getAtacks[i].moviments_actuals + "/" + getAtacks[i].moviments_maxims);
    }
    System.out.println(Constants.ANSI_RESET);
  }

  public String toString() {
    return "Mokepon [nom=" + nom + ", nivell=" + nivell + ", atk=" + atk + ", def=" + def + ", vel=" + vel + ", hp_max=" + hp_max
        + ", hp_actual=" + hp_actual + ", tipus=" + tipus + ", debilitat=" + debilitat + "]";
  }
}