package model;

//idea for this came from https://connected2know.com/programming/java-factory-pattern/

/**
 * The implementation of treasure with a treasure enum built in and also implements a factory
 * method.
 */
public class TreasureImpl {
  /**
   * The treasure enum which contains rubies, diamonds, and sapphires.
   */
  enum TreasureType {
    RUBY("Ruby"), DIAMOND("Diamond"), SAPPHIRE("Sapphire");

    private final String name;

    TreasureType(String name) {
      this.name = name;
    }
  }

  static class Ruby implements Treasure {

    @Override
    public String getName() {
      return "Ruby";
    }

    /** This is used in the factory patter to return a Ruby.
     *
     * @return a new ruby object.
     */
    @Override
    public Treasure getTreasure() {
      return new Ruby();
    }
  }

  static class Diamond implements Treasure {

    @Override
    public String getName() {
      return "Diamond";
    }

    /** This is used in the factory patter to return a Diamond.
     *
     * @return a new diamond object.
     */
    @Override
    public Treasure getTreasure() {
      return new Diamond();
    }
  }

  static class Sapphire implements Treasure {

    @Override
    public String getName() {
      return "Sapphire";
    }

    /** This is used in the factory patter to return a Sapphire.
     *
     * @return a new sapphire object.
     */
    @Override
    public Treasure getTreasure() {
      return new Sapphire();
    }
  }

  static class TreasureFactory {
    /**This is the treasure factory, when the get treasure method is called it actually makes the
     *  treasure from the treasure enum.
     *
     * @param treasureType the type of treasure that needs to be made. either a ruby, diamond,
     *                     or sapphire.
     * @return a piece of treasure.
     */
    static Treasure getTreasureFromEnum(TreasureType treasureType) {

      Treasure treasure = null;

      if (TreasureType.RUBY.equals(treasureType)) {
        treasure = new Ruby();
      } else if (TreasureType.DIAMOND.equals(treasureType)) {
        treasure = new Diamond();
      } else if (TreasureType.SAPPHIRE.equals(treasureType)) {
        treasure = new Sapphire();
      }
      return treasure;
    }
  }

}
