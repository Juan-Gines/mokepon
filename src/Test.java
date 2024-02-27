public class Test {
  public static void main(String[] args) {
    Mokepon mikachu = new Mokepon("Mikachu", 5, 10, 10, 10, 50, Tipus.FOC);
    Mokepon mokarp = new Mokepon("Mokarp", 5, 10, 10, 10, 50, Tipus.PLANTA);

    mikachu.atorgarExperiencia(150);
    mikachu.afegirAtac(new Atacs("Raix", 100, Tipus.FOC, 10));
    mikachu.afegirAtac(new Atacs("Aigua", Tipus.AIGUA));
    MokeponCapturat mikachuCapturat = new MokeponCapturat(mikachu, "Mikachulo", "Juangi");
    mikachuCapturat.afegirAtac(new Atacs("Raix", 100, Tipus.FOC, 10));
    mikachuCapturat.stats();
    mokarp.afegirAtac(new Atacs("Planta", Tipus.PLANTA));
    mokarp.afegirAtac(new Atacs("Aigua", Tipus.AIGUA));
    mikachu.atacar(mokarp, 0);
    mikachuCapturat.atacar(mokarp, 0);
    mokarp.stats();
    mikachu.atacar(mokarp, 0);
    mokarp.stats();
    mikachu.atacar(mokarp, 0);
    mokarp.stats();
    mikachu.atacar(mokarp, 0);
    mokarp.stats();
    mikachu.atacar(mokarp, 0);
    mokarp.atacar(mikachu, 0);
    mokarp.stats();
    mikachu.stats();
    mokarp.curar();
    MokeponCapturat mokarpCapturat = mokarp.capturar("Juangi", "Pececillo");
    mokarpCapturat.stats();
    mokarpCapturat.capturar("Juangi", "Pececillo");
    System.out.println("Mokepons capturats: " + MokeponCapturat.NombreMokeponsCapturats);
    System.out.println(mikachu.getAtacks()[0]);
    System.out.println(mikachuCapturat);
    System.out.println(mikachu);
  }
}
