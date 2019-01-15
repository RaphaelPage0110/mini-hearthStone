package business.messageModels;

public class HisHandMessage {
  private int nbrCards;

  public HisHandMessage(int nbr) {
    nbrCards = nbr;
  }

  public int getNbrCards() {
    return nbrCards;
  }

  public void setNbrCards(int nbrCards) {
    this.nbrCards = nbrCards;
  }
}
